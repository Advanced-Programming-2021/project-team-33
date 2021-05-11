package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;
import View.Communicate;

public class GuessOpponentCard implements Effect {
    @Override
    public void enableEffect(Card card) {
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
            Player.currentPlayer.getBoard().getGraveyard().add(Player.currentPlayer.getBoard().getHand().get(0));
            Player.currentPlayer.getBoard().getHand().remove(0);
        }
    }

    @Override
    public void disableEffect(Card card) {

    }
}
