package lab3.fight;

import java.util.Random;

public class FightImpact {
    int power;
    String about;

    public FightImpact(String about, int damage) {
        this.about = about;
        this.power = damage;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (this.getClass() != o.getClass()){
            return false;
        }

        FightImpact m = (FightImpact) o;

        return this.about.equals(m.about) && this.power == m.power;
    }

    @Override
    public String toString(){
        return "Аргумент: " + this.about + " с силой " + this.power;
    }
}
