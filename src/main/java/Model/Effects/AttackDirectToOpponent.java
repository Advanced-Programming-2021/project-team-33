package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

public class AttackDirectToOpponent implements Effect {
    @Override
    public void enableEffect(Card card) {
        Player.opponent.setLifePoint(Player.opponent.getLifePoint() -
                GameController.lastSelectedCard.getAttack());
        GameController.isAttackTrap = false;
        if (GameController.selectedCard.getCardName().equals("Magic Cylinder")) {
            Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
            int index = Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().indexOf(GameController.selectedCard);
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(index , null);
        }
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "AttackDirectToOpponent";
    }
}
