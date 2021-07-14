package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;
import Model.Phase;

import java.io.Serial;
import java.io.Serializable;


    public class EndBattlePhaseForOpponent implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757677L;
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
        return "When an opponent's monster declares an attack: Target the attacking monster; negate the attack, then end the Battle Phase.";
    }
}
