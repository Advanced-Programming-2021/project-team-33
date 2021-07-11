package Controller;

import Model.Card;
import Model.Player;
import View.GameMenu;
import Model.Phase;
import javafx.scene.control.Alert;

import java.util.Random;

public class RoundController {
    static GameMenu gameMenu = new GameMenu();
    public static boolean isSummoned = false, isRoundFreeze = false;
    public static int remainingRounds, maxLp = 0, rounds;
    public static Player winnerOfFirstRound, otherPlayer;

    public static void setRound(int round) {
        rounds = round;
        remainingRounds = rounds;
    }

    public static void checkEndOfRound() {

        GameMenu gameMenu = new GameMenu();
        if (Player.thePlayer.getLifePoint() <= 0) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Round ended");
            alert.setHeaderText("Round " + remainingRounds + " ended");
            alert.setContentText(otherPlayer.getUsername() + " won the game with score: " + 1000 + " - 0");
            alert.showAndWait();

            winnerOfFirstRound = otherPlayer;
            remainingRounds--;
            maxLp = Math.max(otherPlayer.getLifePoint(), maxLp);
            if (remainingRounds == 0) GameController.setWinner(maxLp, otherPlayer, Player.thePlayer);
            else if (remainingRounds == 1 && winnerOfFirstRound.equals(otherPlayer))
                GameController.setWinner(maxLp, otherPlayer, Player.thePlayer);
            else GameController.setNextGame(Player.thePlayer.getUsername(),
                        otherPlayer.getUsername());
        } else if (otherPlayer.getLifePoint() <= 0) {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Round ended");
            alert.setHeaderText("Round " + remainingRounds + " ended");
            alert.setContentText(Player.thePlayer.getUsername() + " won the game with score: " + 1000 + " - 0");
            alert.showAndWait();


            winnerOfFirstRound = Player.thePlayer;
            remainingRounds--;
            maxLp = Math.max(Player.thePlayer.getLifePoint(), maxLp);
            if (remainingRounds == 0) GameController.setWinner(maxLp, Player.thePlayer, otherPlayer);
            else if (remainingRounds == 1 && winnerOfFirstRound.equals(Player.thePlayer))
                GameController.setWinner(maxLp, Player.thePlayer, otherPlayer);
            else GameController.setNextGame(otherPlayer.getUsername(),
                        Player.thePlayer.getUsername());
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
        if (Player.currentPlayer.equals(Player.theAi))
            Ai.aiAction();
    }


    public static void drawPhase() {
        Player.currentPlayer.setPhase(Phase.DRAW);
        gameMenu.informPhase(Phase.DRAW);
        if (!isDrawPossible()) {
            Player.currentPlayer.setLifePoint(0);
        } else {
            Card card = GameController.drawCard(Player.currentPlayer);
            if (card != null) gameMenu.drawCard(card);
        }
    }

    public static void standByPhase() {
        Player.currentPlayer.setPhase(Phase.STANDBY);
        //someCard...
        gameMenu.informPhase(Phase.STANDBY);
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
        GameMenu gameMenu = new GameMenu();
        gameMenu.endPhaseMassage();
        RoundController.drawPhase();
    }

}
