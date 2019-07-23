package com.gunseful.factory;

import com.gunseful.parser.DomParser;
import com.gunseful.parser.Parser;
import com.gunseful.parser.SaxParser;
import com.gunseful.parser.StaxParser;

public class Factory {

    private Factory(){}
    public static Factory getInstance(){
        return LazySomethingHolder.singletonInstance ;
    }
    private static class LazySomethingHolder {
        public static Factory singletonInstance = new Factory();
    }
        public Parser chooseParser(Class<? extends Parser> type) {
            Parser parser = null;
            if (DomParser.class.equals(type)) {
                return parser = new DomParser();
            } else if (SaxParser.class.equals(type)) {
                return parser = new SaxParser();
            } else if (StaxParser.class.equals(type)) {
                return parser = new StaxParser();
            }
            return null;
    }
}
