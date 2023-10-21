package lab.pokemons;

import ru.ifmo.se.pokemon.*;
import lab.attacks.*;

public class Sewaddle extends Pokemon{
    public Sewaddle(String name, int level) {
        super(name, level);
        super.setStats(45, 53, 70, 40, 60, 42);
        super.setType(Type.BUG, Type.GRASS);
        super.setMove(new Tackle(), new CalmMind());
    }

}
