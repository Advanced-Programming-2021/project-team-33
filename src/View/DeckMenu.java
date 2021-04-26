package View;

import Controller.Util;

import java.util.regex.Matcher;

public class DeckMenu {
    boolean checked = false;

    public void run(String input) {
        checked = false;
        createDeck(Util.getCommand(input, "deck create (\\S+)"));
    }

    public void createDeck(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;

        }
    }

}
