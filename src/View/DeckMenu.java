package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.Util;
import Model.Deck;
import Model.Player;

import java.util.regex.Matcher;

public class DeckMenu {


    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        createDeck(Util.getCommand(input, "deck create (\\S+)"));
        deleteDeck(Util.getCommand(input, "deck delete (\\S+)"));
        CardMenu.showCard(Util.getCommand(input, "card show (.+)"));
        exitMenu(Util.getCommand(input, "menu exit"));
    }

    public void createDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String deckName = matcher.group(1);
            if (ProgramController.isDeckExist(deckName))
                System.out.println("deck with name " + deckName + " already exists");
            else {
                GameController.createDeck(deckName);
                System.out.println("deck created successfully!");
            }
        }
    }

    public void deleteDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String deckName = matcher.group(1);
            if (!ProgramController.isDeckExist(deckName))
                System.out.println("deck with name " + deckName + " does not exist");
            else {
                GameController.deleteDeck(deckName);
                System.out.println("deck deleted successfully!");
            }
        }
    }

    public void exitMenu(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            MainMenu.menu = "main";
        }
    }


}
