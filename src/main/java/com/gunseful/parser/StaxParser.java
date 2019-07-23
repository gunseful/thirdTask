package com.gunseful.parser;

import com.gunseful.item.Gem;
import com.gunseful.item.GemsVisualParameters;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.*;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class StaxParser implements Parser {
    public List<Gem> parse(File file) throws ParserConfigurationException, SAXException, IOException, XMLStreamException { ;
        List<Gem> gems = new ArrayList<>();

        XMLInputFactory factory = XMLInputFactory.newInstance();

        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(file));

        String id = "";
        String name = "";
        boolean precious = false;
        String origin = "";
        String colour = "";
        int transparency = 0;
        int faceting = 0;
        double price = 0;
        int value = 0;
        while (eventReader.hasNext()) {
            XMLEvent xmlEvent = eventReader.nextEvent();

            if (xmlEvent.isStartElement()) {
                StartElement startElement = xmlEvent.asStartElement();

                if ("gem".equalsIgnoreCase(startElement.getName().getLocalPart())) {
                }

                Iterator<Attribute> iterator = startElement.getAttributes();

                while (iterator.hasNext()) {
                    Attribute attribute = iterator.next();
                    QName qName = attribute.getName();
                    if ("id".equalsIgnoreCase(qName.getLocalPart())) {
                        id = attribute.getValue();
                    }
                }

                switch (startElement.getName().getLocalPart()) {
                    case "name":
                        Characters nameDataEvent = (Characters) eventReader.nextEvent();
                        name = nameDataEvent.getData();
                        break;

                    case "preciousness":
                        Characters preciousnessDataEvent = (Characters) eventReader.nextEvent();
                        if (preciousnessDataEvent.getData().equals("precious")) {
                            precious = true;
                        }else{
                            precious = false;
                        }
                        break;

                    case "origin":
                        Characters originDataEvent = (Characters) eventReader.nextEvent();
                        origin = originDataEvent.getData();
                        break;

                    case "colour":
                        Characters colourDataEvent = (Characters) eventReader.nextEvent();
                        colour = colourDataEvent.getData();
                        break;

                    case "transparency":
                        Characters transparencyDataEvent = (Characters) eventReader.nextEvent();
                        transparency = Integer.parseInt(transparencyDataEvent.getData());
                        break;

                    case "faceting":
                        Characters facetingDataEvent = (Characters) eventReader.nextEvent();
                        faceting = Integer.parseInt(facetingDataEvent.getData());
                        break;

                    case "price":
                        Characters priceDataEvent = (Characters) eventReader.nextEvent();
                        price = Double.parseDouble(priceDataEvent.getData());
                        break;

                    case "value":
                        Characters valueDataEvent = (Characters) eventReader.nextEvent();
                        value = Integer.parseInt(valueDataEvent.getData());
                        break;
                }
            }

            if (xmlEvent.isEndElement()) {
                EndElement endElement = xmlEvent.asEndElement();
                if ("gem".equalsIgnoreCase(endElement.getName().getLocalPart())) {
                    GemsVisualParameters gemsVisualParameters = new GemsVisualParameters(colour, transparency, faceting);
                    if(!id.equals("")){
                    gems.add(new Gem(id, name, precious, origin, price, value, gemsVisualParameters));}
                    id = "";
                }
            }
        }
        return gems;
    }
}

