package View;

import Controller.CardController;
import Model.Card;

import java.util.regex.Matcher;

public class CardMenu {


    public static void showCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String cardName = matcher.group(1);
            if (CardController.isCardExist(cardName)) {
                System.out.println(Card.getCardByName(cardName).getDescription());
            } else System.out.println("This card does not exist");
        }
    }
}
