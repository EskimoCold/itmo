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

    @Override
    public int hashCode() {
        String stringToHash = this.getClass().getSimpleName() + this.about.hashCode();
        return stringToHash.hashCode();
    }

    @Override
    public boolean equals(Object o){
        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()){
            return false;
        }

        Argument m = (Argument) o;

        return this.about.equals(m.about) && this.power == m.power;
    }

    @Override
    public String toString(){
        return "Аргумент: " + this.about + " с силой " + this.power;
    }
}
