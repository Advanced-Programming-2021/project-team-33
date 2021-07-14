package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;


    public class PickTwoCardsFromTopOfDeck implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757661L;
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
        return "Draw 2 cards.";
    }
}
