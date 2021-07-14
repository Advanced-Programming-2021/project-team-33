package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

import java.io.Serial;
import java.io.Serializable;

public class ChooseFromGraveyardAndSpecialSummon implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757689L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }



    public void chooseFromGraveYard(String output, Player player) {

    }

    @Override
    public String getEffectName() {
        return "ChooseFromGraveyardAndSpecialSummon";
    }

    @Override
    public String getEffectDescription() {
        return "Target 1 monster in either GY; Special Summon it.";
    }

}
