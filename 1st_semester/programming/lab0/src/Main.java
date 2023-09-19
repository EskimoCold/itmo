import java.lang.Math;
import java.util.Random;

public class Main {
    static double firstFormula(double x) {
        return Math.tan(Math.sin(Math.pow(x, (x / (x + 0.75f)))));
    }

    static double secondFormula(double x) {
        return Math.sin((Math.pow(x, x / (x - 0.5))) / 2);
    }

    static double thirdFormula(double x) {
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

        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < x.length; j++) {
                // main code here


            }
        }
    }
}

// talk with 3 people; cant now lets at end; picasso ...
// have a bf? no; negative exp in company;
// still practise philisophy? i still think a lot; he got a job
// what animal? cat(good life); didnt get a job, stupid question