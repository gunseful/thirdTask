package com.gunseful.parser;

import com.gunseful.item.Gem;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements Parser{

    public List<Gem> parse(String xmlAddress) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        Handler saxParser = new Handler();
        parser.parse(new File(xmlAddress), saxParser);
        return Handler.getGems();
    }

    static class Handler extends DefaultHandler {

        static ArrayList<Gem> getGems() {
            return Parser.gems;
        }
        private String id;
        private String name, lastElementName;
        private String precious;
        private String origin;
        private String colour;
        private String transparency;
        private String faceting;
        private String price;
        private String value;

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            lastElementName = qName;
            if (qName.equals("gem")) {
                id = attributes.getValue("id");
            }
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
                if (lastElementName.equals("price"))
                    price = information;
                if (lastElementName.equals("value"))
                    value = information;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((name != null && !name.isEmpty()) && (precious != null && !precious.isEmpty()) && (origin != null && !origin.isEmpty()
                    && (colour != null && !colour.isEmpty()) && (transparency != null && !transparency.isEmpty())
                    && (faceting != null && !faceting.isEmpty())
                    && (price != null && !price.isEmpty()
                    && (value != null && !value.isEmpty())))) {
                boolean preciousB = false;
                int transparencyB = Integer.parseInt(transparency);
                int facetingB = Integer.parseInt(faceting);
                Double priceB = Double.parseDouble(price);
                int valueB = Integer.parseInt(value);
                if (precious.equals("precious")) {
                    preciousB = true;
                }
                gems.add(new Gem(id, name, preciousB, origin, colour, transparencyB, facetingB, priceB, valueB));
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