package View;

import Controller.GameController;
import Controller.Util;
import Model.Board;
import Model.Player;

import java.util.regex.Matcher;

public class GameMenu {
    boolean checked = false;
    GameController gameController = new GameController();

    public void run(String input) {
        selectCard(Util.getCommand(input, "select --(\\S+) --(\\D*) (\\d+)"));
        deSelectCard(Util.getCommand(input, "select -d"));
    }

    public void selectCard(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            String cardPosition = matcher.group(1);
            String opponent = matcher.group(2);
            int number = Integer.parseInt(matcher.group(3));
            int massage = gameController.selectCard(cardPosition, number, opponent);
            if (massage == 1) System.out.println("card selected");
            if (massage == 0) System.out.println("no card found in the given position");
            else System.out.println("invalid selection");
        }
    }

    public void deSelectCard(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            if(gameController.selectedCard == null) System.out.println("no card is selected yet");
            else System.out.println("card deselected");
            gameController.deSelectCard();
        }
    }

}
