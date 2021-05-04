package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import View.Communicate;

public class ChooseFromGraveyardAndSpecialSummon implements Effect {
    @Override
    public void run(Card card) {
        String opponent = Communicate.input("Choose graveyard:" + "\n" +
                "1-My graveyard \t 2-Opponent graveyard");
        GameController.selectCard("graveyard", 0, opponent.equals("1") ? "" : "opponent");
        // specialSummon(GameController.selectedCard);
    }
}
