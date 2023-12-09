package neznayka;

import java.util.Objects;

public abstract class Item {
    String name;
    Coordinate cords;

    public Item(String name, Coordinate cords) {
        this.name = name;
        this.cords = cords;
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

        return m.cords == this.cords && Objects.equals(m.name, this.name);
    }

    @Override
    public String toString() {
        return this.name;
    }

}
