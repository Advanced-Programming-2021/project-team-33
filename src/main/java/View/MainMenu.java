package View;

import Controller.*;
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
            if (!(round == 3 || round == 1))
                System.out.println("number of rounds is not supported");
            else if (!secondPlayerName.equals("ai")) {
                if (!programController.isUserExist(secondPlayerName) ||
                        secondPlayerName.equals(Player.thePlayer.getUsername()))
                    System.out.println("there is no player with this username");
                else if (!gameController.isDeckActive(Player.thePlayer.getUsername()))
                    System.out.println(Player.thePlayer.getUsername() + " has no active deck");
                else if (!gameController.isDeckActive(secondPlayerName))
                    System.out.println(secondPlayerName + " has no active deck");
                else if (!gameController.isDeckValid(Player.thePlayer.getUsername()))
                    System.out.println(Player.thePlayer.getUsername() + "'s deck is invalid");
                else if (!gameController.isDeckValid(secondPlayerName))
                    System.out.println(secondPlayerName + "'s deck is invalid");
                else {
                    GameController.initiateGame(Player.thePlayer.getUsername(), secondPlayerName, round);
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
        menu = "game";
        Ai.initiateGameWithAi(Player.thePlayer.getUsername(), 3);
    }

    private void enterMenu(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            String menuName = matcher.group(1);
            if (menuName.equals("game")) Communicate.output("you have to set you opponent to enter this menu");
            else if (ProgramController.isNavigationPossible(menuName))
                menu = menuName;
            else Communicate.output("no such menu!");
        }
    }

    public static void showCurrentMenu(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            switch (menu) {
                case "login" -> Communicate.output("Login Menu");
                case "main" -> Communicate.output("Main Menu");
                case "game" -> Communicate.output("Game Menu");
                case "deck" -> Communicate.output("Deck Menu");
                case "shop" -> Communicate.output("Shop Menu");
                case "scoreboard" -> Communicate.output("Scoreboard Menu");
                case "profile" -> Communicate.output("Profile Menu");
                case "importExport" -> Communicate.output("ImportExport Menu");
                case "Graveyard" -> Communicate.output("Graveyard Menu");
            }
        }
    }

    public static void  exitMenu(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            MainMenu.menu = "main";
        }
    }

    public void menu() {
        LoginMenu loginMenu = new LoginMenu();
        DeckMenu deckMenu = new DeckMenu();
        ImportExportMenu importExportMenu = new ImportExportMenu();
        MainMenu mainMenu = new MainMenu();
        ProfileMenu profileMenu = new ProfileMenu();
        ScoreboardMenu scoreboardMenu = new ScoreboardMenu();
        GraveyardMenu graveyardMenu = new GraveyardMenu();
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
                case "Graveyard" -> graveyardMenu.run(input);
            }
            if(!checked) Communicate.output("invalid command!");
        }
    }


}
