package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.CardStatus;
import Model.Effect;
import Model.Player;

public class ThreeLightEffect implements Effect {
    int remainingRounds;
    Player user;

    @Override
    public void enableEffect(Card card) {
        if(!GameController.isThreeLightActive)
        {
            remainingRounds = 6;
            GameController.getLightPlayer = Player.opponent;
            user = Player.currentPlayer;
        }
        if (remainingRounds > 0) {
            for (int i = 0; i < 5; i++) {
                if (Player.opponent.getBoard().getFieldCardsForMonsters().get(i) != null)
                    Player.opponent.getBoard().getFieldCardsForMonsters().get(i).setCardStatus(CardStatus.ATTACK);
            }
            GameController.isThreeLightActive = true;
            remainingRounds--;
        } else {
            GameController.isThreeLightActive = false;
            int index =  user.getBoard().getFieldCardsForSpellTraps().indexOf(Card.getCardByName("Swords of Revealing Light"));
            user.getBoard().getGraveyard().add(Card.getCardByName("Swords of Revealing Light"));
            user.getBoard().getFieldCardsForSpellTraps().set(index, null);
        }

    }

    @Override
    public void disableEffect(Card card) {

    }
    @Override
    public String getEffectName() {
        return null;
    }
}
