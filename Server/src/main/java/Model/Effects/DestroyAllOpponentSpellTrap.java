package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class DestroyAllOpponentSpellTrap implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757673L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "DestroyAllOpponentSpellTrap";
    }

    @Override
    public String getEffectDescription() {
        return "Destroy all Spells and Traps your opponent controls.";
    }
}
