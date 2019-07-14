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

class SaxParser {

    public List<Gem> parse(String xmladdress) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        Handler saxParser = new Handler();
        parser.parse(new File(xmladdress), saxParser);
        return saxParser.getGems();
    }

    static class Handler extends DefaultHandler {
        public static ArrayList<Gem> gems = new ArrayList<>();

        public static ArrayList<Gem> getGems() {
            return gems;
        }
        private String id;
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
                if (lastElementName.equals("value"))
                    value = information;
            }
        }

        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            if ((name != null && !name.isEmpty()) && (precious != null && !precious.isEmpty()) && (origin != null && !origin.isEmpty()
                    && (colour != null && !colour.isEmpty()) && (transparency != null && !transparency.isEmpty())
                    && (faceting != null && !faceting.isEmpty())
                    && (value != null && !value.isEmpty()))) {
                boolean preciousB = false;
                int transparencyB = Integer.parseInt(transparency);
                int facetingB = Integer.parseInt(faceting);
                int valueB = Integer.parseInt(value);
                if (precious.equals("precious")) {
                    preciousB = true;
                }


                gems.add(new Gem(id, name, preciousB, origin, colour, transparencyB, facetingB, valueB));
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