package com.gunseful.parser;

import com.gunseful.item.Gem;
import com.gunseful.item.GemsVisualParameters;
import com.gunseful.reflection.ReflectionMethod;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DomParser implements Parser {
    @Override
    public List<Gem> parse(File file) throws ParserConfigurationException, SAXException, IOException {
        //создаем список всех камней
        List<Gem> gems = new ArrayList<>();
        //создаем фабрику, билдер и парсим XML файл, получая document, с которым дальше будем работать
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);
        //получаем список нодов(подтэгов) внутри тэгов gem и visual_parameters
        NodeList gemElements = document.getDocumentElement().getElementsByTagName("gem");
        NodeList gemVisualParametersElements = document.getDocumentElement().getElementsByTagName("visual_parameters");
        //создаем объекты камень и визуальные параметры камня, так же создаем спец класс чтобы юзать рефлекшн
        ReflectionMethod reflectionMethods = new ReflectionMethod();
        GemsVisualParameters gemsVisualParameters = new GemsVisualParameters();
        Gem gemSource = new Gem(gemsVisualParameters);
        //заходим в цикл, первое - берем гем, второе - берем первое дочернее его значение. Итак проходим все
        for (int i = 0; i < gemElements.getLength(); i++) {
            for (int k = 0; k < gemElements.item(i).getChildNodes().getLength(); k++) {
                //Берем тэг gem и среди дочерних ищем все совпадения тегов и полей класса Gem, при совпадении добавляем значение из XML в gem
                Node nodeGem = gemElements.item(i).getChildNodes().item(k);
                reflectionMethods.pars(nodeGem.getNodeName(), gemSource, nodeGem.getTextContent());
                //Ищем совпадение полей и аттрибутов -> находим id и добавляем значение из XML в gem.id;
                try {
                    reflectionMethods.pars(gemElements.item(i).getAttributes().item(k).getNodeName(), gemSource, gemElements.item(i).getAttributes().item(k).getTextContent());
                } catch (Exception ignored) {}
                //Берем visual_parameters, ищем сравнение тега и поля класса GemsVisualParameters, при совпадении добавляем значение из XML в gemsVisualParameters
                if (k < gemVisualParametersElements.item(i).getChildNodes().getLength()) {
                    Node nodeVisualParameters = gemVisualParametersElements.item(i).getChildNodes().item(k);
                    reflectionMethods.pars(nodeVisualParameters.getNodeName(), gemsVisualParameters, nodeVisualParameters.getTextContent());
                }
            }
                //добавляем собранный гем в список всех гемов
                gems.add(gemSource);
                //создаем новые объекты
                gemsVisualParameters = new GemsVisualParameters();
                gemSource = new Gem(gemsVisualParameters);
        }
        //когда пройдем все тэги, возвращаем список камней
        return gems;
    }
}
