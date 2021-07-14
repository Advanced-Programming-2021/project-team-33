package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;

public class ChangeAllEquipAttack implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757680L;
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

    @Override
    public String getEffectDescription() {
        return "The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.";
    }

    private void changeAttackDeffence(Player player, int attackAmount) {
        for (Card monster : player.getBoard().getFieldCardsForMonsters()) {
            if (monster != null) {
                monster.setAttack(monster.getAttack() + attackAmount);
            }
        }
    }
}
