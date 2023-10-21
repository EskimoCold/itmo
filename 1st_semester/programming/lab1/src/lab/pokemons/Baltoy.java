package lab.pokemons;

import ru.ifmo.se.pokemon.*;
import lab.attacks.*;

public class Baltoy extends Pokemon{
    public Baltoy(String name, int level) {
        super(name, level);
        super.setStats(40, 40, 55, 40, 70, 55);
        super.setType(Type.GROUND, Type.PSYCHIC);
        super.setMove(new Facade(), new Psybeam(), new ShadowBall());
    }

}
