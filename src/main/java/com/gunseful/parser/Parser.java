package com.gunseful.parser;

import com.gunseful.item.Gem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Parser {
    ArrayList<Gem> gems = new ArrayList<>();
    List<Gem> parse(String xmlAddress) throws ParserConfigurationException, SAXException, IOException, XMLStreamException;
}
