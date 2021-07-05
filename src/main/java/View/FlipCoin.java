package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.RoundController;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;


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
            tail.setImage(null);
            head.setImage(null);
            if (number == 1) {
                RoundController.changeTurn();
                coin.setImage(new Image(getClass().getResourceAsStream("/PNG/Gold_21.png")));
                text.setText("Your opponent plays first");
            } else {
                coin.setImage(new Image(getClass().getResourceAsStream("/PNG/Gold_1.png")));
                text.setText("You play first");
            }
        });
        head.setOnMouseClicked(event -> {
            head.setImage(null);
            tail.setImage(null);
            if (number == 0) {
                RoundController.changeTurn();
                coin.setImage(new Image(getClass().getResourceAsStream("/PNG/Gold_1.png")));
                text.setText("Your opponent plays first");
            } else {
                coin.setImage(new Image(getClass().getResourceAsStream("/PNG/Gold_21.png")));
                text.setText("You play first");
            }
        });
        continueButton.setOnMouseClicked(event -> {
            GameController.prepareGame();
            try {
                new GameMenu().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
