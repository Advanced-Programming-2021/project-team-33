package Model.Effects;

import Model.*;

import java.io.Serial;
import java.io.Serializable;


    public class ChangeWarriorAttackDeffenceReplace implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757688L;
    @Override
    public void enableEffect(Card card) {
        changeAttackDeffence(1);
    }

    @Override
    public void disableEffect(Card card) {
        changeAttackDeffence(-1);
    }

    @Override
    public String getEffectName() {
        return "ChangeWarriorAttackDeffenceReplace";
    }

    private void changeAttackDeffence(int zarib) {

    }
    @Override
    public String getEffectDescription() {
        return "Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.";
    }
}
