package Model.Effects;

import Controller.GameController;
import Model.*;
import View.Communicate;

import java.util.ArrayList;

public class ChooseFromGraveyardAndSpecialSummon implements Effect {
    @Override
    public void enableEffect(Card card) {
        String opponent = Communicate.input("Choose graveyard:" + "\n" +
                "1-My graveyard \t 2-Opponent graveyard");
        if (opponent.equals("1")) {
            chooseFromGraveYard("Your", Player.currentPlayer);
        } else {
            chooseFromGraveYard("Opponent's", Player.opponent);
        }


    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "ChooseFromGraveyardAndSpecialSummon";
    }

    public void chooseFromGraveYard(String output, Player player) {
        ArrayList<Card> graveYard = player.getBoard().getGraveyard();
        if (graveYard.size() == 0) {
            Communicate.output(output + " graveyard is empty");
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
}
