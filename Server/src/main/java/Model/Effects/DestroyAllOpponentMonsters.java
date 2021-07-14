package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class DestroyAllOpponentMonsters implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757672L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "DestroyAllOpponentMonsters";
    }

    @Override
    public String getEffectDescription() {
        return "When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.";
    }
}
