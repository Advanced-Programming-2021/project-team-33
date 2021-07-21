package View;

import Controller.ProgramController;
import Controller.Util;
import Model.Card;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class AdminPanel {

    public ImageView exit;
    public ImageView card11;
    public ImageView card12;
    public ImageView card13;
    public ImageView card14;
    public ImageView card21;
    public ImageView card22;
    public ImageView card23;
    public ImageView card24;
    public ImageView card31;
    public ImageView card32;
    public ImageView card33;
    public ImageView card34;
    public ImageView rightButton;
    public ImageView leftButton;
    public Text description;
    public ImageView cardShow;
    public Text price;
    public ImageView Back;
    public Card selectedCard = Card.getCards().get(0);
    public Text numberOfCardsInStock;
    public Text add, remove, forbid;
    public Text message;

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/adminPanel.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        var ref = new Object() {
            int i = 0;
            int page = 1;
        };
        updateShopCards(ref.i);
        exit.setOnMouseClicked(event -> {
            try {
                new ShopMenu().start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        leftButton.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            if (ref.i > 11) {
                ref.page--;
                ref.i -= 12;
                updateShopCards(ref.i);
            } else updateShopCards(0);
        });
        rightButton.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            if (ref.i < 60) {
                ref.i += 12;
                ref.page++;
                updateShopCards(ref.i);
            } else if (ref.page == 6) {
                ref.i += 12;
                ref.page++;
                updateShopCards(-1);
            }
        });
        card11.setOnMouseClicked(event -> updateShowCard(card11, ref.i));
        card12.setOnMouseClicked(event -> updateShowCard(card12, ref.i + 1));
        card13.setOnMouseClicked(event -> updateShowCard(card13, ref.i + 2));
        card14.setOnMouseClicked(event -> updateShowCard(card14, ref.i + 3));
        card21.setOnMouseClicked(event -> updateShowCard(card21, ref.i + 4));
        card22.setOnMouseClicked(event -> updateShowCard(card22, ref.i + 5));
        card23.setOnMouseClicked(event -> updateShowCard(card23, ref.i + 6));
        card24.setOnMouseClicked(event -> updateShowCard(card24, ref.i + 7));
        card31.setOnMouseClicked(event -> updateShowCard(card31, ref.i + 8));
        card32.setOnMouseClicked(event -> updateShowCard(card32, ref.i + 9));
        card33.setOnMouseClicked(event -> updateShowCard(card33, ref.i + 10));
        card34.setOnMouseClicked(event -> updateShowCard(card34, ref.i + 11));
        add.setOnMouseClicked(event -> addCard());
        remove.setOnMouseClicked(event -> removeCard());
        forbid.setOnMouseClicked(event -> forbidCard());
    }

    private void addCard() {
        if (selectedCard == null) message.setText("no card selected yet");
        else {
            try {
                ProgramController.dataOutputStream.writeUTF("addToShop " + selectedCard.getCardName());
                ProgramController.dataOutputStream.flush();
                ProgramController.dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void removeCard() {
        if (selectedCard == null) message.setText("no card selected yet");
        else {
            try {
                ProgramController.dataOutputStream.writeUTF("reduceCard " + selectedCard.getCardName());
                ProgramController.dataOutputStream.flush();
                ProgramController.dataInputStream.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private void forbidCard() {
        if (selectedCard == null) message.setText("no card selected yet");
        else {
            try {
                ProgramController.dataOutputStream.writeUTF("getForbiddenCard " + selectedCard.getCardName());
                ProgramController.dataOutputStream.flush();
                String getForbidden = ProgramController.dataInputStream.readUTF();
                if (getForbidden.equals("1")) {
                    ProgramController.dataOutputStream.writeUTF("permitCard " + selectedCard.getCardName());
                    ProgramController.dataOutputStream.flush();
                    ProgramController.dataInputStream.readUTF();
                    message.setText("card is permitted now");
                } else {
                    ProgramController.dataOutputStream.writeUTF("forbidCard " + selectedCard.getCardName());
                    ProgramController.dataOutputStream.flush();
                    ProgramController.dataInputStream.readUTF();
                    message.setText("card is forbidden now");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateShowCard(ImageView card12, int i) {
        selectedCard = Card.getCards().get(i);
        String count = "";
        try {
            ProgramController.dataOutputStream.writeUTF("cardCount " + selectedCard.getCardName());
            ProgramController.dataOutputStream.flush();
            count = ProgramController.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        numberOfCardsInStock.setText(count);
        cardShow.setImage(card12.getImage());
        description.setText(Card.getCards().get(i).getDescription());
        price.setText(Integer.toString(Card.getCards().get(i).getPrice()));
        //numberOfCards.setText("You have already" + Integer.toString(countSameCard()) + " card from this kind");
    }

    private void updateShopCards(int i) {
        int size = 0;
        if (i == -1) {
            size = Card.getCards().size() - 72;
            i = 72;
        } else size = 12;
        ArrayList<ImageView> cardList = new ArrayList<>();
        cardList.add(card11);
        cardList.add(card12);
        cardList.add(card13);
        cardList.add(card14);
        cardList.add(card21);
        cardList.add(card22);
        cardList.add(card23);
        cardList.add(card24);
        cardList.add(card31);
        cardList.add(card32);
        cardList.add(card33);
        cardList.add(card34);

        for (int j = 0; j < 12; j++) {
            cardList.get(j).setImage(null);
        }
        for (int j = 0; j < size; j++) {
            cardList.get(j).setImage(Util.getImage(Card.getCards().get(i++).getCardName()));
        }

    }

}
