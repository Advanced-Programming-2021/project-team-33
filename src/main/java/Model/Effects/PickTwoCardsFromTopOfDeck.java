package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

public class PickTwoCardsFromTopOfDeck implements Effect {
    @Override
    public void enableEffect(Card card) {
        GameController.drawCard(Player.currentPlayer);
        GameController.drawCard(Player.currentPlayer);
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "PickTwoCardsFromTopOfDeck";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
