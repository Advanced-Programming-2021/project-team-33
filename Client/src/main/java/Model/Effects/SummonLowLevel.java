package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.CardStatus;
import Model.Effect;
import Model.Player;
import View.Communicate;

import java.io.Serial;
import java.io.Serializable;


    public class SummonLowLevel implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757667L;
    @Override
    public void enableEffect(Card card) {
        boolean isCardExist = false;
        for (int i = 0; i < Player.currentPlayer.getBoard().getHand().size(); i++) {
            if (Player.currentPlayer.getBoard().getHand().get(i).getLevel() < 5) {
                isCardExist = true;
                break;
            }
        }
        if (!isCardExist) System.out.println("No card with level 4 or lower exist in your hand");
        else {
            String input = Communicate.input("Choose one card from your hand to set");
            Player.currentPlayer.getBoard().getHand().remove(Card.getCardByName(input));
            GameController.selectedCard = Card.getCardByName(input);
            GameController.selectedCard.setCardStatus(CardStatus.SET);
            GameController.putMonsterOnField();
        }
    }

    @Override
    public void disableEffect(Card card) {

    }
    @Override
    public String getEffectName() {
        return "SummonLowLevel";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
