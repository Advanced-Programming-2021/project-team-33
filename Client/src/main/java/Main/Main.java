package Main;

import Controller.CardController;
import Controller.ProgramController;
import Controller.Util;
import View.LoginMenu;
import View.MainMenu;
import View.PlayMusic;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {
    public ImageView backGround;
    public ImageView foreGround;
    public Rectangle bg2;
    public Text register, login, exit;
    public ImageView title;
    public Rectangle bg21;
    static boolean firstTime = true;
    public ImageView gradiant,gradiant1,gradiant2;

    public static void main(String[] args) {
        ProgramController.initializeNetwork();
        CardController.initialCards();
        MainMenu.menu = "start";
        new PlayMusic().start();
        launch(args);
    }

    @FXML
    public void initialize() {
        gradiant.setImage(null);
        gradiant1.setImage(null);
        gradiant2.setImage(null);
        register.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {
               gradiant.setImage(new Image(getClass().getResourceAsStream("/PNG/gradiant.jpg")));

            }
        });
        register.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {
                gradiant.setImage(null);

            }
        });
        login.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                gradiant1.setImage(new Image(getClass().getResourceAsStream("/PNG/gradiant.jpg")));

            }
        });
        login.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                gradiant1.setImage(null);

            }
        });
        exit.setOnMouseEntered(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent t) {

                gradiant2.setImage(new Image(getClass().getResourceAsStream("/PNG/gradiant.jpg")));
            }
        });
        exit.setOnMouseExited(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent t) {

                gradiant2.setImage(null);
            }
        });
        if (firstTime) {
            FadeTransition fade = new FadeTransition();
            FadeTransition fade1 = new FadeTransition();
            FadeTransition fade2 = new FadeTransition();
            FadeTransition fade3 = new FadeTransition();
            FadeTransition fade4 = new FadeTransition();
            animation(fade2, register);
            animation(fade3, login);
            animation(fade, exit);
            animation2(fade4, title);
            animation1(fade1);
            fade1.setDuration(Duration.millis(4000));
            fade1.setFromValue(10);
            fade1.setToValue(0);
            fade1.setAutoReverse(true);
            fade1.setNode(bg21);
            fade1.play();
            backGround.toBack();
            fade();
        } else {
            bg2.toBack();
            bg21.toBack();
        }
        firstTime = false;


    }

    private void animation(FadeTransition fade, Text text) {
        fade.setDuration(Duration.millis(4000));
        fade.setFromValue(0);
        fade.setToValue(10);
        fade.setAutoReverse(true);
        fade.setNode(text);
        fade.play();
    }

    private void animation2(FadeTransition fade, ImageView imageView) {
        fade.setDuration(Duration.millis(3000));
        fade.setFromValue(0);
        fade.setToValue(10);
        fade.setAutoReverse(true);
        fade.setNode(imageView);
        fade.play();
    }

    private void animation1(FadeTransition fade1) {
        fade1.setDuration(Duration.millis(3000));
        fade1.setFromValue(10);
        fade1.setToValue(0);
        fade1.setAutoReverse(true);
        fade1.setNode(bg21);
        fade1.play();
    }

    private void fade() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        FadeTransition fade = new FadeTransition();
                        fade.setDuration(Duration.millis(3000));
                        fade.setFromValue(10);
                        fade.setToValue(0);
                        fade.setAutoReverse(true);
                        fade.setNode(bg2);
                        fade.play();

                    }
                },
                2000
        );

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
        MainMenu.playSound(Util.CLICK_MUSIC);
        LoginMenu.setRegister(false);
        new LoginMenu().start();
    }


    public void openRegisterMenu(MouseEvent event) throws Exception {
        MainMenu.playSound(Util.CLICK_MUSIC);
        LoginMenu.setRegister(true);
        new LoginMenu().start();
    }

    public void exit(MouseEvent event) {
        MainMenu.playSound(Util.CLICK_MUSIC);
        System.exit(0);
    }
}
