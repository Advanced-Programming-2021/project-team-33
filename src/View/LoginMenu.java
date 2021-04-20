package View;

import Controller.ProgramController;
import Controller.Util;
import Model.Card;
import Model.CardType;
import Model.Deck;
import Model.Player;


import java.util.regex.Matcher;

public class LoginMenu {
    boolean checked = false;
    public void run(String input) {
        checked = false;
        register(ProgramController.getCommand(input, "user create --username (?<username>\\S+)" +
                " --nickname (?<nickname>\\S+) -- password (?<password>\\S+)"));
        login(ProgramController.getCommand(input, "s(d)"));


    }

    private void register(Matcher matcher) {
        if(!checked && matcher.matches()){
            checked = true;
            System.out.println(2);
        }

    }

    private void login(Matcher matcher) {
        if(!checked && matcher.matches()){
            System.out.println(matcher.group(1));
        }
    }

    private void exit() {
        System.exit(0);
    }

}

