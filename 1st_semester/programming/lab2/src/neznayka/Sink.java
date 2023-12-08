package neznayka;

public class Sink extends Item {
    public Sink(String name, Coordinate cords) {
        super(name, cords);
    }

    @Override
    public String interacted(Character obj) {
        if (this.cords.equals(obj.cords)) {
            return obj.name + " умылся";
        } else {
            return "Слишком далеко от раковины чтобы умыться";
        }
    }
}
