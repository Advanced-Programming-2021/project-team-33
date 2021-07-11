package Controller;

import javafx.scene.image.Image;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    public static Scanner scanner = new Scanner(System.in);
    public static String CLICK_MUSIC = "src/main/resources/music/click.mp3";
    private static Util instance = null;

    public static Util getInstance() {
        if (instance == null)
            instance = new Util();
        return instance;
    }

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

    public static Image getImage(String cardName) {
        Image image;
        if (getInstance().getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                cardName.replaceAll("\\s+", "") + ".jpg") == null)
            image = new Image(getInstance().getClass().getResourceAsStream("/PNG/Cards/Monsters/newCard.jpg"));
        else
        image = new Image(getInstance().getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                cardName.replaceAll("\\s+", "") + ".jpg"));
        return image;
    }
}
