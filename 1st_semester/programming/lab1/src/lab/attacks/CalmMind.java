package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class CalmMind extends StatusMove {
    public CalmMind() {
        super(Type.PSYCHIC, 0, 0);
    }

    @Override
    protected void applySelfEffects(Pokemon pokemon) {
        pokemon.addEffect(new Effect().stat(Stat.SPECIAL_ATTACK, +1));
        pokemon.addEffect(new Effect().stat(Stat.SPECIAL_DEFENSE, +1));
    }

    @Override
    protected String describe() {
        return "использует Calm Mind";
    }
}
