package Model.Effects;

import Model.Card;
import Model.CardType;
import Model.Effect;
import Model.Player;

public class ChangeAttackDeffenceForMonstersInsectBeast implements Effect {
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

    private void changeAttackDeffence(Player player, int amount) {
        for (Card monster : player.getBoard().getFieldCardsForMonsters()) {
            if (monster != null) {
                if (monster.getCardTypes().equals(CardType.INSECT) ||
                        monster.getCardTypes().equals(CardType.BEASTWARRIOR) ||
                        monster.getCardTypes().equals(CardType.BEAST)) {
                    monster.setAttack(monster.getAttack() + amount);
                    monster.setDefence(monster.getDefence() + amount);
                }
            }
        }
    }
}
