package neznayka;

public class Coordinate {
    double X;
    double Y;

    public Coordinate(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    public double[] getCoordinates() {
        double[] cords = {this.X, this.Y};
        return cords;
    }

    public void  setCoordinates(double X, double Y) {
        this.X = X;
        this.Y = Y;
    }

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.X + this.Y;
        return stringToHash.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (this.getClass() != o.getClass() || o == null){
            return false;
        }

        Coordinate m = (Coordinate) o;

        return m.X == this.X && m.Y == this.Y;
    }

    @Override
    public String toString() {
        return this.getCoordinates().toString();
    }
}
