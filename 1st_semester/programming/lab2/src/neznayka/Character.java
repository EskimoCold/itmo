package neznayka;

import java.util.ArrayList;

public abstract class Character {
    Coordinate cords;
    String name;
    MentalState mental;
    ArrayList clothes;

    public Character(Coordinate cords, String name, MentalState mental, ArrayList clothes) {
        this.cords = cords;
        this.name = name;
        this.mental = mental;
        this.clothes = clothes;
    }

    public void move(double X, double Y) {
        this.cords.setCoordinates(X, Y);
    }

    public boolean isWearingClothes() {
        return this.clothes.size() > 0;
    }

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.name;
        return stringToHash.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (this.getClass() != o.getClass() || o == null){
            return false;
        }

        Item m = (Item) o;

        return m.cords == this.cords && m.name == this.name;
    }

    @Override
    public String toString() {
        if (this.isWearingClothes()) {
            return this.name;
        } else {
            return this.name + "(без одежды)";
        }
    }

}
