package Controller;

import Model.Player;
import View.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController {

    public boolean isUserExist(String user) {
        return Player.getUserByUsername(user) != null;
    }


}
