package lab.pokemons;

import ru.ifmo.se.pokemon.*;
import lab.attacks.*;

public class Swadloon extends Sewaddle{
    public Swadloon(String name, int level) {
        super(name, level);
        super.setStats(55, 63, 90, 50, 80, 42);

        GrassWhistle GrassWhistle = new GrassWhistle();

        addMove(GrassWhistle);
    }

}
