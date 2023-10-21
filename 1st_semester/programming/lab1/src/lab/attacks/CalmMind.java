package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class CalmMind extends StatusMove {
    public CalmMind() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        Effect effect = new Effect().stat(Stat.SPECIAL_ATTACK, +1);
        Effect effect_2 = new Effect().stat(Stat.SPECIAL_DEFENSE, +1);

        pokemon.addEffect(effect);
        pokemon.addEffect(effect_2);
    }

    @Override
    protected String describe() {
        return "использует Calm Mind";
    }
}
