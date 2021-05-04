package View;

import Controller.CardController;
import Controller.GameController;
import Controller.ProgramController;
import Controller.Util;
import Model.Card;
import Model.Player;

import java.util.regex.Matcher;

public class MainMenu {
    public static String menu = "login";
    public static boolean checked = false;
    ProgramController programController = new ProgramController();
    GameController gameController = new GameController();

    public MainMenu() {
    }

    public void run(String input) {
        checked = false;
        startGame(Util.getCommand(input, "duel --new --second-player (\\S+) --rounds (\\d+)"));
        enterMenu(Util.getCommand(input, "menu enter (\\S+)"));
        showCurrentMenu(Util.getCommand(input, "menu show-current"));
        logout(Util.getCommand(input, "user logout"));
    }

    private void startGame(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            String secondPlayerName = matcher.group(1);
            int round = Integer.parseInt(matcher.group(2));
            if (round > 3 || round < 1)
                System.out.println("number of rounds is not supported");
            if (!secondPlayerName.equals("ai")) {
                if (!programController.isUserExist(secondPlayerName) ||
                        secondPlayerName.equals(Player.thePlayer.getUsername()))
                    System.out.println("there is no player with this username");
                else if (!gameController.isDeckActive(Player.thePlayer.getUsername()))
                    System.out.println(Player.thePlayer.getUsername() + " has no active deck");
                else if (!gameController.isDeckActive(secondPlayerName))
                    System.out.println(secondPlayerName + " has no active deck");
                else if (!gameController.isDeckValid(secondPlayerName))
                    System.out.println(secondPlayerName + "'s deck is invalid");
                else {
                    GameController.initiateGame(Player.thePlayer.getUsername(), secondPlayerName);
                    menu = "game";
                }
            } else duelWithAi();
        }
    }

    private void logout(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            System.out.println("user logged out successfully!");
            menu = "login";
        }
    }

    private void duelWithAi() {
    }

    private void enterMenu(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            String menuName = matcher.group(1);
            if (ProgramController.isNavigationPossible(menuName))
                menu = menuName;
        }
    }

    public static void showCurrentMenu(Matcher matcher) {
        if (matcher.matches()) {
            switch (menu) {
                case "login" -> System.out.println("Login Menu");
                case "main" -> System.out.println("Main Menu");
                case "game" -> System.out.println("Game Menu");
                case "deck" -> System.out.println("Deck Menu");
                case "shop" -> System.out.println("Shop Menu");
                case "scoreboard" -> System.out.println("Scoreboard Menu");
                case "profile" -> System.out.println("Profile Menu");
                case "importExport" -> System.out.println("ImportExport Menu");
            }
        }
    }

    public void menu() {
        LoginMenu loginMenu = new LoginMenu();
        DeckMenu deckMenu = new DeckMenu();
        ImportExportMenu importExportMenu = new ImportExportMenu();
        MainMenu mainMenu = new MainMenu();
        ProfileMenu profileMenu = new ProfileMenu();
        ScoreboardMenu scoreboardMenu = new ScoreboardMenu();
        GameMenu gameMenu = new GameMenu();
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
