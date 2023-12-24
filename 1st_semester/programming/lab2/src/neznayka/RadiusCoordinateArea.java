package neznayka;

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
}
