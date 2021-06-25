import Controller.CardController;
import Controller.EffectController;
import Controller.GameController;
import Controller.ProgramController;
import Model.Card;
import Model.Effect;
import Model.Player;
import View.ChangeCardsMenu;
import View.GameMenu;
import View.MainMenu;
import org.junit.jupiter.api.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GameTest {

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
        new MainMenu().run("duel --new --second-player ali --rounds 3");
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
    public void boardTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new GameMenu().run("showBoard");
        String outputText = outContent.toString();
        Assertions.assertEquals(Player.opponent.getNickname() + ":8000\n" +
                "\tc\tc\tc\tc\tc\t\n" +
                Player.opponent.getBoard().getDeck().size() + "\n" +
                "\tE\tE\tE\tE\tE\t\n" +
                "\tE\tE\tE\tE\tE\t\n" +
                "0\t\t\t\t\t\tE\n" +
                "\n" +
                "--------------------------\n" +
                "\n" +
                "E\t\t\t\t\t\t0\n" +
                "\tE\tE\tE\tE\tE\t\n" +
                "\tE\tE\tE\tE\tE\t\n" +
                "\t\t\t\t\t\t" + Player.currentPlayer.getBoard().getDeck().size() + "\n" +
                "c\tc\tc\tc\tc\tc\t\n" +
                Player.currentPlayer.getNickname() + ":8000\n", outputText);
    }

    @Test
    @Order(2)
    public void round1Test() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new GameMenu().run("select --monster 1");
        new GameMenu().run("summon");
        new GameMenu().run("select --hand 1");
        new GameMenu().run("summon");
        new GameMenu().run("next phase");
        new GameMenu().run("select --hand 1");
        new GameMenu().run("summon");
        new GameMenu().run("next phase");
        String outputText = outContent.toString();
        Assertions.assertEquals("""
                no card found in the given position
                no card is selected yet
                card selected
                summoned successfully3
                phase: Battle Phase
                card selected
                you can’t summon this card
                phase: Main Phase 2
                """, outputText);
    }

    @Test
    @Order(3)
    public void changeTurnTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new GameMenu().run("next phase");
        String outputText = outContent.toString();
        Assertions.assertEquals("phase: End Phase\n" +
                "It's " + Player.currentPlayer.getNickname() + "'s turn\n" +
                "phase: Draw Phase\n" +
                "new card added to the hand : Fireyarou\n" +
                "phase: Standby Phase\n" +
                "phase: Main Phase 1\n" +
                Player.opponent.getNickname()  + ":8000\n" +
                "\tc\tc\tc\tc\tc\t\n" +
                Player.opponent.getBoard().getDeck().size() + "\n" +
                "\tE\tE\tE\tE\tE\t\n" +
                "\tE\tE\tOO\tE\tE\t\n" +
                "0\t\t\t\t\t\tE\n" +
                "\n" +
                "--------------------------\n" +
                "\n" +
                "E\t\t\t\t\t\t0\n" +
                "\tE\tE\tE\tE\tE\t\n" +
                "\tE\tE\tE\tE\tE\t\n" +
                "\t\t\t\t\t\t" + Player.currentPlayer.getBoard().getDeck().size() + "\n" +
                "c\tc\tc\tc\tc\tc\t\n" +
                Player.currentPlayer.getNickname() + ":8000\n" +
                "It's " + Player.currentPlayer.getNickname() + "'s turn\n", outputText);
    }

    @Test
    @Order(4)
    public void round1Test2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new GameMenu().run("select --hand 1");
        new GameMenu().run("set");
        new GameMenu().run("select --monster 1");
        new GameMenu().run("flip-summon");
        String outputText = outContent.toString();
        Assertions.assertEquals("card selected\n" +
                "set successfully1\n" +
                "card selected\n" +
                "you can’t flip summon this card\n", outputText);
        new GameMenu().run("next phase");
        new GameMenu().run("next phase");
        new GameMenu().run("next phase");
    }

    @Test
    @Order(5)
    public void attackTest() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new GameMenu().run("select --hand 0");
        new GameMenu().run("summon");
        new GameMenu().run("select --monster 1");
        new GameMenu().run("attack 1");
        new GameMenu().run("next phase");
        new GameMenu().run("attack 1");
        new GameMenu().run("select --monster 2");
        new GameMenu().run("attack direct");
        String outputText = outContent.toString();
        Assertions.assertEquals("card selected\n" +
                "summoned successfully3\n" +
                "card selected\n" +
                "you can’t do this action in this phase\n" +
                "phase: Battle Phase\n" +
                "opponent’s monster card was Axe Raider and the defense position monster is destroyed\n" +
                "card selected\n" +
                "your opponent receives 1700 battle damage\n", outputText);
    }

    @Test
    @Order(6)
    public void main2Test() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new GameMenu().run("next phase");
        new GameMenu().run("select --monster 1");
        new GameMenu().run("attack 1");
        new GameMenu().run("select --hand 0");
        new GameMenu().run("summon");
        String outputText = outContent.toString();
        Assertions.assertEquals("phase: Main Phase 2\n" +
                "card selected\n" +
                "you can’t do this action in this phase\n" +
                "card selected\n" +
                "you can’t summon this card\n", outputText);
        new GameMenu().run("next phase");
    }


    @Test
    @Order(7)
    public void changePositionTest() {
        new GameMenu().run("select --hand 0");
        new GameMenu().run("set");
        new GameMenu().run("next phase");
        new GameMenu().run("next phase");
        new GameMenu().run("next phase");

        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new GameMenu().run("select --monster 1");
        new GameMenu().run("set --position defence");
        String outputText = outContent.toString();
        Assertions.assertEquals("card selected\n" +
                "monster card position changed successfully\n", outputText);
    }

    @Test
    @Order(8)
    public void trivial() {
        GameController.getBackFromMiddleChange();
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new ChangeCardsMenu().help();
        GameController.selectedCard = Card.getCardByName("Battle Ox");
        GameController.setSpell();
        GameController.selectedCard = Card.getCardByName("Battle Ox");
        GameController.summonMonster(1,1);
        GameController.getMonsterFieldSize();
        GameController.isSpellTrap();
        String outputText = outContent.toString();
        Assertions.assertEquals("""
                deck show (-activeDeck)( --side)?
                done
                deck switch --mainCard (.+?) with --sideCard (.+?)
                """, outputText);
    }

    @Test
    @Order(9)
    public void winner() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        GameController.setWinner(2300,Player.getUserByUsername("ali"),Player.getUserByUsername("reza"));

        String outputText = outContent.toString();
        Assertions.assertEquals("""
                ali won the whole match with score: 3000 - 0
                
                
                """, outputText);
    }

    @Test
    @Order(10)
    public void checkEffect() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        GameController.checkEffects(Card.getCardByName("Command Knight"));
        GameController.checkEffects(Card.getCardByName("Exploder Dragon"));
        GameController.activeThreeLight();
        EffectController.spellAbsorption();


        String outputText = outContent.toString();
        Assertions.assertEquals("""
                You can't attack this monster now 
                """, outputText);
    }

}
