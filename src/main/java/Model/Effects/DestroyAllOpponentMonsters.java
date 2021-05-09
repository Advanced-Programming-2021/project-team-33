package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

public class DestroyAllOpponentMonsters implements Effect {
    @Override
    public void enableEffect(Card card) {
        for (int i = 0; i < Player.opponent.getBoard().getFieldCardsForMonsters().size(); i++) {
            if (Player.opponent.getBoard().getFieldCardsForMonsters().get(i)!=null) {
                Player.opponent.getBoard().getGraveyard().add(Player.opponent.getBoard().getFieldCardsForMonsters().get(i));
                Player.opponent.getBoard().getFieldCardsForMonsters().set(i, null);
            }
        }
    }

    @Override
    public void disableEffect(Card card) {

    }
}
