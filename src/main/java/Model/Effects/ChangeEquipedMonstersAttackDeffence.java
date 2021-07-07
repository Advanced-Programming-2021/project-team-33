package Model.Effects;

import Model.Card;
import Model.CardType;
import Model.Effect;
import Model.Player;

public class ChangeEquipedMonstersAttackDeffence implements Effect {
    @Override
    public void enableEffect(Card card) {
        changeAttackDeffence(Player.currentPlayer, 400, 200);
        changeAttackDeffence(Player.opponent, 400, 200);
    }

    @Override
    public void disableEffect(Card card) {
        changeAttackDeffence(Player.currentPlayer, -400, -200);
        changeAttackDeffence(Player.opponent, -400, -200);
    }

    @Override
    public String getEffectName() {
        return "ChangeEquipedMonstersAttackDeffence";
    }

    private void changeAttackDeffence(Player player, int attackAmount, int deffenceAmount) {
        for (Card monster : player.getBoard().getFieldCardsForMonsters()) {
            if (monster != null) {
                if (monster.getCardTypes().equals(CardType.FIEND) ||
                monster.getCardTypes().equals(CardType.SPELLCASTER)) {
                    monster.setAttack(monster.getAttack() + attackAmount);
                    monster.setDefence(monster.getDefence() - deffenceAmount);
                }
            }
        }
    }

    @Override
    public String getEffectDescription() {
        return "A DARK monster equipped with this card increases its ATK by 400 points and decreases its DEF by 200 points.";
    }
}
