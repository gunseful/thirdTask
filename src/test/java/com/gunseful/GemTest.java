package com.gunseful;

import com.gunseful.factory.Factory;
import com.gunseful.item.Gem;
import com.gunseful.item.GemsVisualParameters;
import com.gunseful.parser.Parser;
import com.gunseful.reader.Reader;
import org.junit.Assert;
import org.junit.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLStreamException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GemTest {
    public static List<Gem> getListExpected(){
        List<Gem> gemListExpected = new ArrayList<>();
        gemListExpected.add(new Gem("id1", "Almaz", true,
                "Africa", 1200.8, 15, new GemsVisualParameters("White", 100, 10)));
        gemListExpected.add(new Gem("id2", "Rube", true,
                "USA", 850.4, 200, new GemsVisualParameters("Red", 80, 5)));
        gemListExpected.add(new Gem("id3", "Sapphire", true,
                "Kazakhstan", 920.4, 100, new GemsVisualParameters("Green", 65, 8)));
        gemListExpected.add(new Gem("id4", "Ametrine", false,
                "Kazakhstan", 81.1, 500, new GemsVisualParameters("Violet", 64, 7)));
        gemListExpected.add(new Gem("id5", "Amethyst", false,
                "Russia", 42.42, 300, new GemsVisualParameters("Violet", 75, 9)));
        gemListExpected.add(new Gem("id6", "Topaz", false,
                "France", 6.77, 115, new GemsVisualParameters("Blue", 80, 10)));
        gemListExpected.add(new Gem("id7", "Diamond", true,
                "Italy", 1600.47, 30, new GemsVisualParameters("Blue", 100, 14)));
        gemListExpected.add(new Gem("id8", "Diamond", true,
                "Italy", 1000.2, 70, new GemsVisualParameters("Blue", 100, 15)));
        return gemListExpected;
    }

    @Test
    public void SaxParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {

        Factory factory = Factory.getInstance();
        Parser parser = factory.chooseParser("sax");
        List<Gem> gemListActual = parser.parse(new Reader().readFile("src/main/resources/gem.xml"));

        Assert.assertEquals(getListExpected(), gemListActual);
        gemListActual = null;
    }

    @Test
    public void DomParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {

        Factory factory = Factory.getInstance();
        Parser parser = factory.chooseParser("dom");
        List<Gem> gemListActual = parser.parse(new Reader().readFile("src/main/resources/gem.xml"));

        Assert.assertEquals(getListExpected(), gemListActual);
        gemListActual = null;
    }

    @Test
    public void StaxParserTest() throws ParserConfigurationException, XMLStreamException, SAXException, IOException {

        Factory factory = Factory.getInstance();
        Parser parser = factory.chooseParser("stax");
        List<Gem> gemListActual = parser.parse(new Reader().readFile("src/main/resources/gem.xml"));

        Assert.assertEquals(getListExpected(), gemListActual);
    }
}