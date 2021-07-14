package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;


    public class ChangeAttackDeffenceForMonstersFieldSpellCaster implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757681L;

    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "ChangeAttackDeffenceForMonstersFieldSpellCaster";
    }

    private void changeAttackDeffence(Player player, int amount) {

    }

    @Override
    public String getEffectDescription() {
        return "If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.";
    }
}
