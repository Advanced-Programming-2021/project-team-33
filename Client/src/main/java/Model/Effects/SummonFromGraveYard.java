package Model.Effects;

import Model.Card;
import Model.CardCategory;
import Model.Effect;
import Model.Player;
import View.Communicate;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;



    public class SummonFromGraveYard implements Effect, Serializable {
        @Serial
        private static final long serialVersionUID = 6529685098267757665L;
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
            String input;
            if(!Player.currentPlayer.getUsername().equals("Ai"))  input = Communicate.input("Choose from graveyard by index");
            else input = "0";
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

    @Override
    public String getEffectName() {
        return "SummonFromGraveYard";
    }

    @Override
    public String getEffectDescription() {
        return "Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position.";
    }
}
