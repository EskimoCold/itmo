package web;

public class Calculator {
    public static boolean calculatePoint(double x, double y, double r) {
        if (x >= -r / 2 && x <= 0 && y <= 0 && y >= -r) {
            return true;
        }

        if (x >= 0 && y >= 0) {
            if (y <= -x + r) {
                return true;
            }
        }

        if (x <= 0 && y <= 0) {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(r / 2, 2)) {
                return true;
            }
        }

        return false;
    }
}
