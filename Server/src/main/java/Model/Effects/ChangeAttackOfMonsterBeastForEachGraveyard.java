package Model.Effects;

import Model.*;

import java.io.Serial;
import java.io.Serializable;

public class ChangeAttackOfMonsterBeastForEachGraveyard implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757684L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "ChangeAttackOfMonsterBeastForEachGraveyard";
    }

    private void changeAttackDeffence(int amount) {

    }

    @Override
    public String getEffectDescription() {
        return "All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard.";
    }
}
