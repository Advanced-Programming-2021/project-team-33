package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

public class AttackDirectToOpponent implements Effect {
    @Override
    public void enableEffect(Card card) {
        Player.currentPlayer.setLifePoint(Player.currentPlayer.getLifePoint() -
                GameController.lastSelectedCard.getAttack());
        GameController.isAttackTrap = false;
    }

    @Override
    public void disableEffect(Card card) {

    }
}
