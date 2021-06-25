import Controller.CardController;
import Controller.GameController;
import Model.Player;
import View.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class mainTest {

    @Test
    @Order(1)
    public void createUser() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user create --username reza --nickname rezax --password 222");
        new LoginMenu().run("user create --username ali --nickname ali --password 222");
        String outputText = outContent.toString();
        Assertions.assertEquals("user created successfully!\nuser created successfully!\n",outputText);
    }

    @Test
    @Order(2)
    public void createUserError1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user create --username reza --nickname rezax --password 222");

        String outputText = outContent.toString();
        Assertions.assertEquals("user with username reza already exists\n",outputText);
    }

    @Test
    @Order(3)
    public void createUserError2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user create --username reza2 --nickname rezax --password 222");
        String outputText = outContent.toString();
        Assertions.assertEquals("user with nickname rezax already exists\n",outputText);
    }

    @Test
    @Order(4)
    public void loginUserError1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user login --username mamad --password 222");
        String outputText = outContent.toString();
        Assertions.assertEquals("Username and password didn't match!\n",outputText);
    }

    @Test
    @Order(5)
    public void loginUserError2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        new LoginMenu().run("user login --username ali --password 2222");
        String outputText = outContent.toString();
        Assertions.assertEquals("Username and password didn't match!\n",outputText);
    }

    @Test
    @Order(6)
    public void loginUser() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        CardController.initialCards();
        new LoginMenu().run("user login --username ali --password 222");
        String outputText = outContent.toString();
        Assertions.assertEquals("user logged in successfully!\n",outputText);
    }

    @Test
    @Order(7)
    public void profileMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        CardController.initialCards();
        new MainMenu().run("menu enter profile");
        new ProfileMenu().run("menu show-current");
        new ProfileMenu().run("profile change --nickname rezax");
        new ProfileMenu().run("profile change --nickname alix");
        new ProfileMenu().run("profile change --password --current 222s --new 222");
        new ProfileMenu().run("profile change --password --current 222 --new 223");
        new ProfileMenu().run("menu exit");
        String outputText = outContent.toString();
        Assertions.assertEquals("""
                Profile Menu
                user with nickname rezax already exists
                nickname changed successfully!
                current password is invalid
                password changed successfully!
                """,outputText);
    }

    @Test
    @Order(8)
    public void shopMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        CardController.initialCards();
        new MainMenu().run("menu enter shop");
        new ShopMenu().run("shop show -all");
        new ShopMenu().run("shop buy Battle Ox");
        new ShopMenu().run("menu exit");
        String outputText = outContent.toString();
        Assertions.assertEquals("""
                Advanced Ritual Art:This card can be used to Ritual Summon any 1 Ritual Monster.
                Alexandrite Dragon:Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures.
                Axe Raider:An axe-wielding monster of tremendous strength and agility.
                Baby Dragon:Much more than just a child, this dragon is gifted with untapped power.
                Battle Ox:A monster with tremendous power, it destroys enemies with a swing of its axe.
                Battle Warrior:A warrior that fights with his bare hands!!!
                Beast King Barbaros:Cannot be destroyed by battle.\s
                Bitron:nothing
                Black Pendant:The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.
                Blue-Eyes White Dragon:This legendary dragon is a powerful engine of destruction.
                Call Of The Haunted:Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position.
                Change of Heart:Target 1 monster your opponent controls; take control of it until the End Phase.
                Closed Forest:All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard.
                Command Knight:Gain 400 ATK to all Warrior-Type monsters, if you control another monster, monsters your opponent controls cannot target this card for an attack
                Crab Turtle:This monster can only be Ritual Summoned with the Ritual Spell Card, "Turtle Oath".
                Crawling dragon:This weakened dragon can no longer fly, but is still a deadly force to be reckoned with.
                Curtain of Dark Ones:A curtain that a spellcaster made, it is said to raise a dark power.
                Dark Blade:They say he is a dragon-manipulating warrior from the dark world.
                Dark Hole:Destroy all monsters on the field.
                Dark Magician:The ultimate wizard in terms of attack and defense.
                Exploder Dragon:If this card is destroyed by battle and sent to the Graveyard
                Feral Imp:A playful little fiend that lurks in the dark, waiting to attack an unwary enemy.
                Fireyarou:A malevolent creature wrapped in flames that attacks enemies with intense fire.
                Flame Manipulator:This Spellcaster attacks enemies with fire-related spells such as "Sea of Flames" and "Wall of Fire".
                Forest:nothing
                Gate Guardian:Cannot be Normal Summoned/Set.\s
                Haniwa:An earthen figure that protects the tomb of an ancient ruler.
                Harpie’s Feather Duster:Destroy all Spells and Traps your opponent controls.
                Herald of Creation:Once per turn: You can discard 1 card, then target 1 Level 7 or higher monster in your Graveyard
                Hero of the East:Feel da strength ah dis sword-swinging samurai from da Far East.
                Horn Imp:A small fiend that dwells in the dark, its single horn makes it a formidable opponent.
                Leotron:A territorial electronic monster that guards its own domain.
                Magic Cylinder:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, and if you do, inflict damage to your opponent equal to its ATK.
                Magic Jammer:When a Spell Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.
                Magnum Shield:Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.
                Man-Eater Bug:FLIP: Target 1 monster on the field; destroy that target.
                Marshmallon:A new species found in electronic space. There's not much information on it.
                Messenger of peace:Monsters with 1500 or more ATK cannot declare an attack. Once per turn, during your Standby Phase, pay 100 LP or destroy this card.
                Mind Crush:Declare 1 card name; if that card is in your opponent's hand, they must discard all copies of it, otherwise you discard 1 random card
                Mirage Dragon:Your opponent cannot activate Trap Cards during the Battle Phase.
                Mirror Force:When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.
                Monster Reborn:Target 1 monster in either GY; Special Summon it.
                Mystical space typhoon:Target 1 Spell/Trap on the field; destroy that target.
                Negate Attack:When an opponent's monster declares an attack: Target the attacking monster; negate the attack, then end the Battle Phase.
                Pot of Greed:Draw 2 cards.
                Raigeki:Destroy all monsters your opponent controls.
                Ring of Defense:nothing
                Scanner:nothing
                Silver Fang:A snow wolf that's beautiful to the eye, but absolutely vicious in battle.
                Skull Guardian:This monster can only be Ritual Summoned with the Ritual Spell Card,
                Slot Machine:The machine's ability is said to vary according to its slot results.
                Solemn Warning:When a monster(s) would be Summoned, OR when a Spell/Trap Card, or monster\s
                Spell Absorption:Each time a Spell Card is activated, gain 500 Life Points immediately after it resolves.
                Spiral Serpent:When huge whirlpools lay cities asunder, it is the hunger of this sea serpent at work.
                Suijin:During damage calculation in your opponent's turn, if this card is being attacked: You can target the attacking monster.
                Supply Squad:Once per turn, if a monster(s) you control is destroyed by battle or card effect: Draw 1 card.
                Sword of Dark Destruction:A DARK monster equipped with this card increases its ATK by 400 points and decreases its DEF by 200 points.
                Swords of Revealing Light:After this card's activation, it remains on the field, but destroy it during the End Phase of your opponent's 3rd turn.
                Terraforming:Add 1 Field Spell from your Deck to your hand.
                Terratiger:When this card is Normal Summoned: You can Special Summon 1 Level 4 or lower Normal Monster from your hand in Defense Position.
                Texchanger:You can Normal Summon/Set this card without Tributing, but its original ATK becomes 1900.
                The Calculator:nothing
                The Tricky:You can Special Summon this card (from your hand) by discarding 1 card.
                Time Seal:Skip the Draw Phase of your opponent's next turn.
                Torrential Tribute:nothing
                Trap Hole:When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK: Target that monster; destroy that target.
                Twin Twisters:Discard 1 card, then target up to 2 Spells/Traps on the field; destroy them.
                UMIIRUKA:Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 points.
                United We Stand:The equipped monster gains 800 ATK/DEF for each face-up monster you control.
                Warrior Dai Grepher:The warrior who can manipulate dragons. Nobody knows his mysterious past.
                Wattaildragon:Capable of indefinite flight. Attacks by wrapping its body with electricity and ramming into opponents.
                Wattkid:A creature that electrocutes opponents with bolts of lightning.
                Yami:nothing
                Yomi Ship:If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.
                You bought Battle Ox  
                """,outputText);
    }

    @Test
    @Order(8)
    public void deckMenu() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        CardController.initialCards();
        new MainMenu().run("menu enter deck");
        new DeckMenu().run("menu show-current");
        new DeckMenu().run("deck create aliDeck");
        new DeckMenu().run("deck delete mamadDeck");
        new DeckMenu().run("deck delete aliDeck");
        new DeckMenu().run("deck create aliDeck");
        new DeckMenu().run("deck create aliDeck1");
        new DeckMenu().run("deck set-activate aliDeck");
        new DeckMenu().run("deck add-card --card Battle Ox --deck aliDeck");
        new DeckMenu().run("deck add-card --card Axe Raider --deck aliDeck");
        new DeckMenu().run("deck rm-card --card Axe Raider --deck aliDeck");
        new DeckMenu().run("deck rm-card --card Magic Cylinder --deck aliDeck");
        String outputText = outContent.toString();
        Assertions.assertEquals("""
                Deck Menu
                deck created successfully!
                deck with name mamadDeck does not exist
                deck deleted successfully!
                deck created successfully!
                deck created successfully!
                deck activated successfully
                card added to deck successfully
                card added to deck successfully
                card removed form deck successfully
                card with name Magic Cylinder does not exist in main deck
                """,outputText);
    }

    @Test
    @Order(9)
    public void deckMenu2() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        CardController.initialCards();
        new DeckMenu().run("deck show --deck-name aliDeck");
        new DeckMenu().run("menu exit");
        String outputText = outContent.toString();
        Assertions.assertEquals("""
                Deck: aliDeck
                Main Deck:
                                
                Monsters:
                Battle Ox: A monster with tremendous power, it destroys enemies with a swing of its axe.
                Axe Raider: An axe-wielding monster of tremendous strength and agility.
                                
                Spell and Traps:
                """,outputText);
    }

    @Test
    @Order(10)
    public void scoreBoard() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        CardController.initialCards();
        new MainMenu().run("menu enter scoreboard");
        new ScoreboardMenu().run("scoreboard show");
        new ScoreboardMenu().run("menu exit");
        String outputText = outContent.toString();
        Assertions.assertEquals("""
                1- alix: 0
                1- rezax: 0
                """,outputText);
    }

    @Test
    @Order(11)
    public void DeckShow() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        outContent.reset();
        CardController.initialCards();
        new MainMenu().run("menu enter deck");
        new DeckMenu().run("deck show --all");
        new DeckMenu().run("menu exit");
        String outputText = outContent.toString();
        Assertions.assertEquals("""
                Decks:
                Active deck:
                aliDeck: main deck 2, side deck 0, valid
                Other decks:
                aliDeck1: main deck 0, side deck 0, valid
                """,outputText);
    }


}
