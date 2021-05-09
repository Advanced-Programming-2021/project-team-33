package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;
import View.Communicate;

public class RemoveCardFromHandAndDestroyTwoSpellTrap implements Effect {
    @Override
    public void enableEffect(Card card) {
        for (Card card1 : Player.currentPlayer.getBoard().getHand()) {
            Communicate.output(Player.currentPlayer.getBoard().getHand().indexOf(card1) + 1 + "-" + card1.getCardName());
        }
        String index = Communicate.input("Please enter the index of card you want to remove:");
        int i = 0;
        try{
            i = Integer.parseInt(index) - 1;
            if (i>Player.currentPlayer.getBoard().getHand().size() || i<0) {
                Communicate.output("Your input is invalid");
                return;
            }
        }catch (Exception e) {
            Communicate.output("Your input is invalid");
            return;
        }
        Player.currentPlayer.getBoard().getHand().remove(i);

        String input1 = Communicate.input("Whoose Board do you like to get card from?" + "\n"+
        "1-My Board\t2-Opponent Board");
        Player player = null;
        if (input1.equals("1")) {
            player = Player.currentPlayer;
        } else if (input1.equals("2")) {
            player = Player.opponent;
        }
        else{
            Communicate.output("Your input is invalid");
            return;
        }
        String numberOfCards = Communicate.input("How many spell or traps do you want to attack to? 1 or 2?");
        try{
            int number = Integer.parseInt(numberOfCards);
            if (number!=1 && number!=2) {
                for (int j = 0; j < number; j++) {
                    GameController.showBoard();
                    String input = Communicate.input("Enter the number of card you want to attack to:");
//                    GameController.selectCard(Integer.parseInt(input));
                }
            } else {
                Communicate.output("Your input is invalid");
            }
        }catch (Exception e) {
            Communicate.output("Your input is invalid");
        }
    }

    @Override
    public void disableEffect(Card card) {

    }
}
