package Model.Effects;

import Controller.RoundController;
import Model.Card;
import Model.Effect;

public class TakeNextRoundFromOpponent implements Effect {

    @Override
    public void enableEffect(Card card) {
        RoundController.isRoundFreeze = true;
    }

    @Override
    public void disableEffect(Card card) {

    }
}
