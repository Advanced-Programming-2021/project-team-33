package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;


    public class GiveYourLife implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757678L;
    @Override
    public void enableEffect(Card card) {
        Player.currentPlayer.setLifePoint(Player.currentPlayer.getLifePoint() - 2000);
        Player.opponent.getBoard().getGraveyard().add(GameController.lastSelectedCard);
        Player.opponent.getBoard().getHand().remove(GameController.lastSelectedCard);
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "GiveYourLife";
    }

    @Override
    public String getEffectDescription() {
        return "When a monster(s) would be Summoned, OR when a Spell/Trap Card, or monster ";
    }
}
