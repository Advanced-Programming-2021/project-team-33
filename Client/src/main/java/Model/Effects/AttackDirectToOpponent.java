package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;

public class AttackDirectToOpponent implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757694L;
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

    @Override
    public String getEffectDescription() {
        return "When an opponent's monster declares an attack: Target the attacking monster; negate the attack," +
                " and if you do, inflict damage to your opponent equal to its ATK.";
    }
}
