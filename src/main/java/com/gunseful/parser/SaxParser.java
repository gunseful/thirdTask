package com.gunseful.parser;

import com.gunseful.item.Gem;
import com.gunseful.item.GemsVisualParameters;
import com.gunseful.reflection.ReflectionMethod;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaxParser implements Parser {
    public List<Gem> parse(File file) throws ParserConfigurationException, SAXException, IOException {
        //создаем фаблику и билдер
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser builder = factory.newSAXParser();
        //создаем объект, который будет последовательно идти по XML файлу
        Handler saxParser = new Handler();
        //парсим файл билдером и нэндлером
        builder.parse(file, saxParser);
        return Handler.getGems();
    }

    static class Handler extends DefaultHandler {
        //создаем поле, которое всегда будет указывать элемент в котором мы работаем сейчас
        private String currentElement;
        //создаем список камней
        static List<Gem> gems = new ArrayList<>();
        //создаем геттер для списка камней
        static List<Gem> getGems() {
            return gems;
        }
        //создаем три объекта - камень, визуальные параметры камня и нулевой объект, чтобы присваивать один из двух первый в него и отдавать в рефлекшн
        GemsVisualParameters gemsVisualParameters = new GemsVisualParameters();
        Gem gem = new Gem(gemsVisualParameters);
        Object o = null;
        //создаем объект спец класса который с помощью которого будем искать соответствия и парсить в другие типы
        ReflectionMethod reflectionMethod = new ReflectionMethod();
        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            //записываем в переменную currentElement значение текущего старт элемента
            currentElement = qName;
            //создаем переменную для итерации
            int i=0;
            //проходимся по всем аттрибутам текушего элемента и ищем соответствие имени аттрибута и имен поля в классе gem
            //при нахождении вызываем спец метод pars, чтобы нужные значения полей, в данном случае будет найдено id
            while(attributes.getValue(i)!=null){
                reflectionMethod.pars(attributes.getLocalName(i), gem, attributes.getValue(i));
                i++;
            }
        }
        @Override
        public void characters(char[] ch, int start, int length) throws SAXException {
            //записываем значение внутри тэга в переменную tagData и удаляем разные пробелы
            String tagData = new String(ch, start, length);
            tagData = tagData.replace("\n", "").trim();
            //проверяем и случае необходимости устанавливаем значение объекта о в камень или в визуальные параметры камня, в зависимости от того
            //в каком именно сейчас тэге мы находимся
            o = (currentElement.equals("visual_parameters")) ? gemsVisualParameters : o;
            o = (currentElement.equals("gem")) ? gem : o;
            //указывая текущий тег и информацию из него, а так же объект в котором будем искать необходимые поля, запускаем рефлекшн
            if (!tagData.isEmpty()) {
                reflectionMethod.pars(currentElement, o, tagData);
            }
        }
        @Override
        public void endElement(String uri, String localName, String qName) throws SAXException {
            //если мы выходим из визуальных параметров (после них еще следуют кое какие параметры гема, то снова присваиваем объекту о значение гема
            o = (qName.equals("visual_parameters")) ? gem : o;
            //если мы выходим из гема, то наши объекты уже полностью собраны и готовы к отправке в список камней. Я считаю что каждый камень должен обладать
            //своим id, к тому же таким образом мы исправляем проблему того что корневой элемент вконце тоже закрывается, но еще раз добавлять (поулчается пустой) гем, не нужно
            if (qName.equals("gem") && gem.getId()!=null) {
                gems.add(gem);
                gemsVisualParameters = new GemsVisualParameters();
                gem = new Gem(gemsVisualParameters);
            }
        }
    }
}
