package Model.Effects;

import Controller.GameController;
import Model.*;
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

        while (check == 0) {
            String input = Communicate.input("Choose from graveyard by index");
            index = Integer.parseInt(input);
            if (index > graveyard.size() - 1) System.out.println("invalid card");
            else check = 1;
        }
        Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
        Player.currentPlayer.getBoard().getHand().remove(GameController.selectedCard);
        GameController.selectedCard = graveyard.get(index);
        if(GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)||
                GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER))
        GameController.putMonsterOnField();
        else if ((GameController.selectedCard.getCardCategory().equals(CardCategory.SPELL)||
                GameController.selectedCard.getCardCategory().equals(CardCategory.TRAP)) &&
                        !GameController.selectedCard.getCardTypes().equals(CardType.FIELD))
            GameController.putSpellTrapOnField();
        else {
            Player.currentPlayer.getBoard().getGraveyard().add(Player.currentPlayer.getBoard().getFieldZone().get(0));
            Player.currentPlayer.getBoard().getFieldZone().set(0, GameController.selectedCard);
        }

        graveyard.remove(index);


    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "SummonFromGraveYard";
    }
}
