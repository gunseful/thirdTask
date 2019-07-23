package com.gunseful.parser;

import com.gunseful.item.Gem;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public interface Parser {
    List<Gem> parse(File file) throws ParserConfigurationException, SAXException, IOException, XMLStreamException;
}
