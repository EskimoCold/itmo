package neznayka;

import java.util.ArrayList;
import java.util.HashMap;

public class Shorty extends Character implements Thinker, Talker, Actor {
    HashMap<Character, String> rated = new HashMap<>();

    public Shorty(Coordinate cords, String name, MentalState mental, Planet planet, ArrayList clothes) {
        super(cords, name, mental, planet, clothes);
    }

    public String act(String story) {
        return this.name + " отыграл " + story;
    }

    public String getMental() {
        return mental.toString();
    }

    public HashMap getRated() {
        return rated;
    }

    public void rateOther(Shorty obj, String rating) {
        obj.getRated().put(this, rating);
    }

    public void setMental(MentalState mental) {
        this.mental = mental;
    }

    @Override
    public String performAction(String action) {
        return this.name + " сделал: " + action;
    }

    @Override
    public String talk(String phrase) {
        return this.name + " говорит: " + phrase;
    }

    @Override
    public String think(String thought) {
        return this.name + " подумал: " + thought;
    }
}
