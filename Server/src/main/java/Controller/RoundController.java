package Controller;

import Model.Card;
import Model.Phase;
import Model.Player;

public class RoundController {

    public static void drawPhase() {
        Player.currentPlayer.setPhase(Phase.DRAW);
        if (!isDrawPossible()) {
            Player.currentPlayer.setLifePoint(0);
        } else {
            Card card = GameController.drawCard(Player.currentPlayer);

        }

    }
    private static boolean isDrawPossible() {
        return Player.currentPlayer.getBoard().getDeck().size() != 0;
    }
}
