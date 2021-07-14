package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;

public class ThreeLightEffect implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757669L;
    int remainingRounds;
    Player user;

    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }
    @Override
    public String getEffectName() {
        return "ThreeLightEffect";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
