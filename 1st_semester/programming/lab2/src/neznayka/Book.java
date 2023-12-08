package neznayka;

public class Book extends Item {
    public Book(String name, Coordinate cords) {
        super(name, cords);
    }

    @Override
    public String interact(Character obj) {
        return obj.name + " начитался книжек";
    }
}
