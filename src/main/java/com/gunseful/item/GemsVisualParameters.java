package com.gunseful.item;

import java.util.Objects;

public class GemsVisualParameters {

    private String colour;
    private int transparency;
    private int faceting;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GemsVisualParameters that = (GemsVisualParameters) o;
        return transparency == that.transparency &&
                faceting == that.faceting &&
                Objects.equals(colour, that.colour);
    }

    @Override
    public int hashCode() {
        return Objects.hash(colour, transparency, faceting);
    }

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
                "%, Faceting=" + faceting;
    }
}
