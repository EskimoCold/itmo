package neznayka;

import java.util.Objects;

public abstract class Item {
    String name;
    Coordinate cords;
    RadiusCoordinateArea area;

    public Item(String name, Coordinate cords, RadiusCoordinateArea area) {
        this.name = name;
        this.cords = cords;
        this.area = area;
    }

    public double[] getCoordinates() {
        return this.cords.getCoordinates();
    }

    public abstract String interacted(Character obj);

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.name;
        return stringToHash.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()){
            return false;
        }

        Item m = (Item) o;

        return this.cords.equals(m.cords) && Objects.equals(m.name, this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
