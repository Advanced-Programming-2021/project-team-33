package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;
import View.Phase;

public class EndBattlePhaseForOpponent implements Effect {
    @Override
    public void enableEffect(Card card) {
        Player.opponent.setPhase(Phase.MAIN2);
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "EndBattlePhaseForOpponent";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
