package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class Extrasensory extends SpecialMove {
    public Extrasensory() {
        super(Type.PSYCHIC, 80, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon opponent, double damage) {
        opponent.setMod(Stat.HP, (int) damage);
    }

    @Override
    protected void applyOppEffects(Pokemon opponent){
        if (Math.random() <= 0.1){
            Effect.flinch(opponent);
        }
    }

    @Override
    protected String describe() {
        return "использует Extrasensory";
    }
}
