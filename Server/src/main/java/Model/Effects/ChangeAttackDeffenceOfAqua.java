package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;


    public class ChangeAttackDeffenceOfAqua implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757683L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "ChangeAttackDeffenceOfAqua";
    }

    private void changeAttackDeffence(Player player, int attackAmount, int deffenceAmount) {

    }

    @Override
    public String getEffectDescription() {
        return "Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 points.";
    }
}
