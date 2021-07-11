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
            if(GameController.selectedCard.getCardName().equals("Torrential Tribute")) {
                int index = Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().indexOf(GameController.selectedCard);
                Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
                Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(index, null);
                Player.opponent.getBoard().getGraveyard().add(GameController.lastSelectedCard);
                Player.opponent.getBoard().getHand().remove(GameController.lastSelectedCard);
            }



    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "DestroyAllMonsters";
    }

    @Override
    public String getEffectDescription() {
        return "Destroy all monsters on the field.";
    }
}
