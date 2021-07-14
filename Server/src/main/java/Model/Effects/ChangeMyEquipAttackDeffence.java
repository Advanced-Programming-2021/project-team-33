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

    }

    @Override
    public String getEffectDescription() {
        return "The equipped monster gains 800 ATK/DEF for each face-up monster you control.";
    }
}
