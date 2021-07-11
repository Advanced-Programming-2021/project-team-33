package View;

import Controller.ProgramController;
import Model.Player;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.*;

public class ScoreboardMenu {

    public ImageView backGround;
    public ListView scoreBoard;

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
        ObservableList<String> scores = FXCollections.observableArrayList(setScoreBoardList());
        scoreBoard.setItems(scores);
        scoreBoard.setCellFactory(cell -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item != null) {
                    setText(item);
                    String regex = "\\d+- (" + Player.thePlayer.getNickname() + ") : \\d+";
                    if (item.matches(regex)) {
                        setTextFill(Color.WHITE);
                        setBackground(new Background(new BackgroundFill(Color.GREEN, CornerRadii.EMPTY, Insets.EMPTY)));
                    }
                }
            }
        });
    }

    public List<String> setScoreBoardList() {
        Map<String, Integer> sortedScoreBoard = ProgramController.createScoreBoard();
        ArrayList<String> listOfPlayers = new ArrayList<>();
        int lineNumber = 1;
        for (Map.Entry<String, Integer> en : sortedScoreBoard.entrySet()) {
            listOfPlayers.add(lineNumber + "- " + en.getKey() + " : " + en.getValue());
            lineNumber++;
            if (lineNumber == 21) break;
        }
        return listOfPlayers;
    }

    public void back(MouseEvent event) throws Exception {
        new MainMenu().start();
    }
}

