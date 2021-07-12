package View;

import Controller.Ai;
import Controller.GameController;
import Controller.ProgramController;
import Controller.Util;
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
    public ImageView multiPlayer;

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/setRound.fxml"));
        MainMenu.menu = "game";
        PlayMusic.stop();
        new PlayMusic().start();
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {

        AtomicInteger round = new AtomicInteger(1);
        AtomicInteger opponentNumber = new AtomicInteger();
        if (Player.getPlayers().get(opponentNumber.get()).getUsername().equals(Player.thePlayer.getUsername()))
            opponent.setText("Ai");
        else
            opponent.setText(Player.getPlayers().get(opponentNumber.get()).getUsername());
        one.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            round.set(1);
        });
        three.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            round.set(3);
        });

        leftButton.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            if (opponentNumber.get() != 0 &&
                    Player.getPlayers().get(opponentNumber.get() - 1).getUsername().equals(Player.thePlayer.getUsername())) {
                opponentNumber.decrementAndGet();
                opponent.setText("Ai");
            } else if (opponentNumber.get() != 0)
                opponent.setText(Player.getPlayers().get(opponentNumber.decrementAndGet()).getUsername());

        });
        rightButton.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            if (opponentNumber.get() != Player.getPlayers().size() - 1 &&
                    Player.getPlayers().get(opponentNumber.get() + 1).getUsername().equals(Player.thePlayer.getUsername())) {
                opponent.setText("Ai");
                opponentNumber.incrementAndGet();
            } else if (opponentNumber.get() != Player.getPlayers().size() - 1)
                opponent.setText(Player.getPlayers().get(opponentNumber.incrementAndGet()).getUsername());

        });
        start.setOnMouseClicked(event -> {
            MainMenu.playSound(Util.CLICK_MUSIC);
            if (opponent.getText().equals("Ai"))
                duelWithAi(round.intValue());
            else
                startGame(round.intValue(), Player.getPlayers().get(opponentNumber.get()).getUsername());
        });
        multiPlayer.setOnMouseClicked(event -> {
            try {
                setMultiPlayer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void setMultiPlayer() throws IOException {

        String result = "0";
        while(true){
            ProgramController.dataOutputStream.writeUTF("multiplayer " + Player.thePlayer.getUsername());
            ProgramController.dataOutputStream.flush();
            result = ProgramController.dataInputStream.readUTF();
            System.out.println(result);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           if(!result.equals("0")){
               Lobby.player1 = Player.thePlayer.getUsername();
               Lobby.player2 = result;
               try {
                   new Lobby().start();
               } catch (Exception e) {
                   e.printStackTrace();
               }
               break;
           }
        }
    }

    private void duelWithAi(int round) {
        Deck deck = new Deck("deck1");
        Player.thePlayer.addToDeckList(deck);
        deck.setDeckActive(true);
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Alexandrite Dragon"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Monster Reborn"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Beast King Barbaros"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Bitron"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Haniwa"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Wattkid"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Baby Dragon"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Fireyarou"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Silver Fang"));


        Ai.initiateGameWithAi(Player.thePlayer.getUsername(), round);
        try {
            new GameMenu().start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startGame(int round, String secondPlayerName) {

        Player secondPlayer = Player.getUserByUsername(secondPlayerName);
        Deck deck = new Deck("deck1");
        Player.thePlayer.addToDeckList(deck);
        deck.setDeckActive(true);
        Deck deck1 = new Deck("deck2");
        secondPlayer.addToDeckList(deck1);
        deck1.setDeckActive(true);
        secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Axe Raider"));
        secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Battle Ox"));
        secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Terraforming"));
        secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Monster Reborn"));
        secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Horn Imp"));
        secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Silver Fang"));
        secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Magic Cylinder"));
        secondPlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Fireyarou"));


        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Alexandrite Dragon"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Beast King Barbaros"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Bitron"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Haniwa"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Wattkid"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Baby Dragon"));
        Player.thePlayer.getActiveDeck().addToMainDeck(Card.getCardByName("Crawling Dragon"));


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
    }


}
