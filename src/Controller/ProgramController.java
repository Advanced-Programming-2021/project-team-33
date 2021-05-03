package Controller;

import Model.Card;
import Model.Deck;
import Model.Player;
import View.*;
import com.sun.tools.javac.Main;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController {

    public static boolean isUserExist(String user) {
        return Player.getUserByUsername(user) != null;
    }

    public static boolean isNicknameExist(String name) {
        return Player.getUserByNickname(name) != null;
    }

    public static boolean isDeckExist(String deckName) {
        return Player.getDeckByName(deckName) != null;
    }

    public static boolean isCardExist(String cardName) {
        return Card.getCardByName(cardName) != null;
    }

    public static boolean isCardExistInMainDeck(String cardName, String deckName) {
        for (int i = 0; i < Player.getDeckByName(deckName).getMainDeck().size(); i++) {
            if (Player.getDeckByName(deckName).getMainDeck().get(i).getCardName().equals(cardName)) return true;
        }
        return false;
    }

    public static boolean isCardExistInSideDeck(String cardName, String deckName) {
        for (int i = 0; i < Player.getDeckByName(deckName).getSideDeck().size(); i++) {
            if (Player.getDeckByName(deckName).getSideDeck().get(i).getCardName().equals(cardName)) return true;
        }
        return false;
    }

    public static boolean isPasswordMatch(String name, String password) {
        if (Player.getUserByUsername(name).getPassword().equals(password))
            return true;
        return false;
    }

    public static void createUser(String username, String nickname, String password) {
        Player player = new Player(username, password, nickname);
    }

    public static void setPlayer(String username) {
        Player.thePlayer = Player.getUserByUsername(username);
    }

    public static boolean isNavigationPossible(String menuName) {
        return menuName.equals("main") || menuName.equals("deck") ||
                menuName.equals("scoreboard") || menuName.equals("profile") || menuName.equals("importExport");
    }

    public static int compare(int first, int second){
        if(first > second) return 1;
        if(first < second) return -1;
        return 0;
    }


}
