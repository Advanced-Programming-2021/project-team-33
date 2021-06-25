import Controller.CardController;
import Controller.GameController;
import Controller.ProgramController;
import Model.Player;
import View.GameMenu;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class GameTest2 {

    @BeforeAll
    public static void initialize() {
        CardController.initialCards();
        ProgramController.createUser("ali", "ali", "123");
        ProgramController.createUser("reza", "reza", "123");
        Player.thePlayer = Player.getUserByUsername("ali");
        GameController.createDeck("aliDeck");
        addCards("aliDeck");
        Player.thePlayer = Player.getUserByUsername("reza");
        GameController.createDeck("rezaDeck");
        addCards("rezaDeck");
        GameController.initiateGame("ali", "reza", 3);
    }

    private static void addCards(String deckName) {
        GameController.addCardToDeck(deckName, "Battle Ox", false);
        GameController.addCardToDeck(deckName, "Axe Raider", false);
        GameController.addCardToDeck(deckName, "Magic Cylinder", false);
        GameController.addCardToDeck(deckName, "Horn Imp", false);
        GameController.addCardToDeck(deckName, "Silver Fang", false);
        GameController.addCardToDeck(deckName, "Fireyarou", false);
        GameController.addCardToDeck(deckName, "Bitron", false);
        GameController.addCardToDeck(deckName, "Haniwa", false);
        GameController.addCardToDeck(deckName, "Command Knight", false);
        GameController.addCardToDeck(deckName, "Crawling Dragon", false);
        GameController.addCardToDeck(deckName, "Alexandrite Dragon", false);
    }

    @Test
    @Order(1)
    public void round1Test() {


    }
}
