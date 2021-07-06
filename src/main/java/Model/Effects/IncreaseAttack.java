package Model.Effects;

import Model.Card;
import Model.CardType;
import Model.Effect;
import Model.Player;

public class IncreaseAttack implements Effect {
    @Override
    public void enableEffect(Card card) {
        for (Card monsterCard : Player.currentPlayer.getBoard().getFieldCardsForMonsters()) {
            if (monsterCard != null && monsterCard.getCardTypes().contains(CardType.WARRIOR) && !monsterCard.isBuffed()) {
                monsterCard.setBuffed(true);
                monsterCard.setAttack(monsterCard.getAttack() + 400);
            }
        }
    }

    @Override
    public void disableEffect(Card card) {
        for (Card monsterCard : Player.currentPlayer.getBoard().getFieldCardsForMonsters()) {
            if (monsterCard != null && monsterCard.getCardTypes().contains(CardType.WARRIOR) && monsterCard.isBuffed()) {
                monsterCard.setBuffed(false);
                monsterCard.setAttack(monsterCard.getAttack() - 400);
            }
        }
    }

    @Override
    public String getEffectName() {
        return "IncreaseAttack";
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
