package View;

import Controller.ProgramController;
import Controller.Util;
import Main.Main;
import Model.Card;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;

public class ShopMenu {


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
    public Text playerName;
    public Text money;
    public Text massage;
    public Text numberOfCards;

    public ShopMenu() {

    }

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("shopMenu.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));

        showAllOfCardsExistInShop(Util.getCommand(input, "shop show -all"));
        MainMenu.exitMenu(Util.getCommand(input, "menu exit"));
    }

    @FXML
    public void initialize() {
        playerName.setText(Player.thePlayer.getUsername());
        money.setText(Integer.toString(Player.thePlayer.getMoney()));
        var ref = new Object() {
            int i = 0;
        };
        updateShopCards(ref.i);
        description.setText(Card.getCards().get(0).getDescription());
        price.setText(Integer.toString(Card.getCards().get(0).getPrice()));
        leftButton.setOnMouseClicked(event -> {
            MainMenu.playSound("src/main/resources/music/click.mp3");
            if (ref.i > 11) {
                ref.i -= 12;
                updateShopCards(ref.i);
            } else updateShopCards(0);

        });
        rightButton.setOnMouseClicked(event -> {
            MainMenu.playSound("src/main/resources/music/click.mp3");
            if (ref.i < 60) {
                ref.i += 12;
                updateShopCards(ref.i);
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
    }

    private void updateShowCard(ImageView card12, int i) {
        selectedCard = Card.getCards().get(i);
        cardShow.setImage(card12.getImage());
        description.setText(Card.getCards().get(i).getDescription());
        price.setText(Integer.toString(Card.getCards().get(i).getPrice()));
        numberOfCards.setText("You have already" + Integer.toString(countSameCard()) + " card from this kind");
    }

    private void updateShopCards(int i) {
        card11.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card12.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 1).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card13.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 2).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card14.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 3).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card21.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 4).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card22.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 5).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card23.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 6).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card24.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 7).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card31.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 8).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card32.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 9).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card33.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 10).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card34.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Card.getCards().get(i + 11).getCardName().replaceAll("\\s+", "") + ".jpg")));
    }

    public void buy() {

        if (selectedCard.getPrice() > Player.thePlayer.getMoney()) {
            massage.setText("not enough money!!");
        } else {
            Player.thePlayer.decreaseMoney(selectedCard.getPrice());
            Player.thePlayer.addToCardList(selectedCard);
            money.setText(Integer.toString(Player.thePlayer.getMoney()));
            massage.setText("You bought " + selectedCard.getCardName() + "!!");
        }
    }

    public void back(MouseEvent event) throws Exception {
        MainMenu.playSound("src/main/resources/music/click.mp3");
        new MainMenu().start();
    }

    public void showCards(MouseEvent event) throws Exception {
        new CardMenu().start();
    }

    private void showAllOfCardsExistInShop(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            ArrayList<Card> cards = Card.getCards();
            String[] cardName = new String[cards.size()];
            for (int i = 0; i < cards.size(); i++) {
                cardName[i] = cards.get(i).getCardName();
            }
            Arrays.sort(cardName);
            for (int i = 0; i < cards.size(); i++) {
                Card card = Card.getCardByName(cardName[i]);
                System.out.println(card.getCardName() + ":" + card.getDescription());
            }
        }
    }

    private int countSameCard() {
        int count = 0;
        for (int i = 0; i < Player.thePlayer.getListOfCards().size(); i++) {
            if (selectedCard.getCardName().equals(Player.thePlayer.getListOfCards().get(i).getCardName())) count++;
        }
        return count;
    }


}
