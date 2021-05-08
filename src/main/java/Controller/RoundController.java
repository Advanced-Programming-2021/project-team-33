package Controller;

import Model.Card;
import Model.Player;
import View.GameMenu;
import View.Phase;

import java.util.Random;

public class RoundController {
    static GameMenu gameMenu = new GameMenu();
    public static boolean isSummoned = false;
    public static int remainingRounds, maxLp = 0, rounds;
    public static Player winnerOfFirstRound, otherPlayer;


    public static void setRound(int round) {
        rounds = round;
        remainingRounds = rounds;
    }

    public static void checkEndOfRound() {
        GameMenu gameMenu = new GameMenu();
        if (rounds != 2) {
            if (Player.thePlayer.getLifePoint() <= 0) {
                winnerOfFirstRound = otherPlayer;
                remainingRounds--;
                maxLp = Math.max(otherPlayer.getLifePoint(), maxLp);
                if (remainingRounds == 0) GameController.setWinner(maxLp, otherPlayer, Player.thePlayer);
                else if (remainingRounds == 1 && winnerOfFirstRound.equals(otherPlayer))
                    GameController.setWinner(maxLp, otherPlayer, Player.thePlayer);
                else GameController.setNextGame(Player.thePlayer.getUsername(),
                            otherPlayer.getUsername());
                gameMenu.informEndOfRound(otherPlayer, 1000);
            } else if (otherPlayer.getLifePoint() <= 0) {
                winnerOfFirstRound = Player.thePlayer;
                remainingRounds--;
                maxLp = Math.max(Player.thePlayer.getLifePoint(), maxLp);
                if (remainingRounds == 0) GameController.setWinner(maxLp, Player.thePlayer, otherPlayer);
                else if (remainingRounds == 1 && winnerOfFirstRound.equals(Player.thePlayer))
                    GameController.setWinner(maxLp, Player.thePlayer, otherPlayer);
                else GameController.setNextGame(otherPlayer.getUsername(),
                            Player.thePlayer.getUsername());
                gameMenu.informEndOfRound(Player.thePlayer, 1000);
            }
        } else {
            if (Player.thePlayer.getLifePoint() <= 0) {
                remainingRounds--;
                maxLp = Math.max(otherPlayer.getLifePoint(), maxLp);
                if (remainingRounds == 1) {
                    winnerOfFirstRound = otherPlayer;
                    GameController.setNextGame(Player.thePlayer.getUsername(),
                            otherPlayer.getUsername());
                } else if (remainingRounds == 0 && winnerOfFirstRound.equals(otherPlayer))
                    GameController.setWinner(maxLp, otherPlayer, Player.thePlayer);
                else if (remainingRounds == 0) GameController.setWinner(-1, otherPlayer, Player.thePlayer);
            } else if (otherPlayer.getLifePoint() <= 0) {
                remainingRounds--;
                maxLp = Math.max(otherPlayer.getLifePoint(), maxLp);
                if (remainingRounds == 1) {
                    winnerOfFirstRound = Player.thePlayer;
                    GameController.setNextGame(otherPlayer.getUsername(),
                            Player.thePlayer.getUsername());
                } else if (remainingRounds == 0 && winnerOfFirstRound.equals(Player.thePlayer))
                    GameController.setWinner(maxLp, Player.thePlayer, otherPlayer);
                else if (remainingRounds == 0) GameController.setWinner(-1, Player.thePlayer, otherPlayer);
            }
        }

    }

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

    private static boolean isDrawPossible() {
        return Player.currentPlayer.getBoard().getDeck().size() != 0;
    }

    public static void changeTurn() {
        Player auxPlayer = Player.currentPlayer;
        Player.currentPlayer = Player.opponent;
        Player.opponent = auxPlayer;
    }


    public static void drawPhase() {
        Player.currentPlayer.setPhase(Phase.DRAW);
        gameMenu.informPhase(Phase.DRAW);
        if (!isDrawPossible()) {
            Player.currentPlayer.setLifePoint(0);
        }
        Card card = GameController.drawCard(Player.currentPlayer);
        if (card != null) gameMenu.drawCard(card);

        standByPhase();
    }

    private static void standByPhase() {
        Player.currentPlayer.setPhase(Phase.STANDBY);
        //someCard...
        gameMenu.informPhase(Phase.STANDBY);
        mainPhase1();
    }

    public static void mainPhase1() {
        GameController.checkOpponentSpellTraps();
        GameController.setAllCardsUnchanged();
        GameController.setAllCardUnSummoned();
        isSummoned = false;
        Player.currentPlayer.setPhase(Phase.MAIN1);
        gameMenu.informPhase(Phase.MAIN1);
        GameController.showBoard();
    }

    public static void battlePhase() {
        GameController.checkOpponentSpellTraps();
        GameController.setAllCardsUnAttacked();
        Player.currentPlayer.setPhase(Phase.BATTLE);
        gameMenu.informPhase(Phase.BATTLE);
    }

    public static void mainPhase2() {
        GameController.checkOpponentSpellTraps();
        Player.currentPlayer.setPhase(Phase.MAIN2);
        gameMenu.informPhase(Phase.MAIN2);
    }

    public static void endPhase() {
        Player.currentPlayer.setPhase(Phase.END);
        gameMenu.informPhase(Phase.END);
        changeTurn();
    }

}
