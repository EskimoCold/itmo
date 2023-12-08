package neznayka;

import java.util.ArrayList;

public class Shorty extends Character implements Thinker, Talker, Actor {

    public Shorty(Coordinate cords, String name, MentalState mental, ArrayList clothes) {
        super(cords, name, mental, clothes);
    }

    public String act(String story) {
        return this.name + " отыграл " + story;
    }

    public String getMental() {
        if (this.mental == MentalState.SANE) {
            return "Нормальный";
        }

        if (this.mental == MentalState.INSANE) {
            return "Сумашедший";
        }

        return null;
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
