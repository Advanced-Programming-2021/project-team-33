package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class IncreaseAttack implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757695L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "IncreaseAttack";
    }

    @Override
    public String getEffectDescription() {
        return "Gain 400 ATK to all Warrior-Type monsters," +
                " if you control another monster, monsters your opponent " +
                "controls cannot target this card for an attack";
    }
}
