package Model.Effects;

import Model.Card;
import Model.Effect;

import java.io.Serial;
import java.io.Serializable;

public class AddFieldSpellToMyHand implements Effect, Serializable {
    @Serial
    private static final long serialVersionUID = 6529685098267757693L;
    @Override
    public void enableEffect(Card card) {

    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "AddFieldSpellToMyHand";
    }

    @Override
    public String getEffectDescription() {
        return "Add 1 Field Spell from your Deck to your hand.";
    }
}
