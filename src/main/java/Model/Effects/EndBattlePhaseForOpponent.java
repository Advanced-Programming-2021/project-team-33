package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;
import View.Phase;

public class EndBattlePhaseForOpponent implements Effect {
    @Override
    public void enableEffect(Card card) {
        Player.opponent.setPhase(Phase.MAIN2);
        Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
        int index = Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().indexOf(GameController.selectedCard);
        Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(index, null);
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "EndBattlePhaseForOpponent";
    }
}
