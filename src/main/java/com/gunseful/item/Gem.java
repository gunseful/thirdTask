package com.gunseful.item;

import java.util.Objects;

public class Gem {
    public String id;
    private String name;
    private boolean preciousness;
    private String origin;
    private double price;
    private int value;
    private GemsVisualParameters gemsVisualParameters;

    public Gem(GemsVisualParameters gemsVisualParameters) {
        this.gemsVisualParameters = gemsVisualParameters;
    }

    public void setGemsVisualParameters(GemsVisualParameters gemsVisualParameters) {
        this.gemsVisualParameters = gemsVisualParameters;
    }

    public Gem(String id, String name, boolean precious, String origin, double price, int value, GemsVisualParameters gemsVisualParameters) {
        this.id = id;
        this.name = name;
        this.preciousness = precious;
        this.origin = origin;
        this.price = price;
        this.value = value;
        this.gemsVisualParameters = gemsVisualParameters;
    }

    public GemsVisualParameters getGemsVisualParameters() {
        return gemsVisualParameters;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isPreciousness() {
        return preciousness;
    }

    public void setPreciousness(boolean preciousness) {
        this.preciousness = preciousness;
    }

    public boolean getPrecious() {
        return preciousness;
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

    @Override
    public String toString() {
        return "Gem - " +
                id+
                ", Name = " + name +
                ", Precious = " + preciousness +
                ", Origin = " + origin +
                ", Price = "  + price +
                "$, Value = " + value +
                ", Visual Parameters: " + gemsVisualParameters;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gem gem = (Gem) o;
        return preciousness == gem.preciousness &&
                Double.compare(gem.price, price) == 0 &&
                value == gem.value &&
                Objects.equals(id, gem.id) &&
                Objects.equals(name, gem.name) &&
                Objects.equals(origin, gem.origin) &&
                Objects.equals(gemsVisualParameters, gem.gemsVisualParameters);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, preciousness, origin, price, value, gemsVisualParameters);
    }
}

