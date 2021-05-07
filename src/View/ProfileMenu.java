package View;

import Controller.ProgramController;
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
        exitMenu(Util.getCommand(input, "menu exit"));
    }

    public ProfileMenu() {

    }

    public void changeName(Matcher matcher) {
        if (!checked && matcher.matches()) {
            player = ProgramController.getPlayerByNickname(matcher.group(1));
            if (player != null) {
                System.out.println("user with nickname " + matcher.group(1) + " already exists");
            } else {
                System.out.println("nickname changed successfully!");
                ProgramController.changePlayerNickname(matcher.group(1));
            }
        }
    }

    public void changePassword(Matcher matcher) {
        if (!checked && matcher.matches()) {
            if (ProgramController.isPasswordTrue(matcher.group(1))) {
                if (ProgramController.isPasswordEqual(matcher.group(2))) {
                    System.out.println("please enter a new password");
                } else {
                    System.out.println("password changed successfully!");
                    ProgramController.changePlayerPassword(matcher.group(2));
                }
            } else {
                System.out.println("current password is invalid");
            }
        }
    }

    public void exitMenu(Matcher matcher) {
        if (!checked && matcher.matches()) {
            MainMenu.checked = true;
            MainMenu.menu = "main";
        }
    }
}
