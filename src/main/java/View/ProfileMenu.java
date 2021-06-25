package View;

import Controller.ProgramController;
import Controller.Util;
import Model.Player;

import java.util.regex.Matcher;

public class ProfileMenu {

    public ProfileMenu() {

    }

    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        changeNickName(Util.getCommand(input, "profile change --nickname (\\S+)"));
        changeUserName(Util.getCommand(input,"profile change --username (\\S+)"));
        changePassword(Util.getCommand(input, "profile change --password --current (\\S+) --new (\\S+)"));
        MainMenu.exitMenu(Util.getCommand(input, "menu exit"));
    }

    private void changeNickName(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            Player player = Player.getUserByNickname(matcher.group(1));
            if (player != null) {
                Communicate.output("user with nickname " + matcher.group(1) + " already exists");
            } else {
                Communicate.output("nickname changed successfully!");
                ProgramController.changePlayerNickname(matcher.group(1));
            }
        }
    }

    private void changeUserName(Matcher matcher){
        if(!MainMenu.checked&&matcher.matches()){
            MainMenu.checked=true;
            Player player = Player.getUserByUsername(matcher.group(1));
            if (player != null) {
                Communicate.output("user with username " + matcher.group(1) + " already exists");
            } else {
                Communicate.output("username changed successfully!");
                ProgramController.changePlayerUserName(matcher.group(1));
            }
        }
    }

    private void changePassword(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (ProgramController.isPasswordTrue(matcher.group(1))) {
                if (ProgramController.isPasswordEqual(matcher.group(2))) {
                    Communicate.output("please enter a new password");
                } else {
                    Communicate.output("password changed successfully!");
                    ProgramController.changePlayerPassword(matcher.group(2));
                }
            } else {
                Communicate.output("current password is invalid");
            }
        }
    }

}
