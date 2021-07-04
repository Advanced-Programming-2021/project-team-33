package View;

import Controller.ProgramController;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

public class ProfileMenu {

    public ImageView background;
    public VBox vBoxInProfileMenu;
    public Circle profile;
    public Label userName;
    public Label nickName;
    public Label showError;
    private TextField newNickName;
    private TextField currentPassword;
    private TextField newPassword;
    private String style;
    private HBox hBox2;

    public ProfileMenu() {

    }

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("profileMenu.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        loadImageForProfile();
        background.setImage(new Image(getClass().getResourceAsStream("/PNG/backGroundForProfileMenu.jpg")));
        style = "-fx-font-size: 28;-fx-background-color: silver";
        userName.setText("Username : " + Player.thePlayer.getUsername());
        userName.setStyle(style);
        nickName.setText("Nickname : " + Player.thePlayer.getNickname());
        nickName.setStyle(style);
        String[] buttonName = {"Change Nickname", "Change Password", "Change Profile"};
        for (String name : buttonName) {
            makeButton(name);
        }
    }

    private void makeButton(String name) {
        HBox hBox1 = new HBox();
        hBox1.setMinHeight(20);
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        Button button = ProgramController.getButton(name);
        hBox2 = new HBox();
        hBox2.setAlignment(Pos.CENTER);
        hBox2.setMinHeight(100);
        switch (name) {
            case "Change Profile" -> button.setOnMouseClicked(event -> {
                changeProfile();
            });
            case "Change Nickname" -> button.setOnMouseClicked(event -> {
                setScene("nickname");
            });
            case "Change Password" -> button.setOnMouseClicked(event -> {
                setScene("password");
            });
            case "Apply Nickname" -> button.setOnMouseClicked(event -> {
                changeNickName(newNickName.getText());
            });
            case "Apply Password" -> button.setOnMouseClicked(event -> {
                changePassword(currentPassword.getText(), newPassword.getText());
            });
        }
        hBox.getChildren().add(button);
        vBoxInProfileMenu.getChildren().addAll(hBox, hBox1);
    }

    private void loadImageForProfile() {
        ImagePattern backgroundProfile;
        int ID = Player.thePlayer.getProfileID();
        if (Player.thePlayer.getProfileAddress() == null) {
            if (ID <= 50) {
                backgroundProfile = new ImagePattern(new Image(getClass().getResourceAsStream("/PNG/Profile/Profile (" + ID + ").png")));
                profile.setFill(backgroundProfile);
            }
        } else {
            File file = new File(Player.thePlayer.getProfileAddress());
            try {
                backgroundProfile = new ImagePattern(new Image(file.toURI().toURL().toExternalForm()));
                profile.setFill(backgroundProfile);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private void setScene(String input) {
        vBoxInProfileMenu.getChildren().remove(0, 5);
        if (input.equals("nickname")) {
            newNickName = new TextField("Enter New Nickname : ");
            newNickName.setMinSize(300, 40);
            hBox2.getChildren().add(newNickName);
            vBoxInProfileMenu.getChildren().add(hBox2);
            makeButton("Apply Nickname");
        } else if (input.equals("password")) {
            currentPassword = new TextField("Enter Current Password : ");
            currentPassword.setMinSize(300, 40);
            newPassword = new TextField("Enter New Password : ");
            newPassword.setMinSize(300, 40);
            hBox2.getChildren().addAll(currentPassword, newPassword);
            vBoxInProfileMenu.getChildren().add(hBox2);
            makeButton("Apply Password");
        }
    }

    private void changeProfile() {
        Stage stage = ProgramController.getStage();
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg"));
        File selectedFile = fileChooser.showOpenDialog(stage);
        Image image = new Image(selectedFile.toURI().toString());
        ImagePattern imagePattern = new ImagePattern(image);
        profile.setFill(imagePattern);
        Player.thePlayer.setProfileAddress(selectedFile.getAbsolutePath());
    }

    private void changeNickName(String newNickname) {
        showError.setStyle(style);
        Player player = Player.getUserByNickname(newNickname);
        if (player != null) {
            showError.setText("user with nickname " + newNickname + " already exists");
        } else {
            showError.setText("nickname changed successfully!");
            ProgramController.changePlayerNickname(newNickname);
            nickName.setText("Nickname : " + Player.thePlayer.getNickname());
        }
    }

    private void changePassword(String currentPassword, String newPassword) {
        showError.setStyle(style);
        if (ProgramController.isPasswordTrue(currentPassword)) {
            if (ProgramController.isPasswordEqual(newPassword)) {
                showError.setText("please enter a new password");
            } else {
                showError.setText("password changed successfully!");
                ProgramController.changePlayerPassword(newPassword);
            }
        } else {
            showError.setText("current password is invalid");
        }
    }

    public void back(MouseEvent event) throws Exception {
        new MainMenu().start();
    }
}
