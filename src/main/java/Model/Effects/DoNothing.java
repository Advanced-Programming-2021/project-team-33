package Model.Effects;

import Model.Card;
import Model.Effect;

public class DoNothing implements Effect {
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "DoNothing";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
