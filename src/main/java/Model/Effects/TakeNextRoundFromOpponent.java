package Model.Effects;

import Controller.GameController;
import Controller.RoundController;
import Model.Card;
import Model.Effect;
import Model.Player;

public class TakeNextRoundFromOpponent implements Effect {

    @Override
    public void enableEffect(Card card) {
        RoundController.isRoundFreeze = true;
        Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
        Player.currentPlayer.getBoard().getHand().remove(GameController.selectedCard);
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
        return "";
    }
}
