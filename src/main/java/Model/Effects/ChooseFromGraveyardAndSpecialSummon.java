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
        if(opponent.equals("1")) {
//            GameController.selectCardFromGraveyard();
        } else {

        }

        // specialSummon(GameController.selectedCard);
    }
}
