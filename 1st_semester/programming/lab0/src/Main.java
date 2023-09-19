import java.lang.Math;
import java.util.Random;

public class Main {
    static double firstFormula(float x) {
        return Math.tan(Math.sin(Math.pow(x, (x / (x + 0.75f)))));
    }

    static double secondFormula(float x) {
        return Math.sin((Math.pow(x, x / (x - 0.5))) / 2);
    }

    static double thirdFormula(float x) {
        return Math.cos(Math.atan(Math.pow((x - 4.5) / 17, 4)));
    }

    public static void main(String[] args) {
        short[] c = new short[6];
        float[] x = new float[14];
        float[][] res = new float[6][14];

        Random rand = new Random();

        byte el = 16;
        for (int i = 0; i < c.length; i++) {
            c[i] = el;
            el -= 2;
        }

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
                System.out.printf("%.5f ", res[i][j]);
            }

            System.out.println();
        }
    }
}
