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
        public Parser chooseParser(String type) {
            Parser parser = null;
            switch (type){
                case "dom": return parser = new DomParser();
                case "sax": return parser = new SaxParser();
                case "stax": return parser = new StaxParser();
            }
            return null;
    }
}
