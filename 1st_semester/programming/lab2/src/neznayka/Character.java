package neznayka;

import neznayka.enums.MentalState;
import neznayka.enums.Planet;

import java.util.ArrayList;
import java.util.Objects;

public abstract class Character {
    Coordinate cords;
    RadiusCoordinateArea area;
    String name;
    MentalState mental;
    Planet planet;
    ArrayList<Clothes> clothes;

    public Character(Coordinate cords, RadiusCoordinateArea area, String name, MentalState mental, Planet planet, ArrayList<Clothes> clothes) {
        this.cords = cords;
        this.area = area;
        this.name = name;
        this.mental = mental;
        this.planet = planet;
        this.clothes = clothes;
    }

    public void move(double X, double Y) {
        this.cords.setCoordinates(X, Y);
    }

    public boolean isWearingClothes() {
        return !this.clothes.isEmpty();
    }

    public Planet getPlanet() {
        return planet;
    }

    public void setPlanet(Planet planet) {
        this.planet = planet;
    }

    public Coordinate getCords() {
        return this.cords;
    }

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
        if (this.isWearingClothes()) {
            return this.name;
        } else {
            return this.name + "(без одежды)";
        }
    }

}
