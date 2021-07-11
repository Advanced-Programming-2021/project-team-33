package Model.Effects;

import Controller.GameController;
import Model.*;
import View.Communicate;
import View.GameMenu;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;

import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;

public class ChooseFromGraveyardAndSpecialSummon implements Effect {
    @Override
    public void enableEffect(Card card) {
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Monster Reborn");
        dialog.setHeaderText("Monster Reborn");
        dialog.setContentText("Choose graveyard:" + "\n" +
                "1-My graveyard \t 2-Opponent graveyard");
        Optional<String> result = dialog.showAndWait();
        String opponent = result.get();
        if (opponent.equals("1")) {
            chooseFromGraveYard("Your", Player.currentPlayer);
        } else {
            chooseFromGraveYard("Opponent's", Player.opponent);
        }
    }

    @Override
    public void disableEffect(Card card) {

    }



    public void chooseFromGraveYard(String output, Player player) {
        ArrayList<Card> graveYard = player.getBoard().getGraveyard();
        if (graveYard.size() == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Graveyard is empty!");
            alert.showAndWait();
            return;
        }
        ArrayList<Integer> indexes = new ArrayList<>();
        for (Card card1 : player.getBoard().getGraveyard()) {
            if (card1.getCardCategory().equals(CardCategory.MONSTER) ||
                    card1.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                int index = player.getBoard().getGraveyard().indexOf(card1);
                indexes.add(index);
                Communicate.output(index + 1 + "- " +
                        card1.getCardName());
            }
        }
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Monster Reborn");
        dialog.setHeaderText("Monster Reborn");
        dialog.setContentText("Please enter the index of card:");
        Optional<String> result = dialog.showAndWait();
        String input = result.get();
        System.out.println(result.get());
        try {
            int selectedCardIndex = Integer.parseInt(input) - 1;
            if (indexes.contains(selectedCardIndex)) {
                GameController.selectedCard =  player.getBoard().getGraveyard().get(selectedCardIndex);
                player.getBoard().getGraveyard().remove(GameController.selectedCard);
                GameController.selectedCard.setCardStatus(CardStatus.ATTACK);
                GameController.putMonsterOnField();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Massage");
                alert.setHeaderText(null);
                alert.setContentText("Special summon done");
                alert.showAndWait();
                GameController.deSelectCard();
            } else {
                Communicate.output("Your input is not valid");
            }
        } catch (Exception e) {
            Communicate.output("Your input is not valid");
        }
    }

    @Override
    public String getEffectName() {
        return "ChooseFromGraveyardAndSpecialSummon";
    }

    @Override
    public String getEffectDescription() {
        return "Target 1 monster in either GY; Special Summon it.";
    }

}
