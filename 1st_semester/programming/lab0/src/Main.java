import static java.lang.Math.*;
import java.util.Random;

public class Main {
    public static double firstFormula(float x) {
        return tan(sin(pow(x, (x / (x + 0.75f)))));
    }

    public static double secondFormula(float x) {
        return sin((pow(x, x / (x - 0.5))) / 2);
    }

    public static double thirdFormula(float x) {
        return cos(atan(pow((x - 4.5) / 17, 4)));
    }

    public static void main(String[] args) {
        short[] c = new short[6];
        float[] x = new float[14];
        float[][] res = new float[6][14];

        Random rand = new Random();

        // make c array
        byte el = 16;
        for (int i = 0; i < c.length; i++) {
            c[i] = el;
            el -= 2;
        }

        // make x array
        for (int i = 0; i < x.length; i++) {
            x[i] = rand.nextFloat()*17 - 13;
        }

        // calculate main array
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < x.length; j++) {
                if (c[i] == 16) {
                    res[i][j] = (float) firstFormula(x[j]);
                }

                else if (c[i] == 6 || c[i] == 10 || c[i] == 14) {
                    res[i][j] = (float) secondFormula(x[j]);
                }

                else {
                    res[i][j] = (float) thirdFormula(x[j]);
                }
            }
        }

        // print array
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < x.length; j++) {
                System.out.printf("%10.5f", res[i][j]);
            }

            System.out.println();
        }
    }
}
