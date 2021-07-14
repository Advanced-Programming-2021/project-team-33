package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class DestroySpecificSpellCard implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757675L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "DestroySpecificSpellCard";
    }

    @Override
    public String getEffectDescription() {
        return "When a Spell Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.";
    }
}
