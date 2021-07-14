package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class ChangeHeartOfEnemyMonster implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757686L;
    Card card1;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "ChangeHeartOfEnemyMonster";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
