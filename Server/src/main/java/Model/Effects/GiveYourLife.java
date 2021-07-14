package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class GiveYourLife implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757678L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "GiveYourLife";
    }

    @Override
    public String getEffectDescription() {
        return "When a monster(s) would be Summoned, OR when a Spell/Trap Card, or monster ";
    }
}
