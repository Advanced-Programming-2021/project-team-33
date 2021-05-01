package Controller;

import Model.Player;
import View.*;

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

    public static boolean isPasswordMatch(String name, String password) {
        if(Player.getUserByUsername(name).getPassword().equals(password))
            return true;
        return false;
    }

    public static void createUser(String username, String nickname, String password){
        Player player = new Player(username, password, nickname);
    }


}
