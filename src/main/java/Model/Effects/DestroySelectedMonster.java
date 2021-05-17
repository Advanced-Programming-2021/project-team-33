package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

public class DestroySelectedMonster implements Effect {
    @Override
    public void enableEffect(Card card) {
        if (GameController.lastSelectedCard.isSummoned()) {
            GameController.lastSelectedCard.setSummoned(false);
            Player.opponent.getBoard().getGraveyard().add(GameController.lastSelectedCard);
            int index = Player.opponent.getBoard().getFieldCardsForMonsters().indexOf(GameController.lastSelectedCard);
            Player.opponent.getBoard().getFieldCardsForMonsters().set(index, null);
        } else {
            Player.opponent.getBoard().getGraveyard().add(GameController.lastSelectedCard);
            Player.opponent.getBoard().getHand().remove(GameController.lastSelectedCard);
        }
        int index = Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().indexOf(GameController.selectedCard);
        Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
        Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(index, null);
    }

    @Override
    public void disableEffect(Card card) {

    }
}
