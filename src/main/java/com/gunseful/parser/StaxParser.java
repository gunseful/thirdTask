package com.gunseful.parser;

import com.gunseful.item.Gem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class StaxParser {
    public List<String> parse(String xmlAddress) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader xmlStreamReader = xmlInputFactory.createXMLStreamReader(new StringReader(xmlAddress));

        List<String> list = new ArrayList<>();

        while (xmlStreamReader.hasNext()) {
            switch (xmlStreamReader.getEventType()) {
                case XMLStreamConstants.START_ELEMENT:
                    if ("name".equals(xmlStreamReader.getLocalName())) {
                        String name = xmlStreamReader.getElementText();
                        System.out.println(name);
                        list.add(name);
                    }
                    break;
                default:
                    break;
            }
            xmlStreamReader.next();
        }


        return list;
    }
}

