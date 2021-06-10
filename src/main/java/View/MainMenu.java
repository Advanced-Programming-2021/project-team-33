package View;

import Controller.*;
import Main.Main;
import Model.Card;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.TriangleMesh;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;

public class MainMenu {
    public static String menu = "login";
    public static boolean checked = false;
    public ImageView backgroundMain;
    public VBox vBox;
    public int layOutY = 1;
    ProgramController programController = new ProgramController();
    GameController gameController = new GameController();

    public MainMenu() {
    }

    public void start() throws Exception {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("mainMenuView.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        backgroundMain.setImage(new Image(getClass().getResourceAsStream("/PNG/11025059.jpg")));
        vBox.setAlignment(Pos.CENTER);
        makeButton("Duel Menu");
        makeButton("Deck Menu");
        makeButton("Scoreboard Menu");
        makeButton("Profile Menu");
        makeButton("Shop Menu");
        makeButton("Log out");
    }

    private void makeButton(String name) {
        HBox hBox1 = new HBox();
        hBox1.setMinHeight(20);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Button button = getButton(name);
        switch (name) {
            case "Duel Menu" -> button.setOnMouseClicked(event -> System.out.println("Yes"));
            case "Deck Menu" -> button.setOnMouseClicked(event -> new DeckMenu().start());
            case "Scoreboard Menu" -> button.setOnMouseClicked(event -> {
                try {
                    new ScoreboardMenu().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            case "Profile Menu" -> button.setOnMouseClicked(event -> new ProfileMenu().start());
            case "Shop Menu" -> button.setOnMouseClicked(event -> {
                try {
                    new ShopMenu().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            case "Log out" -> button.setOnMouseClicked(event -> {
                try {
                    new Main().start(ProgramController.getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        hBox.getChildren().add(button);
        vBox.getChildren().addAll(hBox, hBox1);
    }

    private Button getButton(String name) {
        Button button = new Button(name);
        button.setShape(new Circle(5));
        button.setFont(Font.font(22));
        button.setOnMouseEntered(event -> {
            button.setEffect(new DropShadow());
        });
        button.setOnMouseExited(event -> {
            button.setEffect(null);
        });
        return button;
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
        Ai.initiateGameWithAi(Player.thePlayer.getUsername(), 3);
    }

    private void enterMenu(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            String menuName = matcher.group(1);
            if (menuName.equals("game")) System.out.println("you have to set you opponent to enter this menu");
            else if (ProgramController.isNavigationPossible(menuName))
                menu = menuName;
            else System.out.println("no such menu!");
        }
    }

    public static void showCurrentMenu(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            switch (menu) {
                case "login" -> System.out.println("Login Menu");
                case "main" -> System.out.println("Main Menu");
                case "game" -> System.out.println("Game Menu");
                case "deck" -> System.out.println("Deck Menu");
                case "shop" -> System.out.println("Shop Menu");
                case "scoreboard" -> System.out.println("Scoreboard Menu");
                case "profile" -> System.out.println("Profile Menu");
                case "importExport" -> System.out.println("ImportExport Menu");
                case "Graveyard" -> System.out.println("Graveyard Menu");
            }
        }
    }

    public static void exitMenu(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            MainMenu.menu = "main";
        }
    }

    public void menu() throws Exception {
        LoginMenu loginMenu = new LoginMenu();
        DeckMenu deckMenu = new DeckMenu();
        ImportExportMenu importExportMenu = new ImportExportMenu();
        MainMenu mainMenu = new MainMenu();
        ProfileMenu profileMenu = new ProfileMenu();
        ScoreboardMenu scoreboardMenu = new ScoreboardMenu();
        GraveyardMenu graveyardMenu = new GraveyardMenu();
        GameMenu gameMenu = new GameMenu();
        ShopMenu shopMenu = new ShopMenu();
        String input = "";

        switch (menu) {
            case "login" -> loginMenu.start();
            case "main" -> mainMenu.run(input);
            case "game" -> gameMenu.run(input);
            case "deck" -> deckMenu.run(input);
            case "shop" -> shopMenu.run(input);
            case "scoreboard" -> scoreboardMenu.run(input);
            case "profile" -> profileMenu.run(input);
            case "importExport" -> importExportMenu.run(input);
            case "Graveyard" -> graveyardMenu.run(input);
        }
    }

    public void back(MouseEvent event) throws Exception {
        new Main().start(ProgramController.getStage());
    }
}
