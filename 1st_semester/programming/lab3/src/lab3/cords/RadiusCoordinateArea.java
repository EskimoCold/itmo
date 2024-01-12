package lab3.cords;

public class RadiusCoordinateArea {
    double radius;
    public Coordinate coordinate;

    public RadiusCoordinateArea(double radius, Coordinate coordinate) {
        this.radius = radius;
        this.coordinate = coordinate;
    }

    public boolean compareWithCoordinate(Coordinate otherCoordinate) {
        double diffX = this.coordinate.getCoordinates()[0] - otherCoordinate.getCoordinates()[0];
        double diffY = this.coordinate.getCoordinates()[1] - otherCoordinate.getCoordinates()[1];

        return Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2)) <= this.radius;
    }

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.coordinate.hashCode();
        return stringToHash.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()) {
            return false;
        }

        RadiusCoordinateArea m = (RadiusCoordinateArea) o;

        return m.coordinate.equals(this.coordinate) && m.radius == this.radius;
    }

    @Override
    public String toString() {
        return this.coordinate.getCoordinates().toString() + ' ' + this.radius;
    }
}
