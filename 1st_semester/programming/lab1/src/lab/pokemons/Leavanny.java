package lab.pokemons;

import ru.ifmo.se.pokemon.*;
import lab.attacks.*;

public class Leavanny extends Pokemon{
    public Leavanny(String name, int level) {
        super(name, level);
        super.setStats(75, 103, 80, 70, 80, 92);
        super.setType(Type.BUG, Type.GRASS);
        super.setMove(new Tackle(), new Tackle(), new CalmMind(), new GrassWhistle());
    }

}
