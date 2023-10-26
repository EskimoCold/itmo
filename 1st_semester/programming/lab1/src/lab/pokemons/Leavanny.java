package lab.pokemons;

import ru.ifmo.se.pokemon.*;
import lab.attacks.*;

public class Leavanny extends Swadloon{
    public Leavanny(String name, int level) {
        super(name, level);
        super.setStats(75, 103, 80, 70, 80, 92);

        Tackle Tackle = new Tackle();

        super.addMove(Tackle);
    }

}
