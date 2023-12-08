package neznayka;

import java.util.ArrayList;

public class Shorty extends Character implements Thinker, Talker, Actor {

    public Shorty(Coordinate cords, String name, MentalState mental, ArrayList clothes) {
        super(cords, name, mental, clothes);
    }

    @Override
    public String performAction(String action) {
        return Actor.super.performAction(action);
    }
}
