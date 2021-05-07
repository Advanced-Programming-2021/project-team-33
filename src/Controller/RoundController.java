package Controller;

import Model.Card;
import Model.Player;
import View.GameMenu;
import View.Phase;

import java.util.Random;

public class RoundController {
    static GameMenu gameMenu = new GameMenu();
    public static boolean isSummoned = false;

    public static void setWhoPlayFirst(String firstPlayer, String secondPlayer) {
        Random random = new Random();
        int first = random.nextInt(6 - 0) + 1;
        int second = random.nextInt(6 - 0) + 1;
        int compare = ProgramController.compare(first, second);
        if (compare >= 0) {
            Player.currentPlayer = Player.getUserByUsername(firstPlayer);
            Player.opponent = Player.getUserByUsername(secondPlayer);
        } else {
            Player.opponent = Player.getUserByUsername(firstPlayer);
            Player.currentPlayer = Player.getUserByUsername(secondPlayer);
        }
        gameMenu.rollDice(first, second, Player.currentPlayer.getUsername(), firstPlayer, secondPlayer);
    }

    public static void changeTurn() {
        Player auxPlayer = Player.currentPlayer;
        Player.currentPlayer = Player.opponent;
        Player.opponent = auxPlayer;
    }

    public static void drawPhase() {
        Player.currentPlayer.setPhase(Phase.DRAW);
        gameMenu.informPhase(Phase.DRAW);
        Card card = GameController.drawCard(Player.currentPlayer);
        gameMenu.drawCard(card);
        //checkWinner?!
        standByPhase();
    }

    private static void standByPhase() {
        Player.currentPlayer.setPhase(Phase.STANDBY);
        //someCard...
        gameMenu.informPhase(Phase.STANDBY);
        mainPhase1();
    }

    public static void mainPhase1() {
        GameController.setAllCardsUnchanged();
        GameController.setAllCardUnSummoned();
        isSummoned = false;
        Player.currentPlayer.setPhase(Phase.MAIN1);
        gameMenu.informPhase(Phase.MAIN1);
        GameController.showBoard();
    }

    public static void battlePhase() {
        GameController.setAllCardsUnAttacked();
        Player.currentPlayer.setPhase(Phase.BATTLE);
        gameMenu.informPhase(Phase.BATTLE);
    }

    public static void mainPhase2() {
        Player.currentPlayer.setPhase(Phase.MAIN2);
        gameMenu.informPhase(Phase.MAIN2);
    }

    public static void endPhase() {
        Player.currentPlayer.setPhase(Phase.END);
        gameMenu.informPhase(Phase.END);
        changeTurn();
    }
}
