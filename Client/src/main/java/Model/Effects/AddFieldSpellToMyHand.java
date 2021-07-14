package Model.Effects;

import Controller.GameController;
import Model.*;
import View.Communicate;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Optional;

public class AddFieldSpellToMyHand implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757693L;
    @Override
    public void enableEffect(Card card) {
        ArrayList<Card> deck = Player.currentPlayer.getBoard().getDeck();
        if (deck.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Massage");
            alert.setHeaderText(null);
            alert.setContentText("Your deck is empty");
            alert.showAndWait();

            return;
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        for (Card card1 : Player.currentPlayer.getBoard().getDeck()) {
            if (card1.getCardCategory().equals(CardCategory.SPELL) ||
                    card1.getCardTypes().contains(CardType.FIELD)) {
                int index = Player.currentPlayer.getBoard().getDeck().indexOf(card1);
                indexes.add(index);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Massage");
                alert.setHeaderText(null);
                alert.setContentText(index + 1 + "- " +
                        card1.getCardName());
                alert.showAndWait();
            }
        }
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("");
        dialog.setHeaderText("");
        dialog.setContentText("Please enter the index of card:");
        Optional<String> result = dialog.showAndWait();
        String input = result.get();
        try {
            int selectedCardIndex = Integer.parseInt(input) - 1;
            if (indexes.contains(selectedCardIndex)) {
//                GameController.selectCard();
                if (Player.currentPlayer.getBoard().getHand().size() < 6) {
                    Player.currentPlayer.getBoard().getDeck().remove(selectedCardIndex);
                    Player.currentPlayer.getBoard().getHand().add(GameController.selectedCard);
                }
                else{
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Massage");
                    alert.setHeaderText(null);
                    alert.setContentText("Your hand is full");
                    alert.showAndWait();
                }

            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Massage");
                alert.setHeaderText(null);
                alert.setContentText("Your input is not valid");
                alert.showAndWait();
            }
        } catch (Exception e) {
            Communicate.output("Your input is not valid");
        }
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "AddFieldSpellToMyHand";
    }

    @Override
    public String getEffectDescription() {
        return "Add 1 Field Spell from your Deck to your hand.";
    }
}
