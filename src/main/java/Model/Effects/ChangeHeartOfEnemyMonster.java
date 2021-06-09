package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;
import View.Communicate;

public class ChangeHeartOfEnemyMonster implements Effect {
    Card card1;
    @Override
    public void enableEffect(Card card) {
        String input = Communicate.input("choose one card from enemy monster field by index");
        int index = Integer.parseInt(input);
        index = GameController.switchNumberForCurrent(index);
        System.out.println(index);
        if (index > 5 || Player.opponent.getBoard().getFieldCardsForMonsters().get(index) == null)
            Communicate.output("invalid selection.");
        else {
            card1 = Player.opponent.getBoard().getFieldCardsForMonsters().get(index);
            GameController.selectedCard = card1;
            Player.opponent.getBoard().getFieldCardsForMonsters().set(index , null);
            GameController.putMonsterOnField();
            GameController.isHeartActive = true;
        }
    }

    @Override
    public void disableEffect(Card card) {
        int index = Player.opponent.getBoard().getFieldCardsForMonsters().indexOf(card1);
        Player.opponent.getBoard().getFieldCardsForMonsters().set(index , null);
        GameController.selectedCard = card1;
        GameController.putMonsterOnField();
        GameController.isHeartActive = false;
    }

    @Override
    public String getEffectName() {
        return null;
    }
}
