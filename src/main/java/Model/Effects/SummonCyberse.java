package Model.Effects;

import Controller.GameController;
import Model.*;
import View.Communicate;

import java.util.ArrayList;

public class SummonCyberse implements Effect {
    @Override
    public void enableEffect(Card card) {
        if(!GameController.isCyberseActive){
            ArrayList<Card> cyberseCards = new ArrayList<>();
            System.out.println(Player.currentPlayer.getBoard().getHand().get(1).getCardTypes().get(0));
            for (int i = 0; i < Player.currentPlayer.getBoard().getGraveyard().size(); i++) {
                if (Player.currentPlayer.getBoard().getGraveyard().get(i).getCardTypes().contains(CardType.CYBERSE))
                    cyberseCards.add(Player.currentPlayer.getBoard().getGraveyard().get(i));
            }
            for (int i = 0; i < Player.currentPlayer.getBoard().getHand().size(); i++) {
                if (Player.currentPlayer.getBoard().getHand().get(i).getCardTypes().contains(CardType.CYBERSE))
                    cyberseCards.add(Player.currentPlayer.getBoard().getHand().get(i));
            }
            for (int i = 0; i < Player.currentPlayer.getBoard().getDeck().size(); i++) {
                if (Player.currentPlayer.getBoard().getDeck().get(i).getCardTypes().contains(CardType.CYBERSE))
                    cyberseCards.add(Player.currentPlayer.getBoard().getDeck().get(i));
            }
            if (cyberseCards.get(0) == null) System.out.println("you don't have any cyberse card.");
            else {
                System.out.println("you have these cards:");
                for (int i = 0; i < cyberseCards.size(); i++) {
                    System.out.println(cyberseCards.get(i).getCardName());
                }
                String input = Communicate.input("Choose one card to special summon by name");
                GameController.selectedCard = Card.getCardByName(input);
                if (GameController.selectedCard.getCardStatus().equals(CardStatus.GRAVEYARD)) {
                    Player.currentPlayer.getBoard().getGraveyard().remove(GameController.selectedCard);
                } else if(GameController.selectedCard.getCardStatus().equals(CardStatus.HAND)) {
                    Player.currentPlayer.getBoard().getHand().remove(GameController.selectedCard);
                }
                GameController.selectedCard.setCardStatus(CardStatus.ATTACK);
                GameController.putMonsterOnField();
                System.out.println("special summoned successfully");
            }
        }

    }

    @Override
    public void disableEffect(Card card) {

    }
    @Override
    public String getEffectName() {
        return null;
    }
}
