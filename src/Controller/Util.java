package Controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static Matcher getCommand(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }
}
