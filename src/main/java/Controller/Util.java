package Controller;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static Scanner scanner = new Scanner(System.in);
    public static String CLICK_MUSIC = "src/main/resources/music/click.mp3";

    public static Matcher getCommand(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    public static void printNCharacter(int n, String s) {
        for (int i = 0; i < n; i++) {
            System.out.print(s);
        }
    }
}
