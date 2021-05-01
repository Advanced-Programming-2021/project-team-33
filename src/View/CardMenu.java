package View;

import Controller.CardController;
import Model.Card;
import Model.CardCategory;
import Model.CardType;

import java.util.regex.Matcher;

public class CardMenu {


    public static void showCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String cardName = matcher.group(1);
            if (CardController.isCardExist(cardName)) {
                System.out.println(cardName);
                Card card = Card.getCardByName(cardName);
                if (card.getCardCategory().equals(CardCategory.MONSTER) ||
                        card.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {

                }
                System.out.println(card.getDescription());
            } else System.out.println("This card does not exist");
        }
    }
}
