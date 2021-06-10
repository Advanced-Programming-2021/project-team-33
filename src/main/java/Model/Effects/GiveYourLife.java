package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

public class GiveYourLife implements Effect {
    @Override
    public void enableEffect(Card card) {
        Player.currentPlayer.setLifePoint(Player.currentPlayer.getLifePoint() - 2000);
        Player.opponent.getBoard().getGraveyard().add(GameController.lastSelectedCard);
        Player.opponent.getBoard().getHand().remove(GameController.lastSelectedCard);
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "GiveYourLife";
    }
}
