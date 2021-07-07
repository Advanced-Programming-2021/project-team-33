package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

public class DestroyAllOpponentSpellTrap implements Effect {
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
