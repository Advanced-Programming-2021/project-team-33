package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class IncreaseLPForEverySpell implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757660L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "IncreaseLPForEverySpell";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
