package View.Chatroom;

import Controller.ProgramController;
import Controller.Util;
import Model.Message;
import Model.Player;
import View.MainMenu;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChatRoom implements Initializable {
    public TextField textField;
    public Button sendBtn;
    @FXML
    private ListView<Message> listView;
    Gson gson = new Gson();
    public static ObservableList<Message> messages;
    public static ArrayList<Message> allMessages = new ArrayList<>();

    public void start() throws Exception {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/chatroom.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public ChatRoom() throws IOException {
        messages = FXCollections.observableArrayList();
        ProgramController.dataOutputStream.writeUTF("getMessages");
        ProgramController.dataOutputStream.flush();
        String result = ProgramController.dataInputStream.readUTF();
        allMessages = gson.fromJson(result.substring(2), new TypeToken<ArrayList<Message>>() {
        }.getType());
        messages.addAll(allMessages);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {
            try {
                if (Util.token==null) return;
                ProgramController.dataOutputStream.writeUTF("getMessages");
                ProgramController.dataOutputStream.flush();
                String result = ProgramController.dataInputStream.readUTF();
                allMessages = gson.fromJson(result.substring(2), new TypeToken<ArrayList<Message>>() {
                }.getType());
                messages.clear();
                messages.addAll(allMessages);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        listView.setItems(messages);
        textField.setOnKeyReleased(event -> {
            if (event.getCode() == KeyCode.ENTER){
                try {
                    sendMessage();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        listView.setCellFactory(new Callback<ListView<Message>, ListCell<Message>>() {
            @Override
            public ListCell<Message> call(ListView<Message> messageListView) {
                return new MessageCell();
            }
        });
    }

    private void sendMessage() throws IOException {
        Gson gson = new Gson();
        String request = gson.toJson(new Message(textField.getText(), Player.thePlayer.getProfileAddress(),
                Util.token, Player.thePlayer.getUsername()));
        ProgramController.dataOutputStream.writeUTF("sendMessage " + request);
        ProgramController.dataOutputStream.flush();
        String result = ProgramController.dataInputStream.readUTF();
        allMessages.clear();
        messages.clear();
        allMessages = gson.fromJson(result.substring(2), new TypeToken<ArrayList<Message>>() {
        }.getType());
        messages.addAll(allMessages);
        listView.setItems(messages);
        textField.clear();
        listView.scrollTo(messages.size()-1);
    }

    public void sendMsg(MouseEvent mouseEvent) throws IOException {
        sendMessage();
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start();
    }
}
