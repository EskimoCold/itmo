package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class Psybeam extends SpecialMove {
    public Psybeam() {
        super(Type.PSYCHIC, 65, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon opponent, double damage) {
        opponent.setMod(Stat.HP, (int) damage);
    }

    @Override
    protected void applyOppEffects(Pokemon opponent){
        if (Math.random() <= 0.1){
            Effect.confuse(opponent);
        }
    }

    @Override
    protected String describe() {
        return "использует Psybeam";
    }
}
