package View;

import Controller.GameController;
import Controller.RoundController;
import Controller.Util;
import Model.Player;

import java.util.regex.Matcher;

public class GraveyardMenu {

    public void run(String input) {
        MainMenu.checked = false;
        selectCard(Util.getCommand(input, "select --graveyard (\\d+)"));
        CardMenu.showSelectedCard(Util.getCommand(input, "card show --selected( --opponent)?"));
        backToGame(Util.getCommand(input, "back"));
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
    }

    private void selectCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String opponent = "";
            if (matcher.group(2) != null) opponent = matcher.group(2);
            int index = Integer.parseInt(matcher.group(1));
            if (!opponent.equals("")) {
                if (Player.opponent.getBoard().getGraveyard().get(index) == null)
                    System.out.println("there is no card in this position");
                else {
                    GameController.selectedCard = Player.opponent.getBoard().getGraveyard().get(index);
                    System.out.println("card selected");
                }
            } else {
                if (Player.currentPlayer.getBoard().getGraveyard().get(index) == null)
                    System.out.println("there is no card in this position");
                else {
                    GameController.selectCardFromGraveyard(index);
                    System.out.println("card selected");
                }
            }

        }
    }

    private void backToGame(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            MainMenu.menu = "game";
        }
    }


}
