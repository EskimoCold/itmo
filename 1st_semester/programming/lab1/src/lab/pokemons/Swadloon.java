package lab.pokemons;

import ru.ifmo.se.pokemon.*;
import lab.attacks.*;

public class Swadloon extends Pokemon{
    public Swadloon(String name, int level) {
        super(name, level);
        super.setStats(55, 63, 90, 50, 80, 42);
        super.setType(Type.BUG, Type.GRASS);
        super.setMove(new Tackle(), new CalmMind(), new GrassWhistle());
    }

}
