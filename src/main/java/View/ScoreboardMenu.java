package View;

import Controller.ProgramController;
import Controller.Util;
import Model.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import jdk.jfr.Frequency;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;

public class ScoreboardMenu {

    public ObservableList<String> items;

    public ScoreboardMenu() {

    }

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("scoreBoardMenu.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        //showScoreBoard(Util.getCommand(input, "scoreboard show"));
        MainMenu.exitMenu(Util.getCommand(input, "menu exit"));
    }

    @FXML
    private void initialize() {
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
            //System.out.println(rank + "- " + en.getKey() + ": " + en.getValue());
            System.out.println(en.getKey());
            Player player = Player.getUserByNickname(en.getKey());
            String rank1 = ""+rank;
            String score = ""+player.getScore();
            equal = en.getValue();
        }
    }


    public void back(MouseEvent event) throws Exception {
        new MainMenu().start();
    }
}

