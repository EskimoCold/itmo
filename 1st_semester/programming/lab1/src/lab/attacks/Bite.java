package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class Bite extends PhysicalMove {
    public Bite() {
        super(Type.DARK, 60, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon opponent, double damage) {
        opponent.setMod(Stat.HP, (int) damage);
    }

    @Override
    protected void applyOppEffects(Pokemon opponent){
        if (Math.random() <= 0.3){
            Effect.flinch(opponent);
        }
    }

    @Override
    protected String describe() {
        return "использует Bite";
    }

}
