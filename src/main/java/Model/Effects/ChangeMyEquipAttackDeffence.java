package Model.Effects;

import Model.*;

public class ChangeMyEquipAttackDeffence implements Effect {
    @Override
    public void enableEffect(Card card) {
        changeAttackDeffence(800);
    }

    @Override
    public void disableEffect(Card card) {
        changeAttackDeffence(-800);
    }

    private void changeAttackDeffence(int amount) {
        for (Card monster : Player.currentPlayer.getBoard().getFieldCardsForMonsters()) {
            if (monster != null && monster.getCardStatus().equals(CardStatus.ATTACK)) {
                monster.setAttack(monster.getAttack() + amount);
                monster.setDefence(monster.getDefence() + amount);
            }
        }
    }
}
