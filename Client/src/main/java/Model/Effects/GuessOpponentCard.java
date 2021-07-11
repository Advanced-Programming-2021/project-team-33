package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;
import javafx.scene.control.TextInputDialog;

import java.util.Optional;

public class GuessOpponentCard implements Effect {


    @Override
    public void enableEffect(Card card) {
        Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
        Player.currentPlayer.getBoard().getHand().remove(GameController.selectedCard);
        TextInputDialog dialog = new TextInputDialog("Guess a card");
        dialog.setTitle("Guess");
        dialog.setHeaderText("Guess");
        dialog.setContentText("Guess a card from enemy hand");
        Optional<String> input1 = dialog.showAndWait();
        String input = input1.toString().replaceAll("Optional\\[","").replace("]","");
        boolean isExist = false;
        for (int i = 0; i < Player.opponent.getBoard().getHand().size(); i++) {
            if (Player.opponent.getBoard().getHand().get(i).getCardName().equals(input.toString())) {
                Player.opponent.getBoard().getGraveyard().add(Player.opponent.getBoard().getHand().get(i));
                Player.opponent.getBoard().getHand().remove(i);
                isExist = true;
            }
        }
        if (!isExist) {
            Player.currentPlayer.getBoard().getGraveyard().add(Player.currentPlayer.getBoard().getHand().get(0));
            Player.currentPlayer.getBoard().getHand().remove(0);
        } else {
            for (int i = 0; i < Player.opponent.getBoard().getDeck().size(); i++) {
                if (Player.opponent.getBoard().getDeck().get(i).getCardName().equals(input.toString())){
                    Player.opponent.getBoard().getGraveyard().add(Player.opponent.getBoard().getDeck().get(i));
                    Player.opponent.getBoard().getDeck().remove(i);
                }
            }
        }
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "GuessOpponentCard";
    }

    @Override
    public String getEffectDescription() {
        return "Declare 1 card name; if that card is in your opponent's hand," +
                " they must discard all copies of it, otherwise you discard 1 random card";
    }
}
