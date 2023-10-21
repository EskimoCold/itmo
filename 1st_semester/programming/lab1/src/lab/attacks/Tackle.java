package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class Tackle extends PhysicalMove {
    public Tackle() {
        super(Type.NORMAL, 40, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon opponent, double damage) {
        opponent.setMod(Stat.HP, (int) damage);
    }

    @Override
    protected String describe() {
        return "использует Tackle";
    }
}
