package Controller;

import Model.Board;
import Model.Card;
import Model.Deck;
import Model.Player;

public class GameController {

    public static Card selectedCard = null;

    public static int selectCard(String cardPosition, int number, String opponent) {
        if (cardPosition.equals("monster") && Board.getCardFromMonsterField(number) != null) {
            Board.getCardFromMonsterField(number).setSelected(true);
            selectedCard = Board.getCardFromMonsterField(number);
            return 1;
        } else if (cardPosition.equals("monster")) return 0;
        else if (cardPosition.equals("spell") && Board.getCardFromSpellField(number) != null) {
            Board.getCardFromSpellField(number).setSelected(true);
            selectedCard = Board.getCardFromMonsterField(number);
            return 1;
        } else if (cardPosition.equals("spell")) return 0;
        else if (cardPosition.equals("hand") && Board.getCardFromHand(number) != null) {
            Board.getCardFromHand(number).setSelected(true);
            selectedCard = Board.getCardFromMonsterField(number);
            return 1;
        } else if (cardPosition.equals("hand")) return 0;
        return -1;
    }

    public static void deSelectCard() {
        selectedCard.setSelected(false);
    }

    public static boolean isDeckActive(String user) {
        return Player.getUserByUsername(user).getActiveDeck() != null;
    }

    public static boolean isDeckValid(String user) {
        return Deck.getDeckValidation(Player.getUserByUsername(user).getActiveDeck());
    }


}
