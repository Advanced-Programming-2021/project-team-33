package View;

import Controller.CardController;
import Controller.GameController;
import Controller.ProgramController;
import Controller.Util;

import java.util.regex.Matcher;

public class MainMenu {
    public static String menu = "login";
    boolean checked = false;
    ProgramController programController = new ProgramController();
    GameController gameController = new GameController();

    public MainMenu() {
        CardController.initialCards();
    }

    public void run(String input) {
        checked = false;
        startGame(Util.getCommand(input, "duel --new --second-player (\\S+) --rounds (\\d+)"));
        logout(Util.getCommand(input, "user logout"));
    }

    private void startGame(Matcher matcher) {
        if(!checked && matcher.matches()){
            checked = true;
            String secondPlayerName = matcher.group(1);
            int round = Integer.parseInt(matcher.group(2));
            if (!secondPlayerName.equals("ai") && !programController.isUserExist(secondPlayerName))
                System.out.println("there is no player with this username");
            else if (!secondPlayerName.equals("ai") && !gameController.isDeckActive(secondPlayerName))
                System.out.println(secondPlayerName + " has no active deck");
            else if (!secondPlayerName.equals("ai") && !gameController.isDeckValid(secondPlayerName))
                System.out.println(secondPlayerName + "'s deck is invalid");
            else if (round > 3 || round < 1)
                System.out.println("number of rounds is not supported");
            else if (secondPlayerName.equals("ai")) duelWithAi();
            else menu = "game";
        }
    }

    private void logout(Matcher matcher){
        if(!checked && matcher.matches()){
            checked = true;
            System.out.println("user logged out successfully!");
            menu = "login";
        }
    }

    private void duelWithAi() {
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
            input = Util.scanner.nextLine();
            input = input.trim();
            switch (menu) {
                case "login" -> loginMenu.run(input);
                case "main" -> mainMenu.run(input);
                case "game" -> gameMenu.run(input);
                case "deck" -> deckMenu.run(input);
                case "shop" -> shopMenu.run(input);
                case "scoreboard" -> scoreboardMenu.run(input);
                case "profile" -> profileMenu.run(input);
                case "importExport" -> importExportMenu.run(input);
            }
        }
    }


}
