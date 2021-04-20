package View;

import Controller.ProgramController;
import Model.Player;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenu {
    public static Player player;

    ProfileMenu() {

    }

    public static void manage() {
        ProgramController programController = new ProgramController();
        Scanner scanner = programController.scanner;
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
                pattern=Pattern.compile("profile change --password --current <([.]*)> --new <([.]*)>");
                matcher= pattern.matcher(input);
                if(matcher.find()){
                    changePassword(matcher.group(1),matcher.group(2));
                }
            }
        }

    }

    public static Player getPlayer(String name) {
        return Player.getUserByUsername(name);
    }

    public static boolean isNameExist(String name) {
        if (player == null) return false;
        return true;
    }

    public static void changeName(String newName) {
        player = getPlayer(newName);
        if (isNameExist(newName)) {
            System.out.println("user with nickname<" + newName + "> already exists");
        } else {
            System.out.println("nickname changed successfully");
            player.setNickname(newName);
        }
    }

    public static void changePassword(String currentPassword,String newPassword) {
        if(isPasswordTrue(currentPassword)){

        }
        else {

        }
    }

    public static String getUserPassword() {
        return "A";
    }

    public static boolean isPasswordTrue(String currentPassword) {
        return true;
    }

    public static boolean isPasswordEqual(String newPassword) {
        return true;
    }

    public static void exitMenu() {

    }
}
