package pl.gda.pg.eti.kask.javaee.jsf.entities;

public enum MagicElement {
    FIRE(0, "Ogień"), WATER(1, "Woda"), WIND(2, "Wiatr"), EARTH(3, "Ziemia");

    public int id;
    public String name;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    MagicElement(int id, String name) {
        this.id = id;
        this.name = name;
    }
}
