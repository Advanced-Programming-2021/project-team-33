package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class TakeNextRoundFromOpponent implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757668L;

    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "TakeNextRoundFromOpponent";
    }

    @Override
    public String getEffectDescription() {
        return "Skip the Draw Phase of your opponent's next turn.";
    }
}
