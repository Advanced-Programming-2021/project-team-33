package Model.Effects;

import Controller.GameController;
import Model.Effect;
import View.Communicate;

public class ChooseFromGraveyardAndSpecialSummon<T> implements Effect {
    T input;
    ChooseFromGraveyardAndSpecialSummon(T obj) {  this.input = obj;  }
    public T getObject()  { return this.input; }
    @Override
    public void run() {
        String opponent = Communicate.input("Choose graveyard:" + "\n" +
                "1-My graveyard \t 2-Opponent graveyard");
        GameController.selectCard("graveyard", 0, opponent.equals("1") ? "" : "opponent");
    }
}
