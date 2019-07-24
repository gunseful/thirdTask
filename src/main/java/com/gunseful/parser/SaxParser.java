package com.gunseful.parser;
import com.gunseful.item.Gem;
import com.gunseful.item.GemsVisualParameters;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements Parser{


    public List<Gem> parse(File file) throws ParserConfigurationException, SAXException, IOException {

        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser builder = factory.newSAXParser();

        Handler saxParser = new Handler();

        builder.parse(file, saxParser);
        return Handler.getGems();
    }

    static class Handler extends DefaultHandler {


        private String lastElementName;

        static List<Gem> gems = new ArrayList<>();

        static List<Gem> getGems() {
            return gems;
        }

        GemsVisualParameters gemsVisualParameters = new GemsVisualParameters(null, 0, 0);

        Gem gem = new Gem(null, null, false, null, 0.0, 0, gemsVisualParameters);

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
            if (qName.equals("gem")) gem.id = attributes.getValue("id");
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();
            boolean precious = false;

            if(!information.isEmpty()) {
                try {
                    Field field = gemsVisualParameters.getClass().getDeclaredField(lastElementName);
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    if (fieldType.equals(String.class)) {
                        field.set(gemsVisualParameters, (String) information);
                    }
                    if (fieldType.equals(int.class)) {
                        Integer i = Integer.parseInt(information);
                        field.set(gemsVisualParameters, i);
                    }
                    if (fieldType.equals(double.class)) {
                        Double i = Double.parseDouble(information);
                        field.set(gemsVisualParameters, i);}

                } catch (NoSuchFieldException | IllegalAccessException ignored) {
                }


                try {
                    Field field = gem.getClass().getDeclaredField(lastElementName);
                    field.setAccessible(true);
                    Class<?> fieldType = field.getType();
                    if (fieldType.equals(String.class)) {
                        field.set(gem, (String) information);
                    }
                    if (fieldType.equals(int.class)) {
                        Integer i = Integer.parseInt(information);
                        field.set(gem, i);
                    }
                    if (fieldType.equals(double.class)) {
                        Double i = Double.parseDouble(information);
                        field.set(gem, i);
                    }
                    if (fieldType.equals(boolean.class)) {
                        if(information.equals("preciousness"))
                            precious = true;
                        field.set(gem, precious);
                    }
                } catch (NoSuchFieldException | IllegalAccessException ignored) {
                }
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {

                if(gem.getName()!=null && gem.getOrigin()!=null && gem.getValue()!=0 && gemsVisualParameters.getColour()!=null && gemsVisualParameters.getFaceting()!=0){
                    gems.add(gem);
                    gemsVisualParameters = new GemsVisualParameters(null, 0, 0);
                    gem = new Gem(null, null, false, null, 0.0, 0, gemsVisualParameters);
                }
            }
        }
    }
