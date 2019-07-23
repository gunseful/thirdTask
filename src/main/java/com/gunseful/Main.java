package com.gunseful;

import com.gunseful.factory.Factory;
import com.gunseful.item.Gem;
import com.gunseful.parser.*;
import com.gunseful.reader.Reader;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {

        Factory factory = Factory.getInstance();
        Parser parser = factory.chooseParser("stax");
        List<Gem> list = parser.parse(new Reader().readFile("src/main/resources/gem.xml"));
        list.sort(Comparator.comparing(Gem::getId));
        list.forEach(System.out::println);
        System.out.println("\n");
        list.sort((Gem o1, Gem o2)->o1.getGemsVisualParameters().getColour().compareTo(o2.getGemsVisualParameters().getColour()));
        list.forEach(System.out::println);

    }
}
