package Controller;

import Model.Card;
import Model.CardCategory;
import Model.CardType;

import java.util.ArrayList;

public class CardController {

    public static void initialCards() {
        Card monsterReborn = new Card("Monster Reborn", "...", 1,
                CardType.NORMAL, CardCategory.SPELL, 1);
    }
}
