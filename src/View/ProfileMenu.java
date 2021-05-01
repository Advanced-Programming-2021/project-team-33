package View;

import Controller.ProgramController;
import Controller.Util;
import Model.Player;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenu {
    private static String playerName;
    public static Player player;
    boolean checked = false;

    public void run(String input) {
        checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
    }

    public ProfileMenu() {

    }

    public void manage(String currentName) {
        playerName = currentName;
        MainMenu mainMenu = new MainMenu();
        Scanner scanner = Util.scanner;
        Pattern pattern;
        Matcher matcher;
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.matches("profile change --nickname <[.]*>")) {
                pattern = Pattern.compile("profile change --nickname <([.]*)>");
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    changeName(matcher.group(1));
                }
            }
            if (input.matches("profile change --password --current <[.]*> --new <[.]*>")) {
                pattern = Pattern.compile("profile change --password --current <([.]*)> --new <([.]*)>");
                matcher = pattern.matcher(input);
                if (matcher.find()) {
                    changePassword(matcher.group(1), matcher.group(2));
                }
            }
            if(input.matches("exit menu")){
                exitMenu();
            }
        }
    }

    public Player getPlayer(String name) {
        return Player.getUserByUsername(name);
    }

    public boolean isNameExist(String name) {
        if (player == null) return false;
        return true;
    }

    public void changeName(String newName) {
        player = getPlayer(newName);
        if (isNameExist(newName)) {
            System.out.println("user with nickname<" + newName + "> already exists");
        } else {
            System.out.println("nickname changed successfully!");
            player.setNickname(newName);
            playerName = newName;
        }
    }

    public void changePassword(String currentPassword, String newPassword) {
        if (isPasswordTrue(currentPassword)) {
            if(isPasswordEqual(newPassword)){
                System.out.println("please enter a new password");
            }
            else {
                System.out.println("password changed successfully!");
                player = getPlayer(playerName);
                player.setPassword(newPassword);
            }
        } else {
            System.out.println("current password is invalid");
        }
    }

    public static String getUserPassword() {
        player = Player.getUserByUsername(playerName);
        return player.getPassword();
    }

    public static boolean isPasswordTrue(String currentPassword) {
        if (getUserPassword().equals(currentPassword))
            return true;
        return false;
    }

    public static boolean isPasswordEqual(String newPassword) {
        if(newPassword.equals(getUserPassword())) return true;
        return false;
    }

    public static void exitMenu() {
    }
}
