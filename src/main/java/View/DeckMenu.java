package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.Util;
import Model.Card;
import Model.CardCategory;
import Model.Deck;
import Model.Player;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;

public class DeckMenu {

    public static Card selectedCard;
    public GridPane deckGrid;
    public ListView deckListOfCards;
    public ImageView cardShow;
    public ListView deckList;

    private static Deck mainDeck;
    public GridPane sideDeckGrid;

    public DeckMenu() {
    }

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("deckMenu.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }


    @FXML
    public void initialize() throws FileNotFoundException {
        deckListOfCards.getSelectionModel().selectedItemProperty().addListener(event -> {
            ObservableList selectedIndices = deckListOfCards.getSelectionModel().getSelectedIndices();
            selectedCard = Card.getCards().get(Integer.parseInt(selectedIndices.get(0).toString()));
            cardShow.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                    selectedCard.getCardName().replaceAll("\\s+", "") + ".jpg")));
        });
        deckList.getSelectionModel().selectedItemProperty().addListener(event -> {
            ObservableList selectedIndices = deckList.getSelectionModel().getSelectedIndices();
            if (selectedIndices.size() != 0) {
                mainDeck = Player.thePlayer.getListOfDecks().get(Integer.parseInt(selectedIndices.get(0).toString()));
                updateGrid();
            }
        });
        int a = 0;
        for (Card card : Card.getCards()) {
            a++;
            if (a < 70) {
                Image image = new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                        card.getCardName().replaceAll("\\s+", "") + ".jpg"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(124);
                imageView.setFitWidth(75);
                deckListOfCards.getItems().add(imageView);
            }
        }

        for (Deck deck : Player.thePlayer.getListOfDecks()) {
            deckList.getItems().add(deck.getDeckName());
        }
        createGrid();
    }

    private void createGrid() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                Image image = new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg"));
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(124);
                imageView.setFitWidth(75);
                imageView.setId("mainDeckCard" + i + "_" + j);
                deckGrid.add(imageView, j, i);
                int finalJ = j;
                int finalI = i;
                imageView.setOnMouseClicked(event -> {
                    if (mainDeck != null && finalI * 10 + finalJ < mainDeck.getMainDeck().size()) {
                        selectedCard = mainDeck.getMainDeck().get(finalI * 10 + finalJ);
                        cardShow.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                                selectedCard.getCardName().replaceAll("\\s+", "") + ".jpg")));
                    }
                });
            }
        }
        for (int j = 0; j < 10; j++) {
            Image image = new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg"));
            ImageView imageView = new ImageView(image);
            imageView.setFitHeight(124);
            imageView.setFitWidth(75);
            imageView.setId("sideDeckCard" + j);
            sideDeckGrid.add(imageView, j, 0);
            int finalJ = j;
            imageView.setOnMouseClicked(event -> {
                if (mainDeck != null && finalJ < mainDeck.getSideDeck().size()) {
                    selectedCard = mainDeck.getSideDeck().get(finalJ);
                    cardShow.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                            selectedCard.getCardName().replaceAll("\\s+", "") + ".jpg")));
                }
            });
        }
    }


    public void run(String input) throws CloneNotSupportedException {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
//        createDeck(Util.getCommand(input, "deck create (\\S+)"));
//        deleteDeck(Util.getCommand(input, "deck delete (\\S+)"));
//        activateDeck(Util.getCommand(input, "deck set-activate (\\S+)"));
//        addCardToDeck(Util.getCommand(input, "deck add-card --card (.+?) --deck (\\S+)( --side)?"));
//        removeCardFromDeck(Util.getCommand(input, "deck rm-card --card (.+?) --deck (\\S+)( --side)?"));
        showAllDecks(Util.getCommand(input, "deck show --all"));
        showDeck(Util.getCommand(input, "deck show --deck-name (\\S+)( --side)?"));
        showAllCards(Util.getCommand(input, "deck show --cards"));
        CardMenu.showCard(Util.getCommand(input, "card show (.+?)"));
        MainMenu.exitMenu(Util.getCommand(input, "menu exit"));
    }

    private void createDeck() {
        String deckName = "Deck " + (Player.thePlayer.listOfDecks.size() + 1);
        if (ProgramController.isDeckExist(deckName)) {
            System.out.println("deck with name " + deckName + " already exists");
        } else {
            Deck deck = GameController.createDeck(deckName);
            System.out.println("deck created successfully!");
            mainDeck = deck;
        }
    }

    private void updateGrid() {
        int a = 0, b = 0;
        if (mainDeck == null) {
            a = 1000;
            b = 1000;
        }
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 10; j++) {
                Image image = null;
                if (mainDeck == null || a >= mainDeck.getMainDeck().size()) {
                    image = new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg"));
                } else {
                    image = new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                            mainDeck.getMainDeck().get(a).getCardName().replaceAll("\\s+", "") + ".jpg"));
                }
                ImageView imageView = (ImageView) deckGrid.lookup("#mainDeckCard" + i + "_" + j);
                imageView.setImage(image);
                a++;
            }
        }
        for (int j = 0; j < 10; j++) {
            Image image = null;
            if (mainDeck == null || b >= mainDeck.getSideDeck().size()) {
                image = new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg"));
            } else {
                image = new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                        mainDeck.getSideDeck().get(b).getCardName().replaceAll("\\s+", "") + ".jpg"));
            }
            ImageView imageView = (ImageView) sideDeckGrid.lookup("#sideDeckCard" + j);
            imageView.setImage(image);
            b++;
        }
    }

    private void updateDeckList() {
        deckList.getItems().clear();
        for (Deck deck : Player.thePlayer.getListOfDecks()) {
            deckList.getItems().add(deck.getDeckName());
        }
        if (deckList.getItems().size() != 0) {
            deckList.getSelectionModel().select(deckList.getItems().size() - 1);
        } else mainDeck = null;
    }

    private void deleteDeck() {
        String deckName = mainDeck.getDeckName();
        if (!ProgramController.isDeckExist(deckName))
            System.out.println("deck with name " + deckName + " does not exist");
        else {
            GameController.deleteDeck(deckName);
            System.out.println("deck deleted successfully!");
        }
    }

    private void activateDeck() {
        String deckName = mainDeck.getDeckName();
        if (!ProgramController.isDeckExist(deckName))
            System.out.println("deck with name " + deckName + " does not exist");
        else {
            GameController.activateDeck(deckName);
            System.out.println("deck activated successfully");
        }
    }

    private void addCardToDeck(String cardName, boolean isSide) {
        String deckName = mainDeck.getDeckName();
        if (!ProgramController.isDeckExist(deckName))
            System.out.println("deck with name " + deckName + " does not exist");
        else if (!ProgramController.isCardExist(cardName) ||
                !Player.thePlayer.listOfCards.contains(Card.getCardByName(cardName)))
            System.out.println("card with name " + cardName + " does not exist");
        else if (Player.getDeckByName(deckName).isMainDeckFull())
            System.out.println("main deck is full");
        else if (Player.getDeckByName(deckName).isSideDeckFull())
            System.out.println("side deck is full");
        else if (Player.getDeckByName(deckName).getInvalidCard(deckName, cardName)) {
            System.out.println("there are already three cards with name " + cardName +
                    " in deck " + deckName + "name");
        } else {
            try {
                GameController.addCardToDeck(mainDeck.getDeckName(), cardName, isSide);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.println("card added to deck successfully");
        }
    }

    private void removeCardFromDeck(String cardName, boolean isSide) {
        String deckName = mainDeck.getDeckName();
        if (!ProgramController.isDeckExist(deckName))
            System.out.println("deck with name " + deckName + " does not exist");
        else if (!ProgramController.isCardExistInMainDeck(cardName, deckName) && !isSide)
            System.out.println("card with name " + cardName + " does not exist in main deck");
        else if (!ProgramController.isCardExistInSideDeck(cardName, deckName) && isSide)
            System.out.println("card with name " + cardName + " does not exist in side deck");
        else {
            GameController.removeCardFromDeck(deckName, cardName, isSide);
            System.out.println("card removed form deck successfully");
            selectedCard = null;
        }
    }

    public static void showDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String deckName = matcher.group(1);
            if (deckName.equals("-activeDeck")) deckName = Player.currentPlayer.getActiveDeck().getDeckName();
            boolean isSide = false;
            if (matcher.group(2) != null) isSide = true;
            if (!ProgramController.isDeckExist(deckName))
                System.out.println("deck with name " + deckName + " does not exist");
            else if (!isSide) {
                System.out.println("Deck: " + deckName + "\nMain Deck:\n\nMonsters:");
                for (int i = 0; i < Player.getDeckByName(deckName).getMainDeck().size(); i++) {
                    if (Player.getDeckByName(deckName).getMainDeck().get(i).getCardCategory().equals(CardCategory.MONSTER) ||
                            Player.getDeckByName(deckName).getMainDeck().get(i).getCardCategory().equals(CardCategory.MONSTEREFFECT))
                        System.out.println(Player.getDeckByName(deckName).getMainDeck().get(i).getCardName() + ": " +
                                Player.getDeckByName(deckName).getMainDeck().get(i).getDescription());
                }
                System.out.println("\nSpell and Traps:");
                for (int i = 0; i < Player.getDeckByName(deckName).getMainDeck().size(); i++) {
                    if (Player.getDeckByName(deckName).getMainDeck().get(i).getCardCategory().equals(CardCategory.SPELL) ||
                            Player.getDeckByName(deckName).getMainDeck().get(i).getCardCategory().equals(CardCategory.TRAP))
                        System.out.println(Player.getDeckByName(deckName).getMainDeck().get(i).getCardName() + ": " +
                                Player.getDeckByName(deckName).getMainDeck().get(i).getDescription());
                }
            } else {
                System.out.println("Deck: " + deckName + "\nSide Deck:\n\nMonsters:");
                for (int i = 0; i < Player.getDeckByName(deckName).getSideDeck().size(); i++) {
                    if (Player.getDeckByName(deckName).getSideDeck().get(i).getCardCategory().equals(CardCategory.MONSTER) ||
                            Player.getDeckByName(deckName).getSideDeck().get(i).getCardCategory().equals(CardCategory.MONSTEREFFECT))
                        System.out.println(Player.getDeckByName(deckName).getSideDeck().get(i).getCardName() + ": " +
                                Player.getDeckByName(deckName).getSideDeck().get(i).getDescription());
                }
                System.out.println("\nSpell and Traps:");
                for (int i = 0; i < Player.getDeckByName(deckName).getSideDeck().size(); i++) {
                    if (Player.getDeckByName(deckName).getSideDeck().get(i).getCardCategory().equals(CardCategory.SPELL) ||
                            Player.getDeckByName(deckName).getSideDeck().get(i).getCardCategory().equals(CardCategory.TRAP))
                        System.out.println(Player.getDeckByName(deckName).getSideDeck().get(i).getCardName() + ": " +
                                Player.getDeckByName(deckName).getSideDeck().get(i).getDescription());
                }
            }

        }
    }

    private void showAllDecks(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String valid = "invalid";
            System.out.println("Decks:\nActive deck:");
            if (Player.thePlayer.getActiveDeck() != null) {
                Deck activeDeck = Player.thePlayer.getActiveDeck();
                if (activeDeck.isDeckValid(activeDeck)) valid = "valid";
                System.out.println(activeDeck.getDeckName() + ": main deck " +
                        activeDeck.getMainDeck().size() + ", side deck " +
                        activeDeck.getSideDeck().size() + ", " + valid);
            }
            System.out.println("Other decks:");
            for (int i = 0; i < Player.thePlayer.listOfDecks.size(); i++) {
                if (!Player.thePlayer.listOfDecks.get(i).isDeckActive()) {
                    Deck otherDeck = Player.thePlayer.listOfDecks.get(i);
                    if (otherDeck.isDeckValid(otherDeck)) valid = "valid";
                    System.out.println(otherDeck.getDeckName() + ": main deck " +
                            otherDeck.getMainDeck().size() + ", side deck " +
                            otherDeck.getSideDeck().size() + ", " + valid);
                }
            }
        }
    }

    private void showAllCards(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            ArrayList<String> cardNameList = new ArrayList<>();
            for (int i = 0; i < Player.thePlayer.listOfCards.size(); i++) {
                cardNameList.add(Player.thePlayer.listOfCards.get(i).getCardName()
                        + ": " + Player.thePlayer.listOfCards.get(i).getDescription());
            }
            cardNameList.sort(Comparator.naturalOrder());
            for (int i = 0; i < cardNameList.size(); i++) {
                System.out.println(cardNameList.get(i));
            }
        }

    }


    public void addToMainDeck(MouseEvent mouseEvent) {
        if (mainDeck == null || selectedCard == null) return;
        addCardToDeck(selectedCard.getCardName(), false);
        updateGrid();
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        new MainMenu().start();
    }

    public void createDeck(MouseEvent mouseEvent) {
        createDeck();
        updateGrid();
        updateDeckList();
    }

    public void removeCardFromMainDeck(MouseEvent mouseEvent) {
        if (mainDeck == null || selectedCard == null) return;
        removeCardFromDeck(selectedCard.getCardName(), false);
        updateGrid();
    }

    public void removeCardFromSideDeck(MouseEvent mouseEvent) {
        if (mainDeck == null || selectedCard == null) return;
        removeCardFromDeck(selectedCard.getCardName(), true);
        updateGrid();
    }

    public void addToSideDeck(MouseEvent mouseEvent) {
        if (mainDeck == null || selectedCard == null) return;
        addCardToDeck(selectedCard.getCardName(), true);
        updateGrid();
    }

    public void activateDeck(MouseEvent mouseEvent) {
        if (mainDeck == null) return;
        activateDeck();
        updateGrid();
    }

    public void deleteDeck(MouseEvent mouseEvent) {
        if (mainDeck == null) return;
        deleteDeck();
        updateDeckList();
        updateGrid();
    }

    public void exportCard(MouseEvent mouseEvent) {
        ImportExportMenu.exportCard(selectedCard.getCardName());
    }

    public void importCard(MouseEvent mouseEvent) throws IOException {
        new ImportExportMenu().start();
    }
}
