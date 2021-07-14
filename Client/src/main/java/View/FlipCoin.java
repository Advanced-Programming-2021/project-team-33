package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.RoundController;
import Controller.Util;
import Model.Card;
import Model.Deck;
import Model.Player;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

public class FlipCoin {


    public ImageView tail;
    public ImageView head;
    public ImageView coin;
    public ImageView continueButton;
    public Text text;
    public static boolean autoFlip = false;
    static String player1;

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/flipCoin.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() throws IOException {
        if (autoFlip) {
            int number = 1;
            flipCoin();
            MainMenu.playSound("src/main/resources/music/coin.mp3");
            head.setImage(null);
            if (number == 1) {
                setAnimationForCoin(620);
                ProgramController.dataOutputStream.writeUTF("player1");
                ProgramController.dataOutputStream.flush();
                player1 = ProgramController.dataInputStream.readUTF();
                text.setText(player1 + " plays first");
                setSecondPlayer();
            } else {
                setAnimationForCoin(620);
                ProgramController.dataOutputStream.writeUTF("player2");
                ProgramController.dataOutputStream.flush();
                player1 = ProgramController.dataInputStream.readUTF();
                text.setText(player1 + " plays first");
                setSecondPlayer();
            }
            autoFlip = false;
        }
        Random random = new Random();
        int number = random.nextInt(2);
        tail.setOnMouseClicked(event -> {
            flipCoin();
            MainMenu.playSound("src/main/resources/music/coin.mp3");
            head.setImage(null);

            if (number == 1) {
                setAnimationForCoin(620);
                RoundController.changeTurn();
                text.setText("Your opponent plays first");
            } else {
                setAnimationForCoin(620);
                text.setText("You play first");
            }
        });
        head.setOnMouseClicked(event -> {
            MainMenu.playSound("src/main/resources/music/coin.mp3");
            head.setImage(null);
            tail.setImage(null);
            if (number == 0) {
                setAnimationForCoin(620);
                RoundController.changeTurn();
                text.setText("Your opponent plays first");
            } else {
                setAnimationForCoin(620);
                coin.setImage(new Image(getClass().getResourceAsStream("/PNG/Gold_21.png")));
                text.setText("You play first");
            }
        });
        continueButton.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            GameController.prepareGame();
            try {
                new GameMenu().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }


    private void setSecondPlayer() throws IOException {
        Player secondPlayer = new Player(Lobby.playerName2, Lobby.playerNick2);
        ProgramController.dataOutputStream.writeUTF("profileId " + secondPlayer.getUsername());
        ProgramController.dataOutputStream.flush();
        secondPlayer.setProfileID(Integer.parseInt(ProgramController.dataInputStream.readUTF()));
        Deck deck1 = new Deck("deck22");
        secondPlayer.addToDeckList(deck1);
        deck1.setDeckActive(true);

        ProgramController.dataOutputStream.writeUTF("getDeck " + secondPlayer.getUsername());
        ProgramController.dataOutputStream.flush();
        String result = ProgramController.dataInputStream.readUTF();
        String[] parts = result.split(", ");

        for (int i = 0; i < parts.length; i++) {
            String s = parts[i].replaceAll("]", "").replaceAll("\\[", "");
            secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName(s));
        }
        Deck deck = new Deck("deck11");
        Player.thePlayer.addToDeckList(deck);
        deck.setDeckActive(true);
        ProgramController.dataOutputStream.writeUTF("getDeck " + Player.thePlayer.getUsername());
        ProgramController.dataOutputStream.flush();
        result = ProgramController.dataInputStream.readUTF();
        String[] parts2 = result.split(", ");
        for (int i = 0; i < parts2.length; i++) {
            String s = parts2[i].replaceAll("]", "").replaceAll("\\[", "");
            System.out.println(s);
            Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName(s));
        }
        if (player1.equals(secondPlayer.getUsername())) {
            Player.opponent = Player.thePlayer;
            Player.currentPlayer = secondPlayer;
        } else {
            Player.opponent = secondPlayer;
            Player.currentPlayer = Player.thePlayer;
        }
        RoundController.otherPlayer = secondPlayer;
        RoundController.setRound(SetGame.rounds);
        GameController.prepareGame();
        GameMenu.sendPlayer();
        try {
            new GameMenu().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void flipCoin() {
        ScaleTransition trans = new ScaleTransition();
        trans.setDuration(Duration.millis(300));
        trans.setToX(0);
        trans.setAutoReverse(true);
        trans.setNode(tail);
        trans.play();

    }


    private void setAnimationForCoin(int i) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        coin.setLayoutX(-100);
                        coin.setLayoutY(-100);
                        coin.setImage(new Image(getClass().getResourceAsStream("/PNG/Gold_1.png")));

                        TranslateTransition trans = new TranslateTransition();
                        trans.setDuration(Duration.millis(500));
                        trans.setFromX(i);
                        trans.setFromY(-100);
                        trans.setToX(i);
                        trans.setToY(360);
                        trans.setAutoReverse(true);
                        trans.setNode(coin);
                        trans.play();
                    }
                },
                300
        );

    }
}
