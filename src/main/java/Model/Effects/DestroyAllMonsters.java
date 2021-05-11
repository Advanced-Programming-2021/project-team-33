package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

public class DestroyAllMonsters implements Effect {
    @Override
    public void enableEffect(Card card) {

            for (int i = 0; i < Player.currentPlayer.getBoard().getFieldCardsForMonsters().size(); i++) {
                if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null) {
                    Player.currentPlayer.getBoard().getGraveyard().add(Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i));
                    Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(i, null);
                }
            }
            for (int i = 0; i < Player.opponent.getBoard().getFieldCardsForMonsters().size(); i++) {
                if (Player.opponent.getBoard().getFieldCardsForMonsters().get(i) != null) {
                    Player.opponent.getBoard().getGraveyard().add(Player.opponent.getBoard().getFieldCardsForMonsters().get(i));
                    Player.opponent.getBoard().getFieldCardsForMonsters().set(i, null);
                }
            }
            GameController.isSummonTrap = false;


    }

    @Override
    public void disableEffect(Card card) {

    }
}
