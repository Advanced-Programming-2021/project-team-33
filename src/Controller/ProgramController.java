package Controller;

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




}
