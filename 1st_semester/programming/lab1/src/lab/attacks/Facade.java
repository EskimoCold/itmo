package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class Facade extends PhysicalMove {
    public Facade() {
        super(Type.NORMAL, 70, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon opponent, double damage) {
        Status status = opponent.getCondition();
        if (status.equals(Status.BURN) || status.equals(Status.PARALYZE) || status.equals(Status.POISON)) {
            opponent.setMod(Stat.HP, (int) damage * 2);
        } else {
            opponent.setMod(Stat.HP, (int) damage);
        }
    }

    @Override
    protected String describe() {
        return "использует Facade";
    }
}
