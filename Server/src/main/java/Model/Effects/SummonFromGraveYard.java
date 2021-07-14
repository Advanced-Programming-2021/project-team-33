package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;


public class SummonFromGraveYard implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757665L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "SummonFromGraveYard";
    }

    @Override
    public String getEffectDescription() {
        return "Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position.";
    }
}
