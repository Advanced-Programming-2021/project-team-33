package Controller;

import Model.Card;
import Model.Player;

import java.util.*;

public class ProgramController {

    private static int profileID;

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
            if (Player.getDeckByName(deckName).getMainDeck().get(i).equals(cardName)) return true;
        }
        return false;
    }

    public static boolean isCardExistInSideDeck(String cardName, String deckName) {
        for (int i = 0; i < Player.getDeckByName(deckName).getSideDeck().size(); i++) {
            if (Player.getDeckByName(deckName).getSideDeck().get(i).equals(cardName)) return true;
        }
        return false;
    }

    public static boolean isPasswordMatch(String name, String password) {
        if (Player.getUserByUsername(name).getPassword().equals(password))
            return true;
        return false;
    }

    public static void createUser(String username, String nickname, String password) {
        if (profileID >= 50) profileID = 0;
        profileID++;
        Player player = new Player(username, password, nickname);
        player.setProfileID(profileID);
        ShopController.admin = Player.getPlayers().get(0);
    }

    public static void setPlayer(String username) {
        Player.thePlayer = Player.getUserByUsername(username);
    }

    public static boolean isNavigationPossible(String menuName) {
        return menuName.equals("main") || menuName.equals("deck") || menuName.equals("shop")
                || menuName.equals("scoreboard") || menuName.equals("profile") || menuName.equals("importExport");
    }

    public static int compare(int first, int second) {
        if (first > second) return 1;
        if (first < second) return -1;
        return 0;
    }

    public static boolean isPasswordTrue(String currentPassword) {
        if (Player.thePlayer.getPassword().equals(currentPassword)) {
            return true;
        }
        return false;
    }

    public static boolean isPasswordEqual(String newPassword) {
        if (newPassword.equals(Player.thePlayer.getPassword())) return true;
        return false;
    }

    public static void changePlayerNickname(String nickName) {
        Player.thePlayer.setNickname(nickName);
    }

    public static void changePlayerUserName(String username) {
        Player.thePlayer.setUsername(username);
    }

    public static void changePlayerPassword(String password) {
        Player.thePlayer.setPassword(password);
    }

    public static Map<String, Integer> createScoreBoard() {
        ArrayList<Player> listOfPlayers = Player.thePlayer.getPlayers();
        LinkedHashMap<String, Integer> scoreBoard = new LinkedHashMap<String, Integer>();
        String[] nickNameOfPlayers = new String[listOfPlayers.size()];
        for (int i = 0; i < listOfPlayers.size(); i++) {
            nickNameOfPlayers[i] = listOfPlayers.get(i).getNickname();
        }
        Arrays.sort(nickNameOfPlayers);
        Player player;
        for (int i = 0; i < listOfPlayers.size(); i++) {
            player = Player.getUserByNickname(nickNameOfPlayers[i]);
            if (player != null) scoreBoard.put(nickNameOfPlayers[i], player.getScore());
        }
        Map<String, Integer> sortScoreBoard = sortByValue(scoreBoard);
        return sortScoreBoard;
    }

    public static LinkedHashMap<String, Integer> sortByValue(LinkedHashMap<String, Integer> hm) {
        List<Map.Entry<String, Integer>> list =
                new LinkedList<Map.Entry<String, Integer>>(hm.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1,
                               Map.Entry<String, Integer> o2) {
                return (o2.getValue()).compareTo(o1.getValue());
            }
        });

        LinkedHashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
        for (Map.Entry<String, Integer> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }

    public static void setScore(String score, String username) {
        Player.getUserByUsername(username).setScore(Integer.parseInt(score));
    }


}
