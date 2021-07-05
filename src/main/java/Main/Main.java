package Main;
import Controller.CardController;
import Controller.ProgramController;
import View.LoginMenu;
import View.MainMenu;
import View.ProfileMenu;
import View.ScoreboardMenu;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


public class Main extends Application {
    public ImageView backGround;

    public static void main(String[] args) {
        CardController.initialCards();
        launch(args);
    }
    @FXML
    public void initialize(){
        Image image = new Image(getClass().getResource("/PNG/1300546.jpg").toExternalForm());
        backGround.setImage(image);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ProgramController.setStage(primaryStage);
        Parent root = FXMLLoader.load(getClass().getResource("/firstPage.fxml"));
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/PNG/1300571.jpg")));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public void openLoginMenu(MouseEvent mouseEvent) throws Exception {
        LoginMenu.setRegister(false);
       // ProgramController.createUser("username", "nickname", "password");
        //ProgramController.setPlayer("username");
       // new MainMenu().start();
        new LoginMenu().start();
    }

    public void openRegisterMenu(MouseEvent event) throws Exception {
        LoginMenu.setRegister(true);
        new LoginMenu().start();
    }

    public void exit(MouseEvent event) {
        System.exit(0);
    }
}
