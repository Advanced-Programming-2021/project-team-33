package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class AttackDirectToOpponent implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757694L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "AttackDirectToOpponent";
    }

    @Override
    public String getEffectDescription() {
        return "When an opponent's monster declares an attack: Target the attacking monster; negate the attack," +
                " and if you do, inflict damage to your opponent equal to its ATK.";
    }
}
