package View;

import Controller.Util;

import java.util.regex.Matcher;

public class DeckMenu {


    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        createDeck(Util.getCommand(input, "deck create (\\S+)"));
        CardMenu.showCard(Util.getCommand(input, "card show (.+)"));
    }

    public void createDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
        }
    }


}
