package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

public class DestroySelectedMonster implements Effect {
    @Override
    public void enableEffect(Card card) {
            Player.opponent.getBoard().getGraveyard().add(GameController.lastSelectedCard);
            Player.opponent.getBoard().getHand().remove(GameController.lastSelectedCard);
    }

    @Override
    public void disableEffect(Card card) {

    }
}
