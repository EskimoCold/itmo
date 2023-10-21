package lab.pokemons;

import ru.ifmo.se.pokemon.*;
import lab.attacks.*;

public class Tornadus extends Pokemon {
    public Tornadus(String name, int level) {
        super(name, level);
        super.setStats(79, 115, 70, 125, 80, 111);
        super.setType(Type.FLYING);
        super.setMove(new Bite(), new Facade(), new AirSlash(), new Extrasensory());
    }
}
