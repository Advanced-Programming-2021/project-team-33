package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;


    public class DestroyAllOpponentSpellTrap implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757673L;
    @Override
    public void enableEffect(Card card) {
        for (int i = 0; i < Player.opponent.getBoard().getFieldCardsForSpellTraps().size(); i++) {
            if (Player.opponent.getBoard().getFieldCardsForSpellTraps().get(i)!=null) {
                Player.opponent.getBoard().getGraveyard().add(Player.opponent.getBoard().getFieldCardsForSpellTraps().get(i));
                Player.opponent.getBoard().getFieldCardsForSpellTraps().set(i, null);
            }
        }
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "DestroyAllOpponentSpellTrap";
    }

    @Override
    public String getEffectDescription() {
        return "Destroy all Spells and Traps your opponent controls.";
    }
}
