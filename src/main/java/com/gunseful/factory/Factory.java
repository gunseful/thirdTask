package com.gunseful.factory;

import com.gunseful.parser.DomParser;
import com.gunseful.parser.Parser;
import com.gunseful.parser.SaxParser;
import com.gunseful.parser.StaxParser;

public class Factory {
    private static Factory instance = null;
    private Factory(){}
    public static Factory getInstance(){
        if(instance == null){
            instance = new Factory();
        }
        return instance;
    }
        public Parser chooseParser(String inputos) {
            Parser parser = null;
            if (inputos.equals("dom")) {
                parser = new DomParser();
            } else if (inputos.equals("sax")) {
                parser = new SaxParser();
            } else if (inputos.equals("stax")) {
                parser = new StaxParser();
        }
            return parser;
    }
}
