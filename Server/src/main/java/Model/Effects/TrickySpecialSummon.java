package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.CardStatus;
import Model.Effect;
import Model.Player;
import View.Communicate;



public class TrickySpecialSummon implements Effect {
    @Override
    public void enableEffect(Card card) {
        String input = Communicate.input("Choose one card from your hand to tribute");
        Card theCard = Card.getCardByName(input);
        Player.currentPlayer.getBoard().getHand().remove(theCard);
        Player.currentPlayer.getBoard().getGraveyard().add(theCard);
        Player.currentPlayer.getBoard().getHand().remove(GameController.selectedCard);
        GameController.selectedCard.setCardStatus(CardStatus.ATTACK);
        GameController.putMonsterOnField();
        System.out.println("Special summon successfully done.");
    }

    @Override
    public void disableEffect(Card card) {

    }
    @Override
    public String getEffectName() {
        return "TrickySpecialSummon";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
