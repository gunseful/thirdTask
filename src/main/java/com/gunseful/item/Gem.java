package com.gunseful.item;

public class Gem {
    private String id;
    private String name;
    private boolean precious;
    private String origin;
    private double price;
    private int value;
    GemsVisualParameters gemsVisualParameters;

    @Override
    public String toString() {
        return "Gem{" +
                id+
                ", Name = " + name +
                ", Precious = " + precious +
                ", Origin = " + origin +
                ", Price = "  + price +
                "$, Value = " + value +
                ", Visual Parameters: " + gemsVisualParameters;
    }

    public GemsVisualParameters getGemsVisualParameters() {
        return gemsVisualParameters;
    }

    public Gem(String id, String name, boolean precious, String origin, double price, int value, GemsVisualParameters gemsVisualParameters) {
        this.id = id;
        this.name = name;
        this.precious = precious;
        this.origin = origin;
        this.price = price;
        this.value = value;
        this.gemsVisualParameters = gemsVisualParameters;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPrecious() {
        return precious;
    }

    public void setPrecious(boolean precious) {
        this.precious = precious;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }


}
