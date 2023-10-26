package lab.pokemons;

import ru.ifmo.se.pokemon.*;
import lab.attacks.*;

public class Claydol extends Baltoy{
    public Claydol(String name, int level) {
        super(name, level);
        super.setStats(60, 70, 105, 70, 120, 75);

        StoneEdge StoneEdge = new StoneEdge();

        super.addMove(StoneEdge);
    }

}
