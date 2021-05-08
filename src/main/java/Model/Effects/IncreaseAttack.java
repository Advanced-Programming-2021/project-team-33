package Model.Effects;

import Model.Card;
import Model.Effect;
import Model.Player;

public class IncreaseAttack implements Effect {
    @Override
    public void run(Card card) {
        for (Card monsterCard : Player.currentPlayer.getBoard().getFieldCardsForMonsters()) {
            if (monsterCard != null)
                monsterCard.setAttack(monsterCard.getAttack() + 400);
        }
    }
}
