package View;

import Controller.Util;
import Model.Card;
import Model.Player;

import java.util.regex.Matcher;

public class ChangeCardsMenu {

    private void run() {
        String input = "";
        Communicate.output("find your command in with help.");
        while (!input.equals("done")) {
            input = Util.scanner.nextLine();
            if (input.equals("help")) help();
            DeckMenu.showDeck(Util.getCommand(input, "deck show (-activeDeck)( --side)?"));
            switchMainWithSide(Util.getCommand(input, "deck switch --mainCard (.+?) with --sideCard (.+?)"));
        }
    }

    private void help() {
        System.out.println("deck show (-activeDeck)( --side)?");
        System.out.println("done");
        System.out.println("deck switch --mainCard (.+?) with --sideCard (.+?)");
    }

    public void changeDeck(String playerName) {
        Player.currentPlayer = Player.getUserByUsername(playerName);
        System.out.println(playerName + "'s turn to change card between decks");
        String answer = Communicate.input("Do you want to change your cards? (yes/no)");
        if (answer.equals("yes")) run();
    }

    private void switchMainWithSide(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String mainCard = matcher.group(1);
            String sideCard = matcher.group(2);
            Player.currentPlayer.getActiveDeck().getMainDeck().add(Card.getCardByName(sideCard));
            Player.currentPlayer.getActiveDeck().getSideDeck().remove(Card.getCardByName(sideCard));
            Player.currentPlayer.getActiveDeck().getSideDeck().add(Card.getCardByName(mainCard));
            Player.currentPlayer.getActiveDeck().getMainDeck().remove(Card.getCardByName(mainCard));
        }
    }
}
