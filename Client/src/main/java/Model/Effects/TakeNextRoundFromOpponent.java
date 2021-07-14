package Model.Effects;

import Controller.GameController;
import Controller.RoundController;
import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;


    public class TakeNextRoundFromOpponent implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757668L;
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
        return "Skip the Draw Phase of your opponent's next turn.";
    }
}
