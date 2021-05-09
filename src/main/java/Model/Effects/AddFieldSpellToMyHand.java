package Model.Effects;

import Controller.GameController;
import Model.*;
import View.Communicate;

import java.util.ArrayList;

public class AddFieldSpellToMyHand implements Effect {
    @Override
    public void enableEffect(Card card) {
        ArrayList<Card> deck = Player.currentPlayer.getBoard().getDeck();
        if (deck.size() == 0) {
            Communicate.output("Your deck is empty");
            return;
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        for (Card card1 : Player.currentPlayer.getBoard().getDeck()) {
            if (card1.getCardCategory().equals(CardCategory.SPELL) ||
                    card1.getCardTypes().contains(CardType.FIELD)) {
                int index = Player.currentPlayer.getBoard().getDeck().indexOf(card1);
                indexes.add(index);
                Communicate.output(index + 1 + "- " +
                        card1.getCardName());
            }
        }
        String input = Communicate.input("Please enter the index of card");
        try {
            int selectedCardIndex = Integer.parseInt(input) - 1;
            if (indexes.contains(selectedCardIndex)) {
//                GameController.selectCard();
                if (Player.currentPlayer.getBoard().getHand().size() < 6) {
                    Player.currentPlayer.getBoard().getDeck().remove(selectedCardIndex);
                    Player.currentPlayer.getBoard().getHand().add(GameController.selectedCard);
                }
                else
                    Communicate.output("Your hand is full");
            } else {
                Communicate.output("Your input is not valid");
            }
        } catch (Exception e) {
            Communicate.output("Your input is not valid");
        }
    }

    @Override
    public void disableEffect(Card card) {

    }
}
