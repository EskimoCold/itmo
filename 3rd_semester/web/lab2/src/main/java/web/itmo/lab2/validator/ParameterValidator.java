package web.itmo.lab2.validator;

import java.util.Arrays;
import java.util.HashSet;

public class ParameterValidator {
    private static final HashSet<Double> VALID_X = new HashSet<>(Arrays.asList(-2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0));

    public static ValidParameter checkStrings(String x,
                                              String y,
                                              String R) {
        try {
            Float.parseFloat(x);
        } catch (NumberFormatException | NullPointerException e) {
            return new ValidParameter("X value is not valid", false);
        }

        try {
            Float.parseFloat(y);
        } catch (NumberFormatException | NullPointerException e) {
            return new ValidParameter("Y value is not valid", false);
        }

        try {
            Float.parseFloat(R);
        } catch (NumberFormatException | NullPointerException e) {
            return new ValidParameter("R value is not valid", false);
        }

        return new ValidParameter(null, true);
    }

    public static ValidParameter checkRanges(float x,
                                             float y,
                                             float R) {
        if (!VALID_X.contains((double) x)) {
            return new ValidParameter("X must be float in (-2.0, -1.5, -1.0, -0.5, 0.0, 0.5, 1.0, 1.5, 2.0)", false);
        }

        if (!((y >= -3) && (y <= 5))) {
            return new ValidParameter("Y must be float in range (-3; 5)", false);
        }

        if (!((R >= 1) && (R <= 4))) {
            return new ValidParameter("R must be float in range (1, 4)", false);
        }

        return new ValidParameter(null, true);
    }
}