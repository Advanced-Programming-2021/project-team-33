package View;

import Controller.GameController;
import Controller.ProgramController;
import Model.Card;
import Model.Deck;
import Model.Player;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

public class SetGame {

    public ImageView one;
    public ImageView three;
    public Text opponent;
    public ImageView rightButton;
    public ImageView leftButton;
    public ImageView start;

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("setRound.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        AtomicInteger round = new AtomicInteger(1);
        AtomicInteger opponentNumber = new AtomicInteger();
        opponent.setText(Player.getPlayers().get(opponentNumber.get()).getUsername());
        one.setOnMouseClicked(event -> round.set(1));
        three.setOnMouseClicked(event -> round.set(3));
        leftButton.setOnMouseClicked(event -> {
            if (opponentNumber.get() != 0)
                opponent.setText(Player.getPlayers().get(opponentNumber.decrementAndGet()).getUsername());
        });
        rightButton.setOnMouseClicked(event -> {
            if (opponentNumber.get() != Player.getPlayers().size() - 1)
                opponent.setText(Player.getPlayers().get(opponentNumber.incrementAndGet()).getUsername());

        });
        start.setOnMouseClicked(event -> startGame(round.intValue(), Player.getPlayers().get(opponentNumber.get()).getUsername()));
    }

    private void startGame(int round, String secondPlayerName) {
        if (!secondPlayerName.equals("ai")) {
            Player secondPlayer = Player.getUserByUsername(secondPlayerName);
            Deck deck = new Deck("deck1");
            Player.thePlayer.addToDeckList(deck);
            deck.setDeckActive(true);
            Deck deck1 = new Deck("deck2");
            secondPlayer.addToDeckList(deck1);
            deck1.setDeckActive(true);
            secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Axe Raider"));
            secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Battle Ox"));
            secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Magic Cylinder"));
            secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Horn Imp"));
            secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Silver Fang"));
            secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Fireyarou"));
            secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Crawling Dragon"));
            Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Alexandrite Dragon"));
            Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Swords of Revealing Light"));
            Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Beast King Barbaros"));
            Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Bitron"));
            Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Haniwa"));
            Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Wattkid"));
            Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Baby Dragon"));

            if (!GameController.isDeckActive(Player.thePlayer.getUsername()))
                System.out.println(Player.thePlayer.getUsername() + " has no active deck");
            else if (!GameController.isDeckActive(secondPlayerName))
                System.out.println(secondPlayerName + " has no active deck");
            else if (!GameController.isDeckValid(Player.thePlayer.getUsername()))
                System.out.println(Player.thePlayer.getUsername() + "'s deck is invalid");
            else if (!GameController.isDeckValid(secondPlayerName))
                System.out.println(secondPlayerName + "'s deck is invalid");
            else {
                Player.currentPlayer = Player.thePlayer;
                Player.opponent = secondPlayer;
                GameController.initiateGame(Player.thePlayer.getUsername(), secondPlayerName, round);
                try {
                    new FlipCoin().start();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } //else duelWithAi();
    }

}
