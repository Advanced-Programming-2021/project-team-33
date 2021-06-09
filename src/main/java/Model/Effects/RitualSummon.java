package Model.Effects;

import Controller.GameController;
import Model.*;
import View.Communicate;

import java.util.ArrayList;

public class RitualSummon implements Effect {
    @Override
    public void enableEffect(Card card) {
        int check = 0;
        int level = 0;
        Card ritualCard = null;
        if (GameController.isMonsterFieldFull()) {
            System.out.println("Field is full");
            return;
        }
        for (int i = 0; i < Player.currentPlayer.getBoard().getHand().size(); i++) {
            if (Player.currentPlayer.getBoard().getHand().get(i).getCardTypes().contains(CardType.RITUAL)) {
                ritualCard = Player.currentPlayer.getBoard().getHand().get(i);
                check = 1;
            }
            level += Player.currentPlayer.getBoard().getHand().get(i).getLevel();
        }
        if (check == 0 || level < ritualCard.getLevel() * 2)
            System.out.println("there is no way you could ritual summon a monster");
        String input = Communicate.input("Choose ritual card from your hand");
        Card card1 = Card.getCardByName(input);
        if (!Player.currentPlayer.getBoard().getHand().contains(card1)) {
            System.out.println("You don't have this card in your hand");
            return;
        } else if (!card1.getCardTypes().contains(CardType.RITUAL)) System.out.println("This card is not ritual");
        check = 0;
        level = 0;
        ArrayList<Card> tributeCards = new ArrayList<>();
        while (check == 0) {
            String input1 = Communicate.input("Choose card from hand to tribute");
            Card card2 = Card.getCardByName(input1);

            if (!Player.currentPlayer.getBoard().getHand().contains(card2))
                System.out.println("You don't have this card in your hand");
            else {
                tributeCards.add(card2);
                level += card2.getLevel();
            }
            if (level < card1.getLevel())
                System.out.println("You should tribute another card too");
            else check = 1;
            if (input1.equals("cancel")) break;
        }
        if (check == 1) {
            for (Card card3 : tributeCards) {
                Player.currentPlayer.getBoard().getHand().remove(card3);
                Player.currentPlayer.getBoard().getGraveyard().add(card3);
            }
            Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
            Player.currentPlayer.getBoard().getHand().remove(GameController.selectedCard);
            GameController.selectedCard = card1;
            Player.currentPlayer.getBoard().getHand().remove(GameController.selectedCard);
            GameController.selectedCard.setCardStatus(CardStatus.ATTACK);
            GameController.putMonsterOnField();
            System.out.println("summoned successfully");
        }

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "RitualSummon";
    }
}
