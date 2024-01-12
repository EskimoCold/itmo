package lab3.items;

import lab3.Character;
import lab3.cords.Coordinate;
import lab3.cords.RadiusCoordinateArea;
import lab3.interfaces.CanBeThrown;

public class Spoon extends Item implements CanBeThrown {
    public Spoon(String name, Coordinate cords, RadiusCoordinateArea area) {
        super(name, cords, area);
    }

    @Override
    public String interacted(Character obj) {
        return  "Воспользовался ложкой";
    }
}