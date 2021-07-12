package View.Chatroom;

import Model.Message;
import Model.Player;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class MessageCell extends ListCell<Message> {
    @Override
    protected void updateItem(Message message, boolean empty) {
        super.updateItem(message, empty);

        if (empty || message == null) {
            setText(null);
            setGraphic(null);
        } else {
            HBox hBox = new HBox();
            hBox.setPadding(new Insets(5, 5, 5, 5));
            hBox.setStyle("-fx-background-color: white");
            ImageView avatar = new ImageView();
            avatar.setFitHeight(50);
            avatar.setFitWidth(50);
            Label messageText = new Label();
            messageText.setWrapText(true);
            hBox.setMaxWidth(440);
            HBox.setMargin(hBox, new Insets(5, 5, 5, 5));
            HBox.setMargin(avatar, new Insets(5, 5, 5, 5));
            if (message.getSenderUsername().equals(Player.thePlayer.getUsername())) {
                hBox.getChildren().addAll(messageText, avatar);
                hBox.setAlignment(Pos.CENTER_RIGHT);
            } else {
                hBox.getChildren().addAll(avatar, messageText);
                hBox.setAlignment(Pos.CENTER_LEFT);
            }

            messageText.setText(message.getText());
            if (message.getProfileAddress() == null || message.getProfileAddress().isEmpty()
                    || getClass().getResourceAsStream(message.getProfileAddress()) == null)
                avatar.setImage(new Image(getClass().getResourceAsStream("/PNG/profile.png")));
            else
                avatar.setImage(new Image(getClass().getResourceAsStream(message.getProfileAddress())));
            avatar.setStyle("-fx-border-radius: 20");
            setText(null);
            setGraphic(hBox);
        }

    }
}
