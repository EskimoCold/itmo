package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class StoneEdge extends PhysicalMove{
    public StoneEdge() {
        super(Type.ROCK, 100, 80);
    }

    @Override
    protected void applyOppDamage(Pokemon opponent, double damage) {
        opponent.setMod(Stat.HP, (int) damage);
    }

    @Override
    protected String describe() {
        return "использует Stone Edge";
    }
}
