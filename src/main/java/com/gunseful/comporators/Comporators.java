package com.gunseful.comporators;

import com.gunseful.item.Gem;

import java.util.Comparator;

public class Comporators {
    public static final Comparator<Gem> comparatorById = new Comparator<Gem>() {
        @Override
        public int compare(Gem o1, Gem o2) {
            return o1.getId().compareTo(o2.getId());
        }
    };

    public static Comparator<Gem> comparatorByName = new Comparator<Gem>(){
        @Override
        public int compare(Gem o1, Gem o2) {
            return o1.getName().compareTo(o2.getName());
        }
    };

    public static Comparator<Gem> comparatorByOrigin = new Comparator<Gem>(){
        @Override
        public int compare(Gem o1, Gem o2) {
            return o1.getOrigin().compareTo(o2.getOrigin());
        }
    };

//    public static Comparator<Gem> comparatorByColour = new Comparator<Gem>(){
//        @Override
//        public int compare(Gem o1, Gem o2) {
//            return o1.getColour().compareTo(o2.getColour());
//        }
//    };
//
//    public static Comparator<Gem> comparatorByTransparency = new Comparator<Gem>(){
//        @Override
//        public int compare(Gem o1, Gem o2) {
//            return o1.getTransparency()-o2.getTransparency();
//        }
//    };
}
