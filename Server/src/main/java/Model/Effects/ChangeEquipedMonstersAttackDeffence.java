package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;


    public class ChangeEquipedMonstersAttackDeffence implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757685L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "ChangeEquipedMonstersAttackDeffence";
    }

    private void changeAttackDeffence(Player player, int attackAmount, int deffenceAmount) {

    }

    @Override
    public String getEffectDescription() {
        return "A DARK monster equipped with this card increases its ATK by 400 points and decreases its DEF by 200 points.";
    }
}
