package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.CardCategory;
import Model.Effect;
import Model.Player;
import View.Communicate;

import java.util.ArrayList;

public class SummonFromGraveYard implements Effect {
    @Override
    public void enableEffect(Card card) {
        int check = 0, index = 0;
        ArrayList<Card> graveyard = Player.currentPlayer.getBoard().getGraveyard();
        if (graveyard.size() == 0) {
            System.out.println("empty graveyard");
            return;
        }
        for (Card value : graveyard) {
            if (value.getCardCategory().equals(CardCategory.MONSTER) ||
                    value.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                check = 1;
                break;
            }
        }
        if (check == 0) return;
        check = 0;
        while (check == 0) {
            String input = Communicate.input("Choose from graveyard by index");
            index = Integer.parseInt(input);
            if (graveyard.get(index) != null &&
                    (graveyard.get(index).getCardCategory().equals(CardCategory.MONSTEREFFECT)) ||
                    graveyard.get(index).getCardCategory().equals(CardCategory.MONSTER)) check = 1;
            else System.out.println("invalid card");
        }
        Player.currentPlayer.getBoard().getFieldCardsForMonsters().add(graveyard.get(index));
        graveyard.remove(index);
    }

    @Override
    public void disableEffect(Card card) {

    }
}
