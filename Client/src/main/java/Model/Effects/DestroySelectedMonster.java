package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;


    public class DestroySelectedMonster implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757674L;
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

    @Override
    public String getEffectName() {
        return "DestroySelectedMonster";
    }

    @Override
    public String getEffectDescription() {
        return "When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK:" +
                " Target that monster; destroy that target.";
    }
}
