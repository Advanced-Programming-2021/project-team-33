package Controller;

import Model.Board;
import Model.CardCategory;
import Model.Player;

import static Model.Player.theAi;

public class Ai {


    public static void initiateGameWithAi(String firstPlayer, int round) {
        RoundController.otherPlayer = theAi;
        RoundController.setRound(round);
        prepareGame(firstPlayer);
    }

    private static void setActiveDeckForAi() {

    }


    public static void setNextGame(String firstPlayer, String secondPlayer) {
        //changeDeck
        //prepareGame(firstPlayer, secondPlayer);
    }

    private static void prepareGame(String firstPlayer) {
        RoundController.setWhoPlayFirst(firstPlayer, "Ai");
        Board board1 = new Board(Player.currentPlayer);
        Player.currentPlayer.setBoard(board1);
        Board board2 = new Board(Player.opponent);
        Player.opponent.setBoard(board2);
        Player.currentPlayer.resetLifePoint();
        Player.opponent.resetLifePoint();
        GameController.shuffleDeck(Player.currentPlayer);
        GameController.shuffleDeck(Player.opponent);
        for (int i = 0; i < 5; i++) {
            GameController.drawCard(Player.currentPlayer);
            GameController.drawCard(Player.opponent);
        }
        RoundController.drawPhase();
    }

    public static void aiAction() {
        RoundController.drawPhase();
        if (isTrapPossible()) doTrap();
        if (isSpellPossible()) doSpell();
        if (isTrapPossible()) tributeMonster();
        else if (isMonsterPossible()) summonMonster();
        RoundController.battlePhase();
        if (isAttackPossible()) attack();
        if (isDirectAttackPossible()) directAttack();
        RoundController.mainPhase2();

    }

    private static boolean isTrapPossible() {
        for (int i = 0; i < Player.currentPlayer.getBoard().getHand().size(); i++) {
            if (Player.currentPlayer.getBoard().getHand().get(i).getCardCategory().equals(CardCategory.TRAP))
                return true;
        }
        return false;
    }

    private static boolean isSpellPossible() {
        return false;
    }

    private static boolean isMonsterPossible() {
        return false;
    }

    private static boolean isAttackPossible() {
        return false;
    }

    private static boolean isTributePossible() {
        return false;
    }

    private static boolean isDirectAttackPossible() {
        return false;
    }

    private static void doTrap() {

    }

    private static void doSpell() {

    }

    private static void summonMonster() {

    }

    private static void tributeMonster() {

    }

    private static void attack() {

    }

    private static void directAttack() {

    }

    private static void answerMiddleGameQuestions() {

    }

}
