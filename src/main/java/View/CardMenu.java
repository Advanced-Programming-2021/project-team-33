package View;

import Controller.CardController;
import Controller.GameController;
import Controller.ProgramController;
import Model.Card;
import Model.CardCategory;
import Model.CardStatus;
import Model.CardType;

import java.util.regex.Matcher;

public class CardMenu {


    public static void showCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String cardName = matcher.group(1);
            if (ProgramController.isCardExist(cardName)) {
                System.out.println(cardName);
                Card card = Card.getCardByName(cardName);
                if (card.getCardCategory().equals(CardCategory.MONSTER) ||
                        card.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                    printMonsterCard(card);
                } else printSpellTrapCard(card);
            } else System.out.println("This card does not exist");
        }
    }

    public static void showSelectedCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (GameController.isOpponentCardSelected && (GameController.selectedCard.getCardStatus().equals(CardStatus.SET) ||
                    GameController.selectedCard.getCardStatus().equals(CardStatus.BACK)))
                System.out.println("card is not visible");
            else if (GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) ||
                    GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                printMonsterCard(GameController.selectedCard);
            } else printSpellTrapCard(GameController.selectedCard);
        }
    }


    private static void printMonsterCard(Card card) {
        System.out.println("Name: " + card.getCardName());
        System.out.println("Level: " + card.getLevel());
        System.out.println("Type: " + card.getCardTypes());
        System.out.println("ATK: " + card.getAttack());
        System.out.println("DEF: " + card.getDefence());
        System.out.println("Description: " + card.getDescription());
    }

    private static void printSpellTrapCard(Card card) {
        System.out.println("Name: " + card.getCardName());
        System.out.println(card.getCardCategory());
        System.out.println("Type: " + card.getCardTypes());
        System.out.println("Description: " + card.getDescription());
    }


}
