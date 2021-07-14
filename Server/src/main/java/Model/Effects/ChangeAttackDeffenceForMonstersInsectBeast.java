package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;

public class ChangeAttackDeffenceForMonstersInsectBeast implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757682L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "ChangeAttackDeffenceForMonstersInsectBeast";
    }

    private void changeAttackDeffence(Player player, int amount) {

    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
