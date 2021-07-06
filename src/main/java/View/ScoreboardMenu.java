package View;

import Controller.ProgramController;
import Model.DefineDataModel;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class ScoreboardMenu {

    public ImageView backGround;
    public ObservableList<DefineDataModel> items;

    public ScoreboardMenu() {

    }

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("scoreBoardMenu.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh (Score Board)");

        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    private void initialize() {
        Image image = new Image(getClass().getResource("/PNG/scoreBoardBackground.jpg").toExternalForm());
        backGround.setImage(image);
        int rank = 0, counter = 0, equal = 0, a = 0;
        Map<String, Integer> scoreBoard = ProgramController.createScoreBoard();
        for (Map.Entry<String, Integer> en : scoreBoard.entrySet()) {
            a++;
            if (en.getValue() != equal) {
                rank++;
                rank += counter;
                counter = 0;
            } else {
                counter++;
                if (a == 1) rank++;
            }
            items.add(new DefineDataModel(String.valueOf(rank), en.getKey(), String.valueOf(en.getValue())));
            equal = en.getValue();
            if (a == 20) break;
        }
    }


    public void back(MouseEvent event) throws Exception {
        new MainMenu().start();
    }
}

