package lab3.items;

import lab3.Character;
import lab3.cords.Coordinate;
import lab3.cords.RadiusCoordinateArea;
import lab3.exceptions.NotInRadiusException;
import lab3.interfaces.CanBeThrown;

public class Cup extends Item implements CanBeThrown {
    public Cup(String name, Coordinate cords, RadiusCoordinateArea area) {
        super(name, cords, area);
    }

    @Override
    public String interacted(Character obj) {
        if (!area.compareWithCoordinate(obj.getCords())) {
            throw new NotInRadiusException("Слишком далеко от объекта, чтобы воспользоваться им");
        }
        return  "Воспользовался кружкой";
    }
}
