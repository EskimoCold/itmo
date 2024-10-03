package web.network;

public class Request {
    public double x;
    public double y;
    public double r;

    public Request(double x, double y, double r) {
        this.x = x;
        this.y = y;
        this.r = r;
    }

    @Override
    public String toString() {
        return "network.Request{" +
                "x=" + x +
                ", y=" + y +
                ", r=" + r +
                '}';
    }
}
