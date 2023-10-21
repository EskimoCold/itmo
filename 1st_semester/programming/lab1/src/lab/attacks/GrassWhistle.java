package lab.attacks;

import ru.ifmo.se.pokemon.*;

public class GrassWhistle extends StatusMove {
    public GrassWhistle() {
        super(Type.GRASS, 0, 55);
    }

    @Override
    protected void applyOppEffects(Pokemon opponent){
        if (Math.random() <= 0.55){
            Effect effect = (new Effect()).condition(Status.SLEEP);
            opponent.setCondition(effect);
        }
    }

    @Override
    protected String describe(){
        return "использует Grass Whistle";
    }
}
