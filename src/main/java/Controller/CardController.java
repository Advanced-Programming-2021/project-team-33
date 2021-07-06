package Controller;

import Model.*;
import Model.Effects.*;


import java.util.ArrayList;
import java.util.List;

public class CardController {


    public static void initialCards() {
        initialMonsters();
        initialSpells();
        initialTraps();
    }

    private static void initialTraps() {
        Card magicCylinder = new Card("Magic Cylinder",
                "When an opponent's monster declares an attack: Target the attacking monster; negate the attack," +
                        " and if you do, inflict damage to your opponent equal to its ATK.", 2000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new AttackDirectToOpponent())), 0, 0,Attribute.FIRE);

        Card mirrorForce = new Card("Mirror Force",
                "When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.", 2000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroyAllOpponentMonsters())), 0, 0,Attribute.FIRE);

        Card mindCrush = new Card("Mind Crush",
                "Declare 1 card name; if that card is in your opponent's hand," +
                        " they must discard all copies of it, otherwise you discard 1 random card", 2000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new GuessOpponentCard())), 0, 0,Attribute.FIRE);

        Card trapHole = new Card("Trap Hole",
                "When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK:" +
                        " Target that monster; destroy that target.", 2000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroySelectedMonster())), 0, 0,Attribute.FIRE);

        Card torrentialTribute = new Card("Torrential Tribute", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroyAllMonsters())), 0, 0,Attribute.FIRE);

        Card timeSeal = new Card("Time Seal", "Skip the Draw Phase of your opponent's next turn.",
                2000, new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 1, 0,
                new ArrayList<>(List.of(new TakeNextRoundFromOpponent())), 0, 0,Attribute.FIRE);

        Card negateAttack = new Card("Negate Attack", "When an opponent's monster declares an attack: Target the attacking monster; negate the attack, then end the Battle Phase.", 3000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new EndBattlePhaseForOpponent())), 0, 0,Attribute.FIRE);

        Card solemnWarning = new Card("Solemn Warning", "When a monster(s) would be Summoned, OR when a Spell/Trap Card, or monster ", 3000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new GiveYourLife())), 0, 0,Attribute.FIRE);

        Card magicJammer = new Card("Magic Jammer", "When a Spell Card is activated: Discard 1 card; negate the activation, and if you do, destroy it.", 3000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroySpecificSpellCard())), 0, 0,Attribute.FIRE);

        Card callOfTheHaunted = new Card("Call Of The Haunted", "Activate this card by targeting 1 monster in your GY; Special Summon that target in Attack Position.", 3500,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new SummonFromGraveYard())), 0, 0,Attribute.FIRE);
    }

    private static void initialSpells() {

        Card monsterReborn = new Card("Monster Reborn", "Target 1 monster in either GY; Special Summon it.", 2500,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new ChooseFromGraveyardAndSpecialSummon())), 0, 0,Attribute.FIRE);

        Card terraforming = new Card("Terraforming", "Add 1 Field Spell from your Deck to your hand.", 2000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new AddFieldSpellToMyHand())), 0, 0,Attribute.FIRE);

        Card potOfGreed = new Card("Pot of Greed", "Draw 2 cards.", 2500,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new PickTwoCardsFromTopOfDeck())), 0, 0,Attribute.FIRE);

        Card raigeki = new Card("Raigeki", "Destroy all monsters your opponent controls.", 2500,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new DestroyAllOpponentMonsters())), 0, 0,Attribute.FIRE);


        Card changeOfHeart = new Card("Change of Heart", "Target 1 monster your opponent controls; take control of it until the End Phase.", 2500,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                null, 0, 0,Attribute.FIRE);

        Card harpieFeatherDuster = new Card("Harpies Feather Duster", "Destroy all Spells and Traps your opponent controls.", 2500,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new DestroyAllOpponentSpellTrap())), 0, 0,Attribute.FIRE);

        // what the fuck
        Card swordsOfRevealingLight = new Card("Swords of Revealing Light", "After this card's activation, it remains on the field, but destroy it during the End Phase of your opponent's 3rd turn.", 2500,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new DestroyAllOpponentSpellTrap())), 0, 0,Attribute.FIRE);

        Card darkHole = new Card("Dark Hole", "Destroy all monsters on the field.", 2500,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new DestroyAllMonsters())), 0, 0,Attribute.FIRE);
        // what
        Card supplySquad = new Card("Supply Squad", "Once per turn, if a monster(s) you control is destroyed by battle or card effect: Draw 1 card.", 4000,
                new ArrayList<>(List.of(CardType.CONTINUES)), CardCategory.SPELL, 3, 0,
                null, 0, 0,Attribute.FIRE);

        Card spellAbsorption = new Card("Spell Absorption", "Each time a Spell Card is activated, gain 500 Life Points immediately after it resolves.", 4000,
                new ArrayList<>(List.of(CardType.CONTINUES)), CardCategory.SPELL, 3, 0,
                null, 0, 0,Attribute.FIRE);

        // not complete
        Card messengerOfPeace = new Card("Messenger of peace", "Monsters with 1500 or more ATK cannot declare an attack. Once per turn, during your Standby Phase, pay 100 LP or destroy this card.", 4000,
                new ArrayList<>(List.of(CardType.CONTINUES)), CardCategory.SPELL, 3, 0,
                null, 0, 0,Attribute.FIRE);

        // not complete
        Card twinTwisters = new Card("Twin Twisters", "Discard 1 card, then target up to 2 Spells/Traps on the field; destroy them.", 3500,
                new ArrayList<>(List.of(CardType.QUICKPLAY)), CardCategory.SPELL, 3, 0,
                null, 0, 0,Attribute.FIRE);
        // same problem not solved
        Card mysticalSpaceTyphoon = new Card("Mystical space typhoon", "Target 1 Spell/Trap on the field; destroy that target.", 3500,
                new ArrayList<>(List.of(CardType.QUICKPLAY)), CardCategory.SPELL, 3, 0,
                null, 0, 0,Attribute.FIRE);
        // what
        Card ringOfDefense = new Card("Ring of Defense", "nothing", 4300,
                new ArrayList<>(List.of(CardType.QUICKPLAY)), CardCategory.SPELL, 3, 0,
                null, 0, 0,Attribute.FIRE);
        // not sure
        Card yami = new Card("Yami", "nothing", 4300,
                new ArrayList<>(List.of(CardType.FIELD)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAttackDeffenceForMonstersFieldSpellCaster())), 0, 0,Attribute.FIRE);

        // not sure
        Card forest = new Card("Forest", "nothing", 4300,
                new ArrayList<>(List.of(CardType.FIELD)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAttackDeffenceForMonstersInsectBeast())), 0, 0,Attribute.FIRE);

        Card closedForest = new Card("Closed Forest", "All Beast-Type monsters you control gain 100 ATK for each monster in your Graveyard.", 4300,
                new ArrayList<>(List.of(CardType.FIELD)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAttackOfMonsterBeastForEachGraveyard())), 0, 0,Attribute.FIRE);

        Card umiiruka = new Card("UMIIRUKA", "Increase the ATK of all WATER monsters by 500 points and decrease their DEF by 400 points.", 4300,
                new ArrayList<>(List.of(CardType.FIELD)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAttackDeffenceOfAqua())), 0, 0,Attribute.FIRE);

        Card swordOfDarkDestruction = new Card("Sword of Dark Destruction", "A DARK monster equipped with this card increases its ATK by 400 points and decreases its DEF by 200 points.", 4300,
                new ArrayList<>(List.of(CardType.EQUIP)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeEquipedMonstersAttackDeffence())), 0, 0,Attribute.FIRE);

        Card blackPendant = new Card("Black Pendant", "The equipped monster gains 500 ATK. When this card is sent from the field to the Graveyard: Inflict 500 damage to your opponent.", 4300,
                new ArrayList<>(List.of(CardType.EQUIP)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAllEquipAttack())), 0, 0,Attribute.FIRE);

        Card unitedWeStand = new Card("United We Stand", "The equipped monster gains 800 ATK/DEF for each face-up monster you control.", 4300,
                new ArrayList<>(List.of(CardType.EQUIP)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeMyEquipAttackDeffence())), 0, 0,Attribute.FIRE);

        Card magnumShield = new Card("Magnum Shield", "Equip only to a Warrior-Type monster. Apply this effect, depending on its battle position.", 4300,
                new ArrayList<>(List.of(CardType.EQUIP)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeWarriorAttackDeffenceReplace())), 0, 0,Attribute.FIRE);

        Card advancedRitualArt = new Card("Advanced Ritual Art", "This card can be used to Ritual Summon any 1 Ritual Monster.", 3000,
                new ArrayList<>(List.of(CardType.RITUAL)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new RitualSummon())), 0, 0,Attribute.FIRE);
    }



    private static void initialMonsters() {
        Card commandKnight = new Card("Command Knight", "Gain 400 ATK to all Warrior-Type monsters," +
                " if you control another monster, monsters your opponent " +
                "controls cannot target this card for an attack", 2100,
                new ArrayList<>(List.of(CardType.WARRIOR, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 4, new ArrayList<>(List.of(new IncreaseAttack())), 1000, 1000, Attribute.EARTH);

        Card battleOx = new Card("Battle Ox",
                "A monster with tremendous power, it destroys enemies with a swing of its axe.", 2900,
                new ArrayList<>(List.of(CardType.BEASTWARRIOR)), CardCategory.MONSTER,
                3, 4, null, 1700, 1000, Attribute.EARTH);

        Card axeRaider = new Card("Axe Raider",
                "An axe-wielding monster of tremendous strength and agility.", 3100,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 4, null, 1700, 1150, Attribute.WATER);

        Card hornImp = new Card("Horn Imp",
                "A small fiend that dwells in the dark, its single horn makes it a formidable opponent.", 2500,
                new ArrayList<>(List.of(CardType.FIEND)), CardCategory.MONSTER,
                3, 4, null, 1300, 1000,Attribute.DARK);

        Card yomiShip = new Card("Yomi Ship",
                "If this card is destroyed by battle and sent to the GY: Destroy the monster that destroyed this card.", 1700,
                new ArrayList<>(List.of(CardType.AQUA, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 3, null, 800, 1400,Attribute.WATER);

        Card silverFang = new Card("Silver Fang",
                "A snow wolf that's beautiful to the eye, but absolutely vicious in battle.", 1700,
                new ArrayList<>(List.of(CardType.BEAST)), CardCategory.MONSTER,
                3, 3, null, 1200, 800,Attribute.EARTH);

        Card suijin = new Card("Suijin", "During damage calculation in your opponent's turn, if this card" +
                " is being attacked: You can target the attacking monster.", 8700,
                new ArrayList<>(List.of(CardType.AQUA, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 7, null, 2500, 2400,Attribute.WATER);

        Card fireyarou = new Card("Fireyarou", "A malevolent creature wrapped in flames that attacks enemies with intense fire.", 2500,
                new ArrayList<>(List.of(CardType.PYRO)), CardCategory.MONSTER,
                3, 4, null, 1300, 1000,Attribute.FIRE);

        Card curtainOfDarkOnes = new Card("Curtain of Dark Ones", "A curtain that a spellcaster made, it is said to raise a dark power.", 700,
                new ArrayList<>(List.of(CardType.SPELLCASTER)), CardCategory.MONSTER,
                3, 2, null, 600, 500,Attribute.DARK);

        Card feralImp = new Card("Feral Imp", "A playful little fiend that lurks in the dark, waiting to attack an unwary enemy.", 2800,
                new ArrayList<>(List.of(CardType.FIEND)), CardCategory.MONSTER,
                3, 4, null, 1300, 1400,Attribute.DARK);

        Card darkMagician = new Card("Dark Magician",
                "The ultimate wizard in terms of attack and defense.", 8300,
                new ArrayList<>(List.of(CardType.SPELLCASTER)), CardCategory.MONSTER,
                3, 7, null, 2500, 2100,Attribute.DARK);

        Card wattkid = new Card("Wattkid", "A creature that electrocutes opponents with bolts of lightning.", 1300,
                new ArrayList<>(List.of(CardType.THUNDER)), CardCategory.MONSTER,
                3, 3, null, 1000, 500,Attribute.LIGHT);

        Card babyDragon = new Card("Baby Dragon", "Much more than just a child, this dragon is gifted with untapped power.", 1600,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 3, null, 1200, 700,Attribute.WIND);

        Card heroOfTheEast = new Card("Hero of the East", "Feel da strength ah dis sword-swinging samurai from da Far East.", 1700,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 3, null, 1100, 1000,Attribute.EARTH);

        Card battleWarrior = new Card("Battle Warrior", "A warrior that fights with his bare hands!!!", 1300,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 3, null, 700, 1000,Attribute.EARTH);

        Card crawlingDragon = new Card("Crawling dragon", "This weakened dragon can no longer fly, but is still a deadly force to be reckoned with.", 3900,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 5, null, 1600, 1400,Attribute.EARTH);

        Card flameManipulator = new Card("Flame Manipulator", "This Spellcaster attacks enemies with fire-related spells such as \"Sea of Flames\" and \"Wall of Fire\".", 1500,
                new ArrayList<>(List.of(CardType.SPELLCASTER)), CardCategory.MONSTER,
                3, 3, null, 900, 100,Attribute.FIRE);

        Card blueEyesWhiteDragon = new Card("Blue Eyes White Dragon", "This legendary dragon is a powerful engine of destruction.", 11300,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 8, null, 3000, 2500,Attribute.LIGHT);

        // should has something not effect
        Card crabTurtle = new Card("Crab Turtle", "This monster can only be Ritual Summoned with the Ritual Spell Card, \"Turtle Oath\".", 10200,
                new ArrayList<>(List.of(CardType.AQUA, CardType.RITUAL)), CardCategory.MONSTER,
                3, 7, null, 2550, 2500,Attribute.WATER);

        // should has something not effect
        Card skullGuardian = new Card("Skull Guardian", "This monster can only be Ritual Summoned with the Ritual Spell Card,", 7900,
                new ArrayList<>(List.of(CardType.WARRIOR, CardType.RITUAL)), CardCategory.MONSTER,
                3, 7, null, 2050, 2500,Attribute.LIGHT);

        Card slotMachine = new Card("Slot Machine", "The machine's ability is said to vary according to its slot results.", 7500,
                new ArrayList<>(List.of(CardType.MACHINE)), CardCategory.MONSTER,
                3, 7, null, 2000, 2300,Attribute.DARK);

        Card haniwa = new Card("Haniwa", "An earthen figure that protects the tomb of an ancient ruler.", 600,
                new ArrayList<>(List.of(CardType.ROCK)), CardCategory.MONSTER,
                3, 2, null, 500, 500,Attribute.EARTH);

        // should has effect
        Card manEaterBug = new Card("Man Eater Bug", "FLIP: Target 1 monster on the field; destroy that target.", 600,
                new ArrayList<>(List.of(CardType.INSECT, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 2, null, 450, 600,Attribute.EARTH);

        // should has some shit
        Card gateGuardian = new Card("Gate Guardian", "Cannot be Normal Summoned/Set. ", 20000,
                new ArrayList<>(List.of(CardType.WARRIOR, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 11, null, 3750, 3400,Attribute.DARK);

        // what the fuck is this
        Card scanner = new Card("Scanner", "nothing", 8000,
                new ArrayList<>(List.of(CardType.MACHINE, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 1, null, 0, 0,Attribute.LIGHT);

        Card bitron = new Card("Bitron", "nothing", 1000,
                new ArrayList<>(List.of(CardType.CYBERSE)), CardCategory.MONSTER,
                3, 2, null, 200, 2000,Attribute.EARTH);

        // should has effect
        Card marshmallon = new Card("Marshmallon", "A new species found in electronic space. There's not much information on it.", 700,
                new ArrayList<>(List.of(CardType.FAIRY, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 3, null, 300, 500,Attribute.LIGHT);

        // should has effect
        Card beastKingBarbaros = new Card("Beast King Barbaros", "Cannot be destroyed by battle. ", 9200,
                new ArrayList<>(List.of(CardType.BEASTWARRIOR, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 8, null, 3000, 1200,Attribute.EARTH);

        // should has effect
        Card texchanger = new Card("Texchanger", "You can Normal Summon/Set this card without Tributing, but its original ATK becomes 1900.", 200,
                new ArrayList<>(List.of(CardType.CYBERSE, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 1, null, 100, 100,Attribute.DARK);

        Card leotron = new Card("Leotron", "A territorial electronic monster that guards its own domain.", 2500,
                new ArrayList<>(List.of(CardType.CYBERSE)), CardCategory.MONSTER,
                3, 4, null, 2000, 0,Attribute.EARTH);

        // should has effect
        Card theCalculator = new Card("The Calculator", "nothing", 8000,
                new ArrayList<>(List.of(CardType.THUNDER, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 2, null, 0, 0,Attribute.LIGHT);

        Card alexandriteDragon = new Card("Alexandrite Dragon", "Many of the czars' lost jewels can be found in the scales of this priceless dragon. Its creator remains a mystery, along with how they acquired the imperial treasures.", 2600,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 4, null, 2000, 100,Attribute.LIGHT);

        // should has effect
        Card mirageDragon = new Card("Mirage Dragon", "Your opponent cannot activate Trap Cards during the Battle Phase.", 2500,
                new ArrayList<>(List.of(CardType.DRAGON, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 4, null, 1600, 600,Attribute.LIGHT);

        // should has effect
        Card heraldOfCreation = new Card("Herald of Creation", "Once per turn: You can discard 1 card, then target 1 Level 7 or higher monster in your Graveyard", 2700,
                new ArrayList<>(List.of(CardType.SPELLCASTER, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 4, null, 1800, 600,Attribute.LIGHT);

        // should has effect
        Card exploderDragon = new Card("Exploder Dragon", "If this card is destroyed by battle and sent to the Graveyard", 1000,
                new ArrayList<>(List.of(CardType.DRAGON, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 3, null, 1000, 0,Attribute.EARTH);

        Card warriorDaiGrepher = new Card("Warrior Dai Grepher", "The warrior who can manipulate dragons. Nobody knows his mysterious past.", 3400,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 4, null, 1700, 1600,Attribute.EARTH);

        Card darkBlade = new Card("Dark Blade", "They say he is a dragon-manipulating warrior from the dark world.", 3500,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 4, null, 1800, 1500,Attribute.DARK);

        Card wattaildragon = new Card("Wattaildragon", "Capable of indefinite flight. Attacks by wrapping its body with electricity and ramming into opponents.", 5800,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 6, null, 2500, 1700,Attribute.LIGHT);

        // should has effect
        Card terratiger = new Card("Terratiger", "When this card is Normal Summoned: You can Special Summon 1 Level 4 or lower Normal Monster from your hand in Defense Position.", 3200,
                new ArrayList<>(List.of(CardType.WARRIOR, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 4, null, 1800, 1200,Attribute.EARTH);

        // should has effect
        Card theTricky = new Card("The Tricky", "You can Special Summon this card (from your hand) by discarding 1 card.", 4300,
                new ArrayList<>(List.of(CardType.SPELLCASTER, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 5, null, 2000, 1200,Attribute.WIND);

        Card spiralSerpent = new Card("Spiral Serpent", "When huge whirlpools lay cities asunder, it is the hunger of this sea serpent at work.", 11700,
                new ArrayList<>(List.of(CardType.SEASERPENT)), CardCategory.MONSTER,
                3, 8, null, 2900, 2900,Attribute.WATER);


    }


}
