package Controller;
import View.*;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProgramController {
    public static Scanner scanner=new Scanner(System.in);
    public static String menu = "login";

    public static Matcher getCommand(String input, String regex) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);
        return matcher;
    }

    public void enterMenus() {
        LoginMenu loginMenu = new LoginMenu();
        DeckMenu deckMenu = new DeckMenu();
        GameMenu gameMenu = new GameMenu();
        ImportExportMenu importExportMenu = new ImportExportMenu();
        MainMenu mainMenu = new MainMenu();
        ProfileMenu profileMenu = new ProfileMenu();
        ScoreboardMenu scoreboardMenu = new ScoreboardMenu();
        ShopMenu shopMenu = new ShopMenu();
        String input;
        while (true) {
            input = scanner.nextLine();
            input = input.trim();
            switch (menu) {
                case "login" -> loginMenu.run(input);
                case "main" -> mainMenu.run(input);
            }
        }
    }

}
