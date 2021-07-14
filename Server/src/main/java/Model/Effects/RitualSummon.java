package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class RitualSummon implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757663L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "RitualSummon";
    }

    @Override
    public String getEffectDescription() {
        return "This card can be used to Ritual Summon any 1 Ritual Monster.";
    }
}
