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


    public static void printCardMassage(String cardName) {
        if(cardName.equals("Command Knight")) Communicate.output("You can't attack this monster now");
        if(cardName.equals("Mind Crush1")) Communicate.output("Nice Guess!");
        if(cardName.equals("Mind Crush2")) Communicate.output("Wrong Guess!");
    }

    public static void showCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String cardName = matcher.group(1);
            if (ProgramController.isCardExist(cardName)) {
                Communicate.output(cardName);
                Card card = Card.getCardByName(cardName);
                if (card.getCardCategory().equals(CardCategory.MONSTER) ||
                        card.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                    printMonsterCard(card);
                } else printSpellTrapCard(card);
            } else Communicate.output("This card does not exist");
        }
    }

    public static void showSelectedCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (GameController.isOpponentCardSelected && (GameController.selectedCard.getCardStatus().equals(CardStatus.SET) ||
                    GameController.selectedCard.getCardStatus().equals(CardStatus.BACK)))
                Communicate.output("card is not visible");
            else if (GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) ||
                    GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                printMonsterCard(GameController.selectedCard);
            } else printSpellTrapCard(GameController.selectedCard);
        }
    }


    private static void printMonsterCard(Card card) {
        Communicate.output("Name: " + card.getCardName());
        Communicate.output("Level: " + card.getLevel());
        Communicate.output("Type: " + card.getCardTypes().toString().replace("[", "")
                .replace("]", "").replace("", ""));
        Communicate.output("ATK: " + card.getAttack());
        Communicate.output("DEF: " + card.getDefence());
        Communicate.output("Description: " + card.getDescription());
        Communicate.output(card.getCardStatus().toString());
    }

    private static void printSpellTrapCard(Card card) {
        Communicate.output("Name: " + card.getCardName());
        Communicate.output(card.getCardCategory().toString());
        Communicate.output("Type: " + card.getCardTypes().toString().replace("[", "")
                .replace("]", "").replace("", ""));
        Communicate.output("Description: " + card.getDescription());
    }


}
