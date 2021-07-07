package Model.Effects;

import Model.Card;
import Model.CardType;
import Model.Effect;
import Model.Player;

public class ChangeAttackDeffenceOfAqua implements Effect {
    @Override
    public void enableEffect(Card card) {
        changeAttackDeffence(Player.currentPlayer, 500, 400);
        changeAttackDeffence(Player.opponent, 500, 400);
    }

    @Override
    public void disableEffect(Card card) {
        changeAttackDeffence(Player.currentPlayer, -500, -400);
        changeAttackDeffence(Player.opponent, -500, -400);
    }

    @Override
    public String getEffectName() {
        return "ChangeAttackDeffenceOfAqua";
    }

    private void changeAttackDeffence(Player player, int attackAmount, int deffenceAmount) {
        for (Card monster : player.getBoard().getFieldCardsForMonsters()) {
            if (monster != null) {
                if (monster.getCardTypes().equals(CardType.AQUA)) {
                    monster.setAttack(monster.getAttack() + attackAmount);
                    monster.setDefence(monster.getDefence() - deffenceAmount);
                }
            }
        }
    }

    @Override
    public String getEffectDescription() {
        return "Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 points.";
    }
}
