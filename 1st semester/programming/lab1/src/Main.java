import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

public class Main {
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
