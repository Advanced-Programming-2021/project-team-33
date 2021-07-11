package View;

import Controller.ProgramController;
import Controller.Util;
import Main.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;

public class LoginMenu {
    private static boolean register;
    public AnchorPane anchorPane;
    public HBox nickNameHBox;
    public Label loginError;
    public TextField textUserName;
    public PasswordField textPassword;
    public Button submit;
    public Button clear;

    public static boolean isRegister() {
        return register;
    }

    public static void setRegister(boolean register) {
        LoginMenu.register = register;
    }

    public void start() throws Exception {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("loginView.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        Image image = new Image(getClass().getResource("/PNG/yu-gi-oh.jpg").toExternalForm());
        ImageView imageView = new ImageView(image);
        anchorPane.getChildren().addAll(imageView);
        TextField textNickName = new TextField();
        if (isRegister()) {
            nickNameHBox.setMaxWidth(270);
            nickNameHBox.setStyle("-fx-background-color: silver");
            Text text = new Text("Nick name : ");
            text.setFont(Font.font(20));
            nickNameHBox.getChildren().addAll(text, textNickName);
            submit.setText("Register");
        } else submit.setText("Login");
        clear.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            textUserName.clear();
            textPassword.clear();
            if (isRegister()) textNickName.clear();
        });
        submit.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            String input = "";
            if (!isRegister()) {
                input = "user login --username " + textUserName.getText() + " --password " + textPassword.getText();
                try {
                    login(Util.getCommand(input, "user login --username (\\S+) --password (\\S+)"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                input = "user create --username " + textUserName.getText() + " --nickname " + textNickName.getText()
                        + " --password " + textPassword.getText();
                try {
                    register(Util.getCommand(input, "user create --username (\\S+)" +
                            " --nickname (\\S+) --password (\\S+)"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void register(Matcher matcher) throws IOException {
        if (matcher.matches()) {
            String username = matcher.group(1);
            String nickname = matcher.group(2);
            String password = matcher.group(3);
            ProgramController.dataOutputStream.writeUTF("register " + username + " " + nickname + " " + password);
            ProgramController.dataOutputStream.flush();
            String result = ProgramController.dataInputStream.readUTF();
            if (result.startsWith("0")) {
                showError(result.substring(2));
            }
            else {
                ProgramController.createUser(username, nickname, password);
                showError(result.substring(2));
            }
        }
    }

    private void login(Matcher matcher) throws Exception {
        if (matcher.matches()) {
            String username = matcher.group(1);
            String password = matcher.group(2);
            ProgramController.dataOutputStream.writeUTF("login " + username + " " + password);
            ProgramController.dataOutputStream.flush();
            String result = ProgramController.dataInputStream.readUTF();
            if (result.startsWith("0")) {
                showError(result.substring(2));
            }
            else {
                ProgramController.setPlayer(username);
                Util.token = result.substring(2);
                new MainMenu().start();
            }
        }
    }

    private void showError(String error) {
        loginError.setText(error);
        loginError.setFont(Font.font(20));
        loginError.setStyle("-fx-background-color: silver");
    }

    private void exit() {
        System.exit(0);
    }

    public void back(MouseEvent event) throws Exception {
        MainMenu.playSound(Util.CLICK_MUSIC);
        new Main().start(ProgramController.getStage());
    }
}

