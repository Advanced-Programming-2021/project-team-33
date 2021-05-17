package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;
import View.CardMenu;
import View.Communicate;

public class GuessOpponentCard implements Effect {
    @Override
    public void enableEffect(Card card) {
        Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
        Player.currentPlayer.getBoard().getHand().remove(GameController.selectedCard);
        String input = Communicate.input("Guess a card (type card name)");
        boolean isExist = false;
        for (int i = 0; i < Player.opponent.getBoard().getHand().size(); i++) {
            if (Player.opponent.getBoard().getHand().get(i).getCardName().equals(input)) {
                Player.opponent.getBoard().getGraveyard().add(Player.opponent.getBoard().getHand().get(i));
                Player.opponent.getBoard().getHand().remove(i);
                isExist = true;
            }
        }
        if (!isExist) {
            CardMenu.printCardMassage("Mind Crush2");
            Player.currentPlayer.getBoard().getGraveyard().add(Player.currentPlayer.getBoard().getHand().get(0));
            Player.currentPlayer.getBoard().getHand().remove(0);
        } else {
            CardMenu.printCardMassage("Mind Crush1");
            for (int i = 0; i < Player.opponent.getBoard().getDeck().size(); i++) {
                if (Player.opponent.getBoard().getDeck().get(i).getCardName().equals(input)){
                    Player.opponent.getBoard().getGraveyard().add(Player.opponent.getBoard().getDeck().get(i));
                    Player.opponent.getBoard().getDeck().remove(i);
                }
            }
        }
    }

    @Override
    public void disableEffect(Card card) {

    }
}
