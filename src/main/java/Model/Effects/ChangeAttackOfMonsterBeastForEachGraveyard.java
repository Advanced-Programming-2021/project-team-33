package Model.Effects;

import Model.*;

public class ChangeAttackOfMonsterBeastForEachGraveyard implements Effect {
    @Override
    public void enableEffect(Card card) {
        changeAttackDeffence(100);
    }

    @Override
    public void disableEffect(Card card) {
        changeAttackDeffence(-100);
    }

    @Override
    public String getEffectName() {
        return "ChangeAttackOfMonsterBeastForEachGraveyard";
    }

    private void changeAttackDeffence(int amount) {
        int numberOfCardsInGraveyard = 0;
        for (Card card : Player.currentPlayer.getBoard().getGraveyard()) {
            if (card.getCardCategory().equals(CardCategory.MONSTER) ||
                    card.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                numberOfCardsInGraveyard++;
            }
        }
        for (Card monster : Player.currentPlayer.getBoard().getFieldCardsForMonsters()) {
            if (monster != null) {
                if (monster.getCardTypes().equals(CardType.BEAST) ||
                        monster.getCardTypes().equals(CardType.BEASTWARRIOR)) {
                    monster.setAttack(monster.getAttack() + (numberOfCardsInGraveyard * amount));
                    monster.setDefence(monster.getDefence() + (numberOfCardsInGraveyard * amount));
                }
            }
        }
    }

    @Override
    public String getEffectDescription() {
        return "";
    }
}
