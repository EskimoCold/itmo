package neznayka;

public class Book extends Item {
    String about;
    public Book(String name, Coordinate cords, RadiusCoordinateArea area, String about) {
        super(name, cords, area);
        this.about = about;
    }

    @Override
    public String interacted(Character obj) {
        if (this.area.compareWithCoordinate(obj.getCords())) {
            return obj.name + " начитался книжек";
        } else {
            return "Слишком далеко от книжки чтобы почитать книжку";
        }
    }

    @Override
    public String toString() {
        return "Книжка о: " + this.about;
    }
}
