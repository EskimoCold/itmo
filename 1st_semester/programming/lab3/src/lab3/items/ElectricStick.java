package lab3.items;

import lab3.Character;
import lab3.cords.Coordinate;
import lab3.cords.RadiusCoordinateArea;

public class ElectricStick extends Stick {

    public ElectricStick(String name, Coordinate cords, RadiusCoordinateArea area) {
        super(name, cords, area);
    }

    @Override
    public String interacted(Character obj) {
        return  obj.getName() + " воспользовался электрической дубинкой.";
    }
}
