package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.CardStatus;
import Model.Effect;
import Model.Player;
import View.Communicate;

public class SummonHighLevel implements Effect {
    @Override
    public void enableEffect(Card card) {
        boolean isCardExist = false;
        Card theCard = null;
        for (int i = 0; i < Player.currentPlayer.getBoard().getGraveyard().size(); i++) {
            if (Player.currentPlayer.getBoard().getGraveyard().get(i).getLevel() > 6) {
                theCard = Player.currentPlayer.getBoard().getGraveyard().get(i);
                isCardExist = true;
                break;
            }
        }
        if (!isCardExist) System.out.println("No card with level 7 or higher exist in your graveyard");
        else {
            String input = Communicate.input("Choose one card from your hand by name for tribute");
            Player.currentPlayer.getBoard().getHand().remove(Card.getCardByName(input));
            Player.currentPlayer.getBoard().getGraveyard().remove(theCard);
            theCard.setCardStatus(CardStatus.ATTACK);
            GameController.selectedCard = theCard;
            GameController.putMonsterOnField();
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
