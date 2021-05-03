package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.Util;
import Model.Board;
import Model.Card;
import Model.Player;

import java.util.Random;
import java.util.regex.Matcher;

public class GameMenu {
    boolean checked = false;

    public void run(String input) {
        checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        CardMenu.showCard(Util.getCommand(input, "card show (.+)"));
        selectCard(Util.getCommand(input, "select --(\\S+) (--\\D*) (\\d+)"));
        deSelectCard(Util.getCommand(input, "select -d"));
        activeSpell(Util.getCommand(input, "activate effect"));
    }


    public void rollDice(int first, int second, String currentPlayer, String firstPlayer, String secondPlayer) {
        System.out.println(firstPlayer + " get " + first);
        System.out.println(secondPlayer + " get " + second);
        System.out.println(currentPlayer + "'s turn\n\n");
    }

    public void informPhase(Phase phase) {
        switch (phase) {
            case DRAW -> System.out.println("phase: Draw Phase");
            case STANDBY -> System.out.println("phase: Standby Phase");
            case MAIN1 -> System.out.println("phase: Main Phase 1");
            case BATTLE -> System.out.println("phase: Battle Phase");
            case MAIN2 -> System.out.println("phase: Main Phase 2");
            case END -> System.out.println("phase: End Phase");
        }
    }

    public void drawCard(Card card) {
        System.out.println("new card added to the hand : " + card.getCardName());
    }


    private void activeSpell(Matcher matcher) {
        if (!checked && matcher.matches()) {
            // GameController.selectedCard.run() ????
        }
    }

    public void selectCard(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            String cardPosition = matcher.group(1);
            String opponent = matcher.group(2);
            int number = Integer.parseInt(matcher.group(3));
            int massage = GameController.selectCard(cardPosition, number, opponent);
            if (massage == 1) System.out.println("card selected");
            if (massage == 0) System.out.println("no card found in the given position");
            else System.out.println("invalid selection");
        }
    }

    public void deSelectCard(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else System.out.println("card deselected");
            GameController.deSelectCard();
        }
    }

}
