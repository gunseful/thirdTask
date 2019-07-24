package com.gunseful.parser;

import com.gunseful.item.Gem;
import com.gunseful.item.GemsVisualParameters;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;


public class DomParser implements Parser {
    @Override
    public List<Gem> parse(File file) throws ParserConfigurationException, SAXException, IOException {
        List<Gem> gems = new ArrayList<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.parse(file);

        NodeList gemElements = document.getDocumentElement().getElementsByTagName("gem");

        for (int i = 0; i < gemElements.getLength(); i++) {
            Node gem = gemElements.item(i);
            NamedNodeMap attributes = gem.getAttributes();

            String name = document.getElementsByTagName("name").item(i).getTextContent();
            boolean percious = false;
            if(document.getElementsByTagName("preciousness").item(i).getTextContent().equals("preciousness")){
                percious = true;
            }
            String origin = document.getElementsByTagName("origin").item(i).getTextContent();
            String colour = document.getElementsByTagName("colour").item(i).getTextContent();
            int transparency = Integer.parseInt(document.getElementsByTagName("transparency").item(i).getTextContent());
            int faceting = Integer.parseInt(document.getElementsByTagName("faceting").item(i).getTextContent());
            double price = Double.parseDouble(document.getElementsByTagName("price").item(i).getTextContent());
            int value = Integer.parseInt(document.getElementsByTagName("value").item(i).getTextContent());
            String id = attributes.getNamedItem("id").getNodeValue();
            GemsVisualParameters gemsVisualParameters = new GemsVisualParameters(colour, transparency, faceting);

            gems.add(new Gem(id, name, percious, origin, price, value, gemsVisualParameters));

        }
        return gems;
    }
}
