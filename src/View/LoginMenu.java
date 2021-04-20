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


        Player player1 = new Player("ali", "123", "ali");
        Player player2 = new Player("reza", "123", "reza");
        Card card1 = new Card("Monster", "sss", 3, CardType.MONSTER);
        Deck deck1 = new Deck("player1");
        deck1.addToMainDeck(card1);
        player1.setActiveDeck(deck1);
        player2.setActiveDeck(deck1);
        GameMenu gameMenu = new GameMenu();
        gameMenu.run(player1, player2);


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

