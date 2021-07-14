package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class GuessOpponentCard implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757679L;

    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "GuessOpponentCard";
    }

    @Override
    public String getEffectDescription() {
        return "Declare 1 card name; if that card is in your opponent's hand," +
                " they must discard all copies of it, otherwise you discard 1 random card";
    }
}
