package Model.Effects;

import Model.*;

import java.io.Serial;
import java.io.Serializable;


    public class ChangeMyEquipAttackDeffence implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757687L;
    @Override
    public void enableEffect(Card card) {
        changeAttackDeffence(800);
    }

    @Override
    public void disableEffect(Card card) {
        changeAttackDeffence(-800);
    }

    @Override
    public String getEffectName() {
        return "ChangeMyEquipAttackDeffence";
    }

    private void changeAttackDeffence(int amount) {
        for (Card monster : Player.currentPlayer.getBoard().getFieldCardsForMonsters()) {
            if (monster != null && monster.getCardStatus().equals(CardStatus.ATTACK)) {
                monster.setAttack(monster.getAttack() + amount);
                monster.setDefence(monster.getDefence() + amount);
            }
        }
    }

    @Override
    public String getEffectDescription() {
        return "The equipped monster gains 800 ATK/DEF for each face-up monster you control.";
    }
}
