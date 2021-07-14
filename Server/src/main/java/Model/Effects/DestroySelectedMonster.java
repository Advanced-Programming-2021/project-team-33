package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class DestroySelectedMonster implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757674L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "DestroySelectedMonster";
    }

    @Override
    public String getEffectDescription() {
        return "When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK:" +
                " Target that monster; destroy that target.";
    }
}
