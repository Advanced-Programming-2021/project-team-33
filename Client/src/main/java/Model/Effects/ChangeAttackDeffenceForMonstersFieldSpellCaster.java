package Model.Effects;

import Model.Card;
import Model.CardType;
import Model.Effect;
import Model.Player;

public class ChangeAttackDeffenceForMonstersFieldSpellCaster implements Effect {
    @Override
    public void enableEffect(Card card) {
        changeAttackDeffence(Player.currentPlayer, 200);
        changeAttackDeffence(Player.opponent, 200);
    }

    @Override
    public void disableEffect(Card card) {
        changeAttackDeffence(Player.currentPlayer, -200);
        changeAttackDeffence(Player.opponent, -200);
    }

    @Override
    public String getEffectName() {
        return "ChangeAttackDeffenceForMonstersFieldSpellCaster";
    }

    private void changeAttackDeffence(Player player, int amount) {
        for (Card monster : player.getBoard().getFieldCardsForMonsters()) {
            if(monster!=null) {
                if (monster.getCardTypes().equals(CardType.FIEND) ||
                        monster.getCardTypes().equals(CardType.SPELLCASTER)) {
                    monster.setAttack(monster.getAttack() + amount);
                    monster.setDefence(monster.getDefence() + amount);
                } else if(monster.getCardTypes().equals(CardType.FAIRY)) {
                    monster.setAttack(monster.getAttack() - amount);
                    monster.setDefence(monster.getDefence() - amount);
                }
            }
        }
    }

    @Override
    public String getEffectDescription() {
        return "If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.";
    }
}
