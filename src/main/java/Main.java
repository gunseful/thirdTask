import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    private static ArrayList<Gem> gems = new ArrayList<>();
    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        AdvancedXMLHandler handler = new AdvancedXMLHandler();
        parser.parse(new File("src/gem.xml"), handler);

        for (Gem gem : gems){
            System.out.println(gem.toString());
        }

        Comparator<Gem> comparator = new Comparator<Gem>(){
            public int compare(Gem o1, Gem o2) {
                return o1.getColour().compareTo(o2.getColour());
            }
        };

        Collections.sort(gems, comparator);
        System.out.println("\n");
        for (Gem gem : gems){
            System.out.println(gem.toString());
        }



    }

    private static class AdvancedXMLHandler extends DefaultHandler {
        private String name, lastElementName;
        private String precious;
        private String origin;
        private String colour;
        private String transparency;
        private String faceting;
        private String value;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
        }

        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            String information = new String(ch, start, length);

            information = information.replace("\n", "").trim();

            if (!information.isEmpty()) {
                if (lastElementName.equals("name"))
                    name = information;
                if (lastElementName.equals("preciousness"))
                    precious = information;
                if (lastElementName.equals("origin"))
                    origin = information;
                if (lastElementName.equals("colour"))
                    colour = information;
                if (lastElementName.equals("transparency"))
                    transparency = information;
                if (lastElementName.equals("faceting"))
                    faceting = information;
                if (lastElementName.equals("value"))
                    value = information;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ( (name != null && !name.isEmpty()) && (precious != null && !precious.isEmpty()) && (origin != null && !origin.isEmpty()
                    && (colour != null && !colour.isEmpty()) && (transparency != null && !transparency.isEmpty())
                    && (faceting != null && !faceting.isEmpty())
                    && (value != null && !value.isEmpty()) ) ) {
                boolean preciousB = false;
                int transparencyB = Integer.parseInt(transparency);
                int facetingB = Integer.parseInt(faceting);
                int valueB = Integer.parseInt(value);
                if(precious.equals("precious")) {
                    preciousB = true;
                }
                gems.add(new Gem(name, preciousB,   origin, colour, transparencyB, facetingB, valueB));
                name = null;
                precious = null;
                origin = null;
                colour = null;
                transparency = null;
                faceting = null;
                value = null;
            }
        }
    }
}
