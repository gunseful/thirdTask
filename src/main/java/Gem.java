public class Gem {
    private String name;
    boolean precious;
    private String origin;
    private String colour;
    private int transparency;
    private int faceting;

    private int value;

    @Override
    public String toString() {
        return "Gem{" +
                "name='" + name + '\'' +
                ", precious=" + precious +
                ", origin='" + origin + '\'' +
                ", colour='" + colour + '\'' +
                ", transparency=" + transparency +
                ", faceting=" + faceting +
                ", value=" + value +
                '}';
    }

    public Gem(String name, boolean precious, String origin, String colour, int transparency, int faceting, int value) {
        this.name = name;
        this.precious = precious;
        this.origin = origin;
        this.colour = colour;
        this.transparency = transparency;
        this.faceting = faceting;
        this.value = value;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public int getTransparency() {
        return transparency;
    }

    public void setTransparency(int transparency) {
        this.transparency = transparency;
    }

    public int getFaceting() {
        return faceting;
    }

    public void setFaceting(int faceting) {
        this.faceting = faceting;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }



}
