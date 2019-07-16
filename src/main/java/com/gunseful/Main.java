package com.gunseful;

import com.gunseful.comporators.Comporators;
import com.gunseful.item.Gem;
import com.gunseful.parser.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {

        Factory factory = Factory.getInstance();
        Parser parser = factory.chooseParser("sax");
        List<Gem> list = parser.parse(new Reader().readFile("src/main/resources/gem.xml"));
        list.sort(Comparator.comparing(Gem::getName));
        list.forEach(System.out::println);

    }
}
