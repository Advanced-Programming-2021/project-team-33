package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.RoundController;
import Controller.Util;
import Model.Player;
import javafx.animation.FadeTransition;
import javafx.animation.PathTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
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

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("flipCoin.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
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
