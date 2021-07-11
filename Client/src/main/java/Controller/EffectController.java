package Controller;

import Model.Card;
import Model.Effect;
import Model.Effects.*;
import Model.Player;

import java.util.ArrayList;

public class EffectController {
    public static ArrayList<Effect> effects = new ArrayList<>();
    public static void spellAbsorption() {
        Card card = getCardFromField(Player.currentPlayer, "Spell Absorption");
        if (card != null) {
            for (Effect effect : card.getEffects()) {
                effect.enableEffect(null);
            }
        }
        card = getCardFromField(Player.opponent, "Spell Absorption");
        if (card != null) {
            for (Effect effect : card.getEffects()) {
                effect.enableEffect(null);
            }
        }
    }

    public static boolean messengerOfPeace() {
        Card card = getCardFromField(Player.currentPlayer, "Messenger of peace");
        if (card!=null) {
            if (GameController.selectedCard.getAttack() >= 1500)
                return true;
        }
        return false;
    }

    private static Card getCardFromField(Player player, String name) {
        for (Card card : player.getBoard().getFieldCardsForSpellTraps()) {
            if (card != null && card.getCardName().equals(name)) {
                return card;
            }
        }
        for (Card card : player.getBoard().getFieldCardsForMonsters()) {
            if (card != null && card.getCardName().equals(name)) {
                return card;
            }
        }
        return null;
    }


}
