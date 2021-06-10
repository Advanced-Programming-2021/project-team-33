package View;

import Controller.ProgramController;
import Controller.Util;
import Model.Card;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        buy(Util.getCommand(input, "shop buy (.+)"));
        showAllOfCardsExistInShop(Util.getCommand(input, "shop show -all"));
        MainMenu.exitMenu(Util.getCommand(input, "menu exit"));
    }

    @FXML
    public void initialize() {
        var ref = new Object() {
            int i = 0;
        };
        updateShopCards(ref.i);
        description.setText(Card.getCards().get(0).getDescription());
        price.setText(Integer.toString(Card.getCards().get(0).getPrice()));
        leftButton.setOnMouseClicked(event -> {
            if(ref.i > 11) {
                ref.i -= 12;
                updateShopCards(ref.i);
            }else updateShopCards(0);

        });
        rightButton.setOnMouseClicked(event -> {
            if(ref.i < 60){
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
        cardShow.setImage(card12.getImage());
        description.setText(Card.getCards().get(i).getDescription());
        price.setText(Integer.toString(Card.getCards().get(i).getPrice()));
    }

    private void updateShopCards(int i) {
        card11.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i).getCardName().replaceAll("\\s+","")+".jpg")));
        card12.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 1).getCardName().replaceAll("\\s+","")+".jpg")));
        card13.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 2).getCardName().replaceAll("\\s+","")+".jpg")));
        card14.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 3).getCardName().replaceAll("\\s+","")+".jpg")));
        card21.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 4).getCardName().replaceAll("\\s+","")+".jpg")));
        card22.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 5).getCardName().replaceAll("\\s+","")+".jpg")));
        card23.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 6).getCardName().replaceAll("\\s+","")+".jpg")));
        card24.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 7).getCardName().replaceAll("\\s+","")+".jpg")));
        card31.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 8).getCardName().replaceAll("\\s+","")+".jpg")));
        card32.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 9).getCardName().replaceAll("\\s+","")+".jpg")));
        card33.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 10).getCardName().replaceAll("\\s+","")+".jpg")));
        card34.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/"+ Card.getCards().get(i + 11).getCardName().replaceAll("\\s+","")+".jpg")));
    }

    private void buy(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (!ProgramController.isCardExist(matcher.group(1))) System.out.println("there is no card with this name");
            else {
                Card card = Card.getCardByName(matcher.group(1));
                if (card.getPrice() > Player.thePlayer.getMoney()) {
                    System.out.println("not enough money");
                } else {
                    Player.thePlayer.decreaseMoney(card.getPrice());
                    Player.thePlayer.addToCardList(card);
                    System.out.println("You bought " + card.getCardName());
                }


            }
        }
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


}
