package lab.pokemons;

import ru.ifmo.se.pokemon.*;
import lab.attacks.*;

public class Claydol extends Pokemon{
    public Claydol(String name, int level) {
        super(name, level);
        super.setStats(60, 70, 105, 70, 120, 75);
        super.setType(Type.GROUND, Type.PSYCHIC);
        super.setMove(new Facade(), new Psybeam(), new ShadowBall(), new StoneEdge());
    }

}
