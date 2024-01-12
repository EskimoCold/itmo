package lab3;

import lab3.cords.Coordinate;
import lab3.cords.RadiusCoordinateArea;
import lab3.interfaces.Talker;
import lab3.interfaces.Thinker;
import lab3.items.Clothes;

import java.util.ArrayList;
import java.util.HashMap;

public class Shorty extends Character implements Thinker, Talker {
    HashMap<Character, String> rated = new HashMap<>();

    public Shorty(Coordinate cords, RadiusCoordinateArea area, String name, ArrayList<Clothes> clothes) {
        super(cords, area, name, clothes);
    }

    public String act(String story) {
        return this.name + " отыграл " + story;
    }

    public HashMap<Character, String> getRated() {
        return rated;
    }

    public void rateOther(Shorty obj, String rating) {
        obj.getRated().put(this, rating);
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
