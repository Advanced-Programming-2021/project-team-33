package Controller;

import Model.Player;

import java.util.*;

public class ScoreboardController {
    public static String setScoreBoardList() {
        Map<String, Integer> sortedScoreBoard = createScoreBoard();
        int lineNumber = 1;
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> en : sortedScoreBoard.entrySet()) {
            result.append(lineNumber + "- " + en.getKey() + " : " + en.getValue() + "/");
            lineNumber++;
            if (lineNumber == 21) break;
        }
        return result.toString();
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
}
