package com.gunseful.item;

public class GemsVisualParameters {

    private String colour;
    private int transparency;
    private int faceting;

    public GemsVisualParameters(String colour, int transparency, int faceting) {
        this.colour = colour;
        this.transparency = transparency;
        this.faceting = faceting;
    }

    public String getColour() {
        return colour;
    }

    public int getTransparency() {
        return transparency;
    }

    public int getFaceting() {
        return faceting;
    }

    @Override
    public String toString() {
        return "Colour = " + colour +
                ", Transparency=" + transparency +
                ", Faceting=" + faceting;
    }


}
