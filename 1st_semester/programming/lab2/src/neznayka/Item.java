package neznayka;

public abstract class Item {
    String name;
    Coordinate cords;

    public Item(String name, Coordinate cords) {
        this.name = name;
        this.cords = cords;
    }

    public double[] getCoordinates() {
        return this.cords.getCoordinates();
    }

    public abstract String interact(Character obj);

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.name;
        return stringToHash.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (this.getClass() != o.getClass() || o == null){
            return false;
        }

        Item m = (Item) o;

        return m.cords == this.cords && m.name == this.name;
    }

}
