package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

public class IncreaseLPForEverySpell implements Effect {
    @Override
    public void enableEffect(Card card) {
        Player.currentPlayer.setLifePoint(Player.currentPlayer.getLifePoint() + 500);
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "IncreaseLPForEverySpell";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
