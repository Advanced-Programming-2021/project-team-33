package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.Util;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;



public class SetWinner {
    public Text massage;
    public ImageView goNext;

    public void start() throws Exception {
        MainMenu.menu = "menu";
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("setWinner.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        massage.setText(Player.currentPlayer.getUsername() + " won the whole match with score: " + GameController.score + " - 0\n\n");
        goNext.setOnMouseClicked(event ->{
            MainMenu.playSound(Util.CLICK_MUSIC);
            try {
                new MainMenu().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }
}
