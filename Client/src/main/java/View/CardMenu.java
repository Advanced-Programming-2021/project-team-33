package View;

import Controller.CardController;
import Controller.GameController;
import Controller.ProgramController;
import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.regex.Matcher;

public class CardMenu {


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
    public Text attack;
    public Text defence;
    public Text type;
    public Text price;

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("cardMenu.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        var ref = new Object() {
            int i = 0;
        };
        updateShopCards(ref.i);
        description.setText(Card.getCards().get(0).getDescription());
        leftButton.setOnMouseClicked(event -> {
            if (ref.i > 11) {
                ref.i -= 12;
                updateShopCards(ref.i);
            } else updateShopCards(0);

        });
        rightButton.setOnMouseClicked(event -> {
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

        if(Card.getCards().get(i).getCardCategory().equals(CardCategory.MONSTER) ||
                Card.getCards().get(i).getCardCategory().equals(CardCategory.MONSTEREFFECT)){
            attack.setText(Integer.toString(Card.getCards().get(i).getAttack()));
            defence.setText(Integer.toString(Card.getCards().get(i).getAttack()));
        } else{
            attack.setText("---");
            defence.setText("---");
        }
        type.setText(Card.getCards().get(i).getCardTypes().toString().replaceAll("]","").replaceAll("\\[",""));
        price.setText(Integer.toString(Card.getCards().get(i).getPrice()));
        cardShow.setImage(card12.getImage());
        description.setText(Card.getCards().get(i).getDescription());
    }

    private void updateShopCards(int i) {
        card11.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card12.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 1).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card13.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 2).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card14.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 3).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card21.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 4).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card22.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 5).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card23.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 6).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card24.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 7).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card31.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 8).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card32.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 9).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card33.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 10).getCardName().replaceAll("\\s+", "") + ".jpg")));
        card34.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" + Player.thePlayer.getListOfCards().get(i + 11).getCardName().replaceAll("\\s+", "") + ".jpg")));
    }

    public static void showCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String cardName = matcher.group(1);
            if (ProgramController.isCardExist(cardName)) {
                System.out.println(cardName);
                Card card = Card.getCardByName(cardName);
                if (card.getCardCategory().equals(CardCategory.MONSTER) ||
                        card.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                    printMonsterCard(card);
                } else printSpellTrapCard(card);
            } else System.out.println("This card does not exist");
        }
    }

    public static void showSelectedCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (GameController.isOpponentCardSelected && (GameController.selectedCard.getCardStatus().equals(CardStatus.SET) ||
                    GameController.selectedCard.getCardStatus().equals(CardStatus.BACK)))
                System.out.println("card is not visible");
            else if (GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) ||
                    GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                printMonsterCard(GameController.selectedCard);
            } else printSpellTrapCard(GameController.selectedCard);
        }
    }


    private static void printMonsterCard(Card card) {
        System.out.println("Name: " + card.getCardName());
        System.out.println("Level: " + card.getLevel());
        System.out.println("Type: " + card.getCardTypes().toString().replace("[", "")
                .replace("]", "").replace("", ""));
        System.out.println("ATK: " + card.getAttack());
        System.out.println("DEF: " + card.getDefence());
        System.out.println("Description: " + card.getDescription());
        System.out.println(card.getCardStatus().toString());
    }

    private static void printSpellTrapCard(Card card) {
        System.out.println("Name: " + card.getCardName());
        System.out.println(card.getCardCategory());
        System.out.println("Type: " + card.getCardTypes().toString().replace("[", "")
                .replace("]", "").replace("", ""));
        System.out.println("Description: " + card.getDescription());
    }

    public static void printCardMassage(String cardName) {
        if(cardName.equals("Command Knight")) new GameMenu().showError("You can't attack this monster now");
    }

    public void back(MouseEvent event) throws Exception {
        new MainMenu().start();
    }


}
