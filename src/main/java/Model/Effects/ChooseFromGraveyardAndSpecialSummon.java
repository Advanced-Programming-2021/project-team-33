package Model.Effects;

import Controller.GameController;
import Model.*;
import View.Communicate;
import View.GameMenu;
import javafx.scene.image.Image;

import java.util.ArrayList;

public class ChooseFromGraveyardAndSpecialSummon implements Effect {
    @Override
    public void enableEffect(Card card) {
        new GameMenu().showError("Choose graveyard:");
        new GameMenu().setAction("spell");
        new GameMenu().button1.setImage(new Image(getClass().getResourceAsStream("/PNG/mygraveyard.png")));
        new GameMenu().button2.setImage(new Image(getClass().getResourceAsStream("/PNG/opponentGraveyard.png")));
        new GameMenu().button1.setOnMouseClicked(event -> chooseFromGraveYard("Your", Player.currentPlayer));
        new GameMenu().button2.setOnMouseClicked(event -> chooseFromGraveYard("Opponent's", Player.opponent));
    }

    @Override
    public void disableEffect(Card card) {

    }



    public void chooseFromGraveYard(String output, Player player) {
        ArrayList<Card> graveYard = player.getBoard().getGraveyard();
        if (graveYard.size() == 0) {
            new GameMenu().showError(output + " graveyard is empty");
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
        String input = Communicate.input("Please enter the index of card");
        try {
            int selectedCardIndex = Integer.parseInt(input) - 1;
            if (indexes.contains(selectedCardIndex)) {
                GameController.selectCardFromGraveyard(selectedCardIndex);
                // specialSummon(GameController.selectedCard);
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
