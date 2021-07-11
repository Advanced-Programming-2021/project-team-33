package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;

public class DestroyAllOpponentMonsters implements Effect {
    @Override
    public void enableEffect(Card card) {

        for (int i = 0; i < Player.opponent.getBoard().getFieldCardsForMonsters().size(); i++) {
            if (Player.opponent.getBoard().getFieldCardsForMonsters().get(i) != null) {
                Player.opponent.getBoard().getGraveyard().add(Player.opponent.getBoard().getFieldCardsForMonsters().get(i));
                Player.opponent.getBoard().getFieldCardsForMonsters().set(i, null);
            }
        }
        if (GameController.selectedCard.getCardName().equals("Mirror Force")) {
            Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
            int index = Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().indexOf(GameController.selectedCard);
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(index , null);
        }
        GameController.isAttackTrap = false;

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
