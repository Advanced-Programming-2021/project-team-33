package Model.Effects;

import Controller.GameController;
import Model.Card;
import Model.Effect;
import Model.Player;
import View.Communicate;

public class DestroySpecificSpellCard implements Effect {
    @Override
    public void enableEffect(Card card) {
        int check = 0, index = 0;
        while(check == 0){
            String input = Communicate.input("remove one card from your hand by choosing index");
            index = Integer.parseInt(input);
            check = GameController.selectCard("hand", index, "");
        }
        Player.currentPlayer.getBoard().getGraveyard().add(Player.currentPlayer.getBoard().getHand().get(index));
        Player.currentPlayer.getBoard().getHand().remove(index);

        Player.opponent.getBoard().getGraveyard().add(GameController.lastSelectedCard);
        Player.opponent.getBoard().getHand().remove(GameController.lastSelectedCard);
        GameController.isSpellTrap = false;
    }

    @Override
    public void disableEffect(Card card) {

    }

    @Override
    public String getEffectName() {
        return "DestroySpecificSpellCard";
    }
}
