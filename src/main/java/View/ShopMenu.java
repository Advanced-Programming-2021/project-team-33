package View;

import Controller.CardController;
import Controller.ProgramController;
import Controller.Util;
import Model.Card;
import Model.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

public class ShopMenu {

    public ShopMenu() {

    }

    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        buy(Util.getCommand(input, "shop buy (.+)"));
        showAllOfCardsExistInShop(Util.getCommand(input, "shop show -all"));
        MainMenu.exitMenu(Util.getCommand(input, "menu exit"));

    }

    private void buy(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (!ProgramController.isCardExist(matcher.group(1))) Communicate.output("there is no card with this name");
            else {
                Card card = Card.getCardByName(matcher.group(1));
                if (card.getPrice() > Player.thePlayer.getMoney()) {
                    Communicate.output("not enough money");
                } else {
                    Player.thePlayer.decreaseMoney(card.getPrice());
                    Player.thePlayer.addToCardList(card);
                    Communicate.output("You bought " + card.getCardName());
                }


            }
        }
    }

    private void showAllOfCardsExistInShop(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            ArrayList<Card> cards = Player.thePlayer.getListOfCards();
            String[] cardName = new String[cards.size()];
            for (int i = 0; i < cards.size(); i++) {
                cardName[i] = cards.get(i).getCardName();
            }
            Arrays.sort(cardName);
            for (int i = 0; i < cards.size(); i++) {
                Card card = Card.getCardByName(cardName[i]);
                Communicate.output(card.getCardName() + ":" + card.getDescription());
            }
        }
    }


}
