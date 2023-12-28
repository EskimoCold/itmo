package neznayka;

public class Sink extends Item {
    public Sink(String name, Coordinate cords, RadiusCoordinateArea area) {
        super(name, cords, area);
    }

    @Override
    public String interacted(Character obj) {
        if (this.area.compareWithCoordinate(obj.getCords())) {
            return obj.name + " умылся";
        } else {
            return "Слишком далеко от раковины чтобы умыться";
        }
    }
}
