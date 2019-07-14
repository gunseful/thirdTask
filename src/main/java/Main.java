

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    private static ArrayList<Gem> gems = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SaxParser saxParser = new SaxParser();
        gems.addAll(saxParser.parse("src/gem.xml"));
        gems.forEach(System.out::println);
        System.out.println("\n");
        gems.sort(Comporators.comparatorById);
        gems.forEach(System.out::println);
        System.out.println("\n");
        gems.sort(Comporators.comparatorByColour);
        gems.forEach(System.out::println);
    }
}
