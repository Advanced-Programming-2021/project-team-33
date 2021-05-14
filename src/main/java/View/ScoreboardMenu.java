package View;

import Controller.ProgramController;
import Controller.Util;
import Model.Player;

import java.util.*;
import java.util.regex.Matcher;

public class ScoreboardMenu {


    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        showScoreBoard(Util.getCommand(input, "scoreboard show"));
        MainMenu.exitMenu(Util.getCommand(input, "menu exit"));
        Player.thePlayer.increaseScore(1);
        Player.getUserByUsername("reza").increaseScore(1);
    }

    public ScoreboardMenu() {

    }

    private void showScoreBoard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            int rank = 0, counter = 0, equal = 0, a = 0;
            Map<String, Integer> scoreBoard = ProgramController.createScoreBoard();
            for (Map.Entry<String, Integer> en : scoreBoard.entrySet()) {
                a++;
                if (en.getValue() != equal) {
                    rank++;
                    rank += counter;
                    counter = 0;
                } else {
                    counter++;
                    if (a == 1) rank++;
                }
                System.out.println(rank + "- " + en.getKey() + ": " + en.getValue());
                equal = en.getValue();
            }
        }
    }


}
