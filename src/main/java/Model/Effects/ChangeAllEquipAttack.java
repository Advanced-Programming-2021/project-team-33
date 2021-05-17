package Model.Effects;

import Model.Card;
import Model.CardType;
import Model.Effect;
import Model.Player;

public class ChangeAllEquipAttack implements Effect {
    @Override
    public void enableEffect(Card card) {
        changeAttackDeffence(Player.currentPlayer, 500);
        changeAttackDeffence(Player.opponent, 500);
    }

    @Override
    public void disableEffect(Card card) {
        changeAttackDeffence(Player.currentPlayer, -500);
        changeAttackDeffence(Player.opponent, -500);
    }

    @Override
    public String getEffectName() {
        return "ChangeAllEquipAttack";
    }

    private void changeAttackDeffence(Player player, int attackAmount) {
        for (Card monster : player.getBoard().getFieldCardsForMonsters()) {
            if (monster != null) {
                monster.setAttack(monster.getAttack() + attackAmount);
            }
        }
    }
}
