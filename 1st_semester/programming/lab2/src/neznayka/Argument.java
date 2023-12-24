package neznayka;

import java.util.Random;

public class Argument {
    int power;
    String about;
    private Random randomizer = new Random();

    public Argument(String about) {
        this.about = about;
        this.power = (int) Math.round(this.randomizer.nextDouble()*100);
    }

    public String getAbout() {
        return about;
    }

    public int getPower() {
        return this.power;
    }
}
