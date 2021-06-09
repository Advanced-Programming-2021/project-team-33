package View;

import Controller.ProgramController;
import Controller.Util;
import Model.Player;


import java.util.regex.Matcher;

public class LoginMenu {



    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        register(Util.getCommand(input, "user create --username (\\S+)" +
                " --nickname (\\S+) --password (\\S+)"));
        login(Util.getCommand(input, "user login --username (\\S+) --password (\\S+)"));
    }


    public void register(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String username = matcher.group(1);
            String nickname = matcher.group(2);
            String password = matcher.group(3);
            if (ProgramController.isUserExist(username))
                Communicate.output("user with username " + username + " already exists");
            else if (ProgramController.isNicknameExist(nickname))
                Communicate.output("user with nickname " + nickname + " already exists");
            else {
                ProgramController.createUser(username, nickname, password);
                Communicate.output("user created successfully!");
            }
        }
    }

    private void login(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String username = matcher.group(1);
            String password = matcher.group(2);
            if (!ProgramController.isUserExist(username))
                Communicate.output("Username and password didn't match!");
            else if (!ProgramController.isPasswordMatch(username,password))
                Communicate.output("Username and password didn't match!");
            else {
                ProgramController.setPlayer(username);
                MainMenu.menu = "main";
                Communicate.output("user logged in successfully!");
            }
        }
    }

    private void exit() {
        System.exit(0);
    }

}

