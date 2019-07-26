package com.gunseful.parser;

import com.gunseful.item.Gem;
import com.gunseful.item.GemsVisualParameters;
import com.gunseful.reflection.ReflectionMethod;
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
    public List<Gem> parse(File file) throws ParserConfigurationException, SAXException, IOException, XMLStreamException {
        //создаем список камней
        List<Gem> gems = new ArrayList<>();
        //создаем фабрику и из нее ивентридер XML файлов
        XMLInputFactory factory = XMLInputFactory.newInstance();
        XMLEventReader eventReader = factory.createXMLEventReader(new FileReader(file));
        //создаем необходимые методы, аналогичные в sax парс
        GemsVisualParameters gemsVisualParameters = new GemsVisualParameters();
        Gem gem = new Gem(gemsVisualParameters);
        Object o = null;
        ReflectionMethod reflectionMethods = new ReflectionMethod();
        //создаем цикл, в котором будем двигаться пока тэги не закончатся
        while (eventReader.hasNext()) {
            //присваиваем ивенту значение следующего тега
            XMLEvent xmlEvent = eventReader.nextEvent();
            //проверяем является ли тег открывающим или нет
            if (xmlEvent.isStartElement()) {
                //если он открывающий, создаем объект стартого элемента и с ним уже работаем
                StartElement startElement = xmlEvent.asStartElement();
                startElement.getName().getLocalPart();
                Iterator<Attribute> iterator = startElement.getAttributes();
                //проходитмся по всем аттрибутам
                while (iterator.hasNext()) {
                    Attribute attribute = iterator.next();
                    QName qName = attribute.getName();
                    //вызываем спец метод нашего спец класса, указываем имя атрибута, в каком объекте будем менять поля и значение атрибута
                    reflectionMethods.pars(qName.getLocalPart(), gem, attribute.getValue());
                }
                //будем проходить по тэгам внутри гема, можем наткнуться на визуал параметрс, проверяем находимся ли мы там или нет, если
                //зашли в гем, то указываем что объект о - гем
                //заходим в визуальные параметры, указываем что работаем сейчас с объектом gemsVisualParameters
                o = (String.valueOf(startElement.getName()).equals("gem")) ? gem : o;
                o = (String.valueOf(startElement.getName()).equals("visual_parameters")) ? gemsVisualParameters : o;
                //внутренности тэга записываем в объект чарактеров
                Characters nameDataEvent = (Characters) eventReader.nextEvent();
                //вызываем излюбленный собственный метод рефлекшн+парсер, указываем имя тэга, чо внутри и объект в котором работаем
                reflectionMethods.pars(String.valueOf(startElement.getName()), o, nameDataEvent.getData());
            }

            if (xmlEvent.isEndElement()) {
                //проверяем является ли тэг закрывающим
                EndElement endElement = xmlEvent.asEndElement();
                //если так то указываем что о - gem, если мы уже вышли с визуальных параметров
                o = (endElement.getName().getLocalPart().equals("visual_parameters")) ? gem : o;
                //проверяем является ли этот тэг закрывающим gem и есть ли у него айди, если все ок, добавляем в список гемов и создаем сразу новые объекты
                if ("gem".equalsIgnoreCase(endElement.getName().getLocalPart()) && gem.getId() != null) {
                    gems.add(gem);
                    gemsVisualParameters = new GemsVisualParameters();
                    gem = new Gem(gemsVisualParameters);
                }
            }
        }
        return gems;
    }
}

