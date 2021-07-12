package View;

import Controller.ProgramController;
import Controller.Util;
import Main.Main;
import Model.Player;
import View.Chatroom.ChatRoom;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.regex.Matcher;

public class MainMenu {
    public static String menu = "login";
    public static boolean checked = false;
    public ImageView backgroundMain;
    public VBox vBox;
    public int layOutY = 1;
    public Label duelError;



    public MainMenu() {
    }

    public void start() throws Exception {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/mainMenuView.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        if(menu.equals("menu")){
            PlayMusic.stop();
            new PlayMusic().start();
        }
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        backgroundMain.setImage(new Image(getClass().getResourceAsStream("/PNG/11025059.jpg")));
        vBox.setAlignment(Pos.CENTER);
        String[] buttonName = {"Duel Menu","Chat Room", "Deck Menu", "Scoreboard Menu", "Profile Menu", "Shop Menu", "Import and Export", "Card Creator", "Log out"};
        for (String name : buttonName) {
            makeButton(name);
        }
    }

    private void makeButton(String name) {
        HBox hBox1 = new HBox();
        hBox1.setMinHeight(20);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Button button = ProgramController.getButton(name);
        switch (name) {
            case "Duel Menu" -> button.setOnMouseClicked(event -> {
                MainMenu.playSound(Util.CLICK_MUSIC);
                startGame();
            });
            case "Chat Room" -> button.setOnMouseClicked(event -> {
                MainMenu.playSound(Util.CLICK_MUSIC);
                try {
                    new ChatRoom().start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            case "Deck Menu" -> button.setOnMouseClicked(event -> {
                MainMenu.playSound(Util.CLICK_MUSIC);
                try {
                    new DeckMenu().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            case "Scoreboard Menu" -> button.setOnMouseClicked(event -> {
                MainMenu.playSound(Util.CLICK_MUSIC);
                try {
                    new ScoreboardMenu().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            case "Profile Menu" -> button.setOnMouseClicked(event -> {
                MainMenu.playSound(Util.CLICK_MUSIC);
                try {
                    new ProfileMenu().start();
                } catch (IOException e) {


                }
            });
            case "Shop Menu" -> button.setOnMouseClicked(event -> {
                MainMenu.playSound(Util.CLICK_MUSIC);
                try {
                    new ShopMenu().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            case "Import and Export" -> button.setOnMouseClicked(event -> {
                MainMenu.playSound(Util.CLICK_MUSIC);
                try {
                    new ImportExportMenu().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            case "Card Creator" -> button.setOnMouseClicked(event -> {
                MainMenu.playSound(Util.CLICK_MUSIC);
                try {
                    new CardCreatorMenu().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            case "Log out" -> button.setOnMouseClicked(event -> {
                MainMenu.playSound(Util.CLICK_MUSIC);
                try {
                    ProgramController.dataOutputStream.writeUTF("logout " + Util.token);
                    ProgramController.dataOutputStream.flush();
                    String result = ProgramController.dataInputStream.readUTF();
                    Util.token = null;
                    Player.thePlayer = null;
                    new Main().start(ProgramController.getStage());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
        hBox.getChildren().add(button);
        vBox.getChildren().addAll(hBox, hBox1);
    }



    public void run(String input) {
        checked = false;
        enterMenu(Util.getCommand(input, "menu enter (\\S+)"));
        showCurrentMenu(Util.getCommand(input, "menu show-current"));
        logout(Util.getCommand(input, "user logout"));
    }

    private void startGame() {
        if (Player.getPlayers().size() < 2) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("there is no other player");
            alert.showAndWait();
        }
        else {
            try {
                new SetGame().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void logout(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            System.out.println("user logged out successfully!");
            menu = "login";
        }
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
            // case "scoreboard" -> scoreboardMenu.start(input);
         //   case "profile" -> profileMenu.run(input);
//            case "importExport" -> importExportMenu.run(input);
            case "Graveyard" -> graveyardMenu.run(input);
        }
    }

    private void showError(String error) {
        duelError.setText(error);
    }

    public static void playSound(String s) {
        String path = s;
        Media media = new Media(new File(path).toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        mediaPlayer.setVolume(100);
        mediaPlayer.setAutoPlay(true);
    }


    public void back(MouseEvent event) throws Exception {
        new Main().start(ProgramController.getStage());
    }
}
