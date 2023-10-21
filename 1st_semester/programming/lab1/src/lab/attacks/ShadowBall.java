package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class ShadowBall extends SpecialMove{
    public ShadowBall() {
        super(Type.GHOST, 80, 100);
    }

    @Override
    protected void applyOppDamage(Pokemon opponent, double damage) {
        opponent.setMod(Stat.HP, (int) damage);
    }

    @Override
    protected void applyOppEffects(Pokemon opponent){
        if (Math.random() <= 0.2){
            opponent.setMod(Stat.SPECIAL_DEFENSE, -1);
        }
    }

    @Override
    protected String describe() {
        return "использует Shadow Ball";
    }
}
