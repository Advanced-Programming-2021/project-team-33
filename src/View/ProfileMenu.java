package View;

import Controller.Util;
import Model.Player;

import java.util.regex.Matcher;

public class ProfileMenu {
    public static Player player;
    boolean checked = false;

    public void run(String input) {
        checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        changeName(Util.getCommand(input, "profile change --nickname (\\S+)"));
        changePassword(Util.getCommand(input, "profile change --password --current (\\S+) --new (\\S+)"));
        exitMenu(Util.getCommand(input, "exit menu"));
    }

    public ProfileMenu() {

    }

    public void changeName(Matcher matcher) {
        if (!checked && matcher.matches()) {
            player = getPlayer(matcher.group(1));
            if (isNameExist()) {
                System.out.println("user with nickname " + matcher.group(1) + " already exists");
            } else {
                System.out.println("nickname changed successfully!");
                player.setNickname(matcher.group(1));
            }
        }
    }

    public Player getPlayer(String name) {
        return Player.getUserByNickname(name);
    }

    public boolean isNameExist() {
        if (player == null) return false;
        return true;
    }

    public void changePassword(Matcher matcher) {
        if (!checked && matcher.matches()) {
            if (isPasswordTrue(matcher.group(1))) {
                if (isPasswordEqual(matcher.group(2))) {
                    System.out.println("please enter a new password");
                } else {
                    System.out.println("password changed successfully!");
                    player.setPassword(matcher.group(2));
                }
            } else {
                System.out.println("current password is invalid");
            }
        }
    }

    public static String getUserPassword() {
        player = Player.thePlayer;
        return player.getPassword();
    }

    public static boolean isPasswordTrue(String currentPassword) {
        if (getUserPassword().equals(currentPassword))
            return true;
        return false;
    }

    public static boolean isPasswordEqual(String newPassword) {
        if (newPassword.equals(getUserPassword())) return true;
        return false;
    }

    public static void exitMenu(Matcher matcher) {
        MainMenu.menu = "main";
    }
}
