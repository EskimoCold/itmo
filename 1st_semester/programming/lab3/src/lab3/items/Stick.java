package lab3.items;

import lab3.Character;
import lab3.cords.Coordinate;
import lab3.cords.RadiusCoordinateArea;
import lab3.exceptions.NotInRadiusException;

public class Stick extends Item {
    public Stick(String name, Coordinate cords, RadiusCoordinateArea area) {
        super(name, cords, area);
    }

    @Override
    public String interacted(Character obj) {
        if (!area.compareWithCoordinate(obj.getCords())) {
            throw new NotInRadiusException("Слишком далеко от объекта, чтобы воспользоваться им");
        }
        return  obj.getName() + " воспользовался дубинкой.";
    }
}
