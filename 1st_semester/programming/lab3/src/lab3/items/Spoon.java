package lab3.items;

import lab3.Character;
import lab3.cords.Coordinate;
import lab3.cords.RadiusCoordinateArea;
import lab3.exceptions.CustomUncheckedException;
import lab3.interfaces.CanBeThrown;

public class Spoon extends Item implements CanBeThrown {
    public Spoon(String name, Coordinate cords, RadiusCoordinateArea area) {
        super(name, cords, area);
    }

    @Override
    public String interacted(Character obj) {
        if (!area.compareWithCoordinate(obj.getCords())) {
            throw new CustomUncheckedException("Слишком далеко от объекта, чтобы воспользоваться им");
        }
        return  "Воспользовался ложкой";
    }
}
