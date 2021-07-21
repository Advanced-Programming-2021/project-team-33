package View;

import Controller.ProgramController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class Lobby {

    public Text name1, name2, score1, score2, ready;
    public static String playerName1, playerName2,playerNick1,playerNick2, playerScore1,playerScore2,profileId1,profileId2;

    public void start() throws Exception {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/lobby.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        name1.setText(playerName1);
        name2.setText(playerName2);
        score1.setText(playerScore1);
        score2.setText(playerScore2);
        ready.setOnMouseClicked(event -> {
            try {
                checkReady();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

    }

    public void checkReady() throws IOException {

        String result = "0";
        boolean isCheck = true;
        while (isCheck) {
            ProgramController.dataOutputStream.writeUTF("ready " + playerName1 + " " + playerName2);
            ProgramController.dataOutputStream.flush();
            result = ProgramController.dataInputStream.readUTF();

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (result.equals("1")) {
                isCheck = false;
            }
        }
        FlipCoin.autoFlip = true;
        new FlipCoin().start();
    }
}