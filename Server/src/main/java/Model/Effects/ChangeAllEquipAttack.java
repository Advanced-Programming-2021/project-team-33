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

    }

    @Override
    public void disableEffect(Card card) {

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

    }
}
