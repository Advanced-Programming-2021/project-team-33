package View;

import Controller.ProgramController;
import Controller.Util;


import java.util.regex.Matcher;

public class LoginMenu {
    boolean checked = false;

    public void run(String input) {
        checked = false;
        register(Util.getCommand(input, "user create --username (\\S+)" +
                " --nickname (\\S+) --password (\\S+)"));
        login(Util.getCommand(input, "user login --username (\\S+) --password (\\S+)"));
    }

    private void register(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            String username = matcher.group(1);
            String nickname = matcher.group(2);
            String password = matcher.group(3);
            if (ProgramController.isUserExist(username))
                System.out.println("user with username " + username + " already exists");
            else if (ProgramController.isNicknameExist(nickname))
                System.out.println("user with nickname " + nickname + " already exists");
            else {
                ProgramController.createUser(username, nickname, password);
                System.out.println("user created successfully!");
            }
        }
    }

    private void login(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            String username = matcher.group(1);
            String password = matcher.group(2);
            if (!ProgramController.isUserExist(username))
                System.out.println("Username and password didn't match!");
            else if (!ProgramController.isPasswordMatch(username,password))
                System.out.println("Username and password didn't match!");
            else {
                ProgramController.setPlayer(username);
                MainMenu.menu = "main";
                System.out.println("user logged in successfully!");
            }
        }
    }

    private void exit() {
        System.exit(0);
    }

}

