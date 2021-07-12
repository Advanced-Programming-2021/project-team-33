package Controller;

import java.util.ArrayList;

public class GameController {

    static ArrayList<String> waitingUsers = new ArrayList<>();
    static boolean isSecondPlayerCame = false;
    static int count = 0;

    public static String setMultiPlayer(String username) {
        System.out.println(username);
        if (waitingUsers.size() == 0 || (!waitingUsers.get(0).equals(username) && !isSecondPlayerCame)) {
            if(waitingUsers.size() == 1)isSecondPlayerCame = true;
            waitingUsers.add(username);
        }
        if (waitingUsers.size() > 1) {
            if (waitingUsers.get(0).equals(username)) {
                count++;
                return waitingUsers.get(1);
            } else if (waitingUsers.get(1).equals(username)) {
                count++;
                return waitingUsers.get(0);
            }
        }
        if (count == 2) {
            count = 0;
            waitingUsers.clear();
            isSecondPlayerCame = false;
        }
        return "0";
    }

}