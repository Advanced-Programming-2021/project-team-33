package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;
import View.Communicate;

import java.io.Serial;
import java.io.Serializable;

public class ChooseMonsterAndDestroy implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757670L;
    @Override
    public void enableEffect(Card card) {
        int check = 0;
        while (check == 0) {
            String input = Communicate.input("chose enemy monster by index");
            int index = Integer.parseInt(input);
            index = GameController.switchNumberForCurrent(index);
            if (Player.opponent.getBoard().getFieldCardsForMonsters().get(index) == null)
                System.out.println("No monster in given position");
            else {
                Player.opponent.getBoard().getGraveyard().add(Player.opponent.getBoard().getFieldCardsForMonsters().get(index));
                Player.opponent.getBoard().getFieldCardsForMonsters().set(index, null);
                System.out.println("Enemy monster Destroyed");
                check = 1;
            }

        }

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "ChooseMonsterAndDestroy";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
