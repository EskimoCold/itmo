package web.itmo.lab2.checker;

public class HitChecker {
    public static boolean checkHit(float x, float y, float R) {
        if (x >= -R / 2 && x <= 0 && y <= 0 && y >= -R) {
            return true;
        }

        if (x >= 0 && y >= 0) {
            if (y <= -x + R) {
                return true;
            }
        }

        if (x <= 0 && y <= 0) {
            if (Math.pow(x, 2) + Math.pow(y, 2) <= Math.pow(R / 2, 2)) {
                return true;
            }
        }

        return false;
    }
}
