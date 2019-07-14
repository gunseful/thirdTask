import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Main {
    private static ArrayList<Gem> gems = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SaxParser saxParser = new SaxParser();
        gems.addAll(saxParser.parse("src/gem.xml"));

        for (Gem gem : gems){
            System.out.println(gem.toString());
        }

        Comparator<Gem> comparator = new Comparator<Gem>(){
            public int compare(Gem o1, Gem o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };

        Collections.sort(gems, comparator);
        System.out.println("\n");
        for (Gem gem : gems){
            System.out.println(gem.toString());
        }
    }
}
