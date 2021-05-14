package Controller;

import Model.Card;
import Model.CardCategory;
import Model.CardType;
import Model.Effects.*;
import Model.Player;


import java.util.ArrayList;
import java.util.List;

public class CardController {

    public static void initialCards() {
        initialMonsters();
        initialSpells();
        initialTraps();
    }

    private static void initialTraps() {
        Card magicCylinder = new Card("Magic Cylinder", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new AttackDirectToOpponent())), 0, 0);

        Card mirrorForce = new Card("Mirror Force", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroyAllOpponentMonsters())), 0, 0);

        Card mindCrush = new Card("Mind Crush", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new GuessOpponentCard())), 0, 0);

        Card trapHole = new Card("Trap Hole", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroySelectedMonster())), 0, 0);

        Card torrentialTribute = new Card("Torrential Tribute", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroyAllMonsters())), 0, 0);

        Card timeSeal = new Card("Time Seal", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 1, 0,
                new ArrayList<>(List.of(new DestroySelectedMonster())), 0, 0);

        Card negateAttack = new Card("Negate Attack", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new EndBattlePhaseForOpponent())), 0, 0);

        Card solemnWarning = new Card("Solemn Warning", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new GiveYourLife())), 0, 0);

        Card magicJammer = new Card("Magic Jammer", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroySpecificSpellCard())), 0, 0);

        Card callOfTheHaunted = new Card("Call Of The Haunted", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new SummonFromGraveYard())), 0, 0);
    }

    private static void initialSpells() {
        // not complete
        Card monsterReborn = new Card("Monster Reborn", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new ChooseFromGraveyardAndSpecialSummon())), 0, 0);
// not complete
        Card terraforming = new Card("Terraforming", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new AddFieldSpellToMyHand())), 0, 0);

        Card potOfGreed = new Card("Pot of Greed", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new PickTwoCardsFromTopOfDeck())), 0, 0);

        Card raigeki = new Card("Raigeki", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new DestroyAllOpponentMonsters())), 0, 0);

        // what the fuck
        Card changeOfHeart = new Card("Change of Heart", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                null, 0, 0);

        Card harpieFeatherDuster = new Card("Harpie’s Feather Duster", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 1, 0,
                new ArrayList<>(List.of(new DestroyAllOpponentSpellTrap())), 0, 0);

        // what the fuck
        Card swordsOfRevealingLight = new Card("Swords of Revealing Light", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new DestroyAllOpponentSpellTrap())), 0, 0);

        Card darkHole = new Card("Dark Hole", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new DestroyAllMonsters())), 0, 0);
        // what
        Card supplySquad = new Card("Supply Squad", "nothing", 1,
                new ArrayList<>(List.of(CardType.CONTINUES)), CardCategory.SPELL, 3, 0,
                null, 0, 0);

        Card spellAbsorption = new Card("Spell Absorption", "nothing", 1,
                new ArrayList<>(List.of(CardType.CONTINUES)), CardCategory.SPELL, 3, 0,
                null, 0, 0);

        // not complete
        Card messengerOfPeace = new Card("Messenger of peace", "nothing", 1,
                new ArrayList<>(List.of(CardType.CONTINUES)), CardCategory.SPELL, 3, 0,
                null, 0, 0);

        // not complete
        Card twinTwisters = new Card("Twin Twisters", "nothing", 1,
                new ArrayList<>(List.of(CardType.QUICKPLAY)), CardCategory.SPELL, 3, 0,
                null, 0, 0);
        // same problem not solved
        Card mysticalSpaceTyphoon = new Card("Mystical space typhoon", "nothing", 1,
                new ArrayList<>(List.of(CardType.QUICKPLAY)), CardCategory.SPELL, 3, 0,
                null, 0, 0);
        // what
        Card ringOfDefense = new Card("Ring of Defense", "nothing", 1,
                new ArrayList<>(List.of(CardType.QUICKPLAY)), CardCategory.SPELL, 3, 0,
                null, 0, 0);
        // not sure
        Card yami = new Card("Yami", "nothing", 1,
                new ArrayList<>(List.of(CardType.FIELD)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAttackDeffenceForMonstersFieldSpellCaster())), 0, 0);

        // not sure
        Card forest = new Card("Forest", "nothing", 1,
                new ArrayList<>(List.of(CardType.FIELD)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAttackDeffenceForMonstersInsectBeast())), 0, 0);

        Card closedForest = new Card("Closed Forest", "nothing", 1,
                new ArrayList<>(List.of(CardType.FIELD)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAttackOfMonsterBeastForEachGraveyard())), 0, 0);

        Card umiiruka = new Card("UMIIRUKA", "nothing", 1,
                new ArrayList<>(List.of(CardType.FIELD)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAttackDeffenceOfAqua())), 0, 0);

        Card swordOfDarkDestruction = new Card("Sword of Dark Destruction", "nothing", 1,
                new ArrayList<>(List.of(CardType.EQUIP)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeEquipedMonstersAttackDeffence())), 0, 0);

        Card blackPendant = new Card("Black Pendant", "nothing", 1,
                new ArrayList<>(List.of(CardType.EQUIP)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeAllEquipAttack())), 0, 0);

        Card unitedWeStand = new Card("United We Stand", "nothing", 1,
                new ArrayList<>(List.of(CardType.EQUIP)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeMyEquipAttackDeffence())), 0, 0);

        Card magnumShield = new Card("Magnum Shield", "nothing", 1,
                new ArrayList<>(List.of(CardType.EQUIP)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new ChangeWarriorAttackDeffenceReplace())), 0, 0);

        Card advancedRitualArt = new Card("Advanced Ritual Art", "nothing", 1,
                new ArrayList<>(List.of(CardType.RITUAL)), CardCategory.SPELL, 3, 0,
                new ArrayList<>(List.of(new RitualSummon())), 0, 0);
    }

    private static void initialMonsters() {
        Card commandKnight = new Card("Command Knight", "Gain 400 ATK to all Warrior-Type monsters," +
                " if you control another monster, monsters your opponent " +
                "controls cannot target this card for an attack", 1,
                new ArrayList<>(List.of(CardType.WARRIOR, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 4, new ArrayList<>(List.of(new IncreaseAttack())), 1000, 1000);

        Card battleOx = new Card("Battle Ox", "nothing", 1,
                new ArrayList<>(List.of(CardType.BEASTWARRIOR)), CardCategory.MONSTER,
                3, 4, null, 1700, 1000);

        Card axeRaider = new Card("Axe Raider", "nothing", 1,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 4, null, 1700, 1150);

        Card hornImp = new Card("Horn Imp", "nothing", 1,
                new ArrayList<>(List.of(CardType.FIEND)), CardCategory.MONSTER,
                3, 4, null, 1300, 1000);

        Card yomiShip = new Card("Yomi Ship", "nothing", 1,
                new ArrayList<>(List.of(CardType.AQUA, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 3, null, 800, 1400);

        Card silverFang = new Card("Silver Fang", "nothing", 1,
                new ArrayList<>(List.of(CardType.BEAST)), CardCategory.MONSTER,
                3, 3, null, 1200, 800);

        Card suijin = new Card("Suijin", "nothing", 1,
                new ArrayList<>(List.of(CardType.AQUA, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 7, null, 2500, 2400);

        Card fireyarou = new Card("Fireyarou", "nothing", 1,
                new ArrayList<>(List.of(CardType.PYRO)), CardCategory.MONSTER,
                3, 4, null, 1300, 1000);

        Card curtainOfDarkOnes = new Card("Curtain of Dark Ones", "nothing", 1,
                new ArrayList<>(List.of(CardType.SPELLCASTER)), CardCategory.MONSTER,
                3, 2, null, 600, 500);

        Card feralImp = new Card("Feral Imp", "nothing", 1,
                new ArrayList<>(List.of(CardType.FIEND)), CardCategory.MONSTER,
                3, 4, null, 1300, 1400);

        Card darkMagician = new Card("Dark Magician", "nothing", 1,
                new ArrayList<>(List.of(CardType.SPELLCASTER)), CardCategory.MONSTER,
                3, 7, null, 2500, 2100);

        Card wattkid = new Card("Wattkid", "nothing", 1,
                new ArrayList<>(List.of(CardType.THUNDER)), CardCategory.MONSTER,
                3, 3, null, 1000, 500);

        Card babyDragon = new Card("Baby Dragon", "nothing", 1,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 3, null, 1200, 700);

        Card heroOfTheEast = new Card("Hero of the East", "nothing", 1,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 3, null, 1100, 1000);

        Card battleWarrior = new Card("Battle Warrior", "nothing", 1,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 3, null, 700, 1000);

        Card crawlingDragon = new Card("Crawling dragon", "nothing", 1,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 5, null, 1600, 1400);

        Card flameManipulator = new Card("Flame Manipulator", "nothing", 1,
                new ArrayList<>(List.of(CardType.SPELLCASTER)), CardCategory.MONSTER,
                3, 3, null, 900, 100);

        Card blueEyesWhiteDragon = new Card("Blue-Eyes White Dragon", "nothing", 1,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 8, null, 3000, 2500);

        // should has something not effect
        Card crabTurtle = new Card("Crab Turtle", "nothing", 1,
                new ArrayList<>(List.of(CardType.AQUA, CardType.RITUAL)), CardCategory.MONSTER,
                3, 7, null, 2550, 2500);

        // should has something not effect
        Card skullGuardian = new Card("Skull Guardian", "nothing", 1,
                new ArrayList<>(List.of(CardType.WARRIOR, CardType.RITUAL)), CardCategory.MONSTER,
                3, 7, null, 2050, 2500);

        Card slotMachine = new Card("Slot Machine", "nothing", 1,
                new ArrayList<>(List.of(CardType.MACHINE)), CardCategory.MONSTER,
                3, 7, null, 2000, 2300);

        Card haniwa = new Card("Haniwa", "nothing", 1,
                new ArrayList<>(List.of(CardType.ROCK)), CardCategory.MONSTER,
                3, 2, null, 500, 500);

        // should has effect
        Card manEaterBug = new Card("Man-Eater Bug", "nothing", 1,
                new ArrayList<>(List.of(CardType.INSECT, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 2, null, 450, 600);

        // should has some shit
        Card gateGuardian = new Card("Gate Guardian", "nothing", 1,
                new ArrayList<>(List.of(CardType.WARRIOR, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 11, null, 3750, 3400);

        // what the fuck is this
        Card scanner = new Card("Scanner", "nothing", 1,
                new ArrayList<>(List.of(CardType.MACHINE, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 1, null, 0, 0);

        Card bitron = new Card("Bitron", "nothing", 1,
                new ArrayList<>(List.of(CardType.CYBERSE)), CardCategory.MONSTER,
                3, 2, null, 200, 2000);

        // should has effect
        Card marshmallon = new Card("Marshmallon", "nothing", 1,
                new ArrayList<>(List.of(CardType.FAIRY, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 3, null, 300, 500);

        // should has effect
        Card beastKingBarbaros = new Card("Beast King Barbaros", "nothing", 1,
                new ArrayList<>(List.of(CardType.BEASTWARRIOR, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 8, null, 3000, 1200);

        // should has effect
        Card texchanger = new Card("Texchanger", "nothing", 1,
                new ArrayList<>(List.of(CardType.CYBERSE, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 1, null, 100, 100);

        Card leotron = new Card("Leotron", "nothing", 1,
                new ArrayList<>(List.of(CardType.CYBERSE)), CardCategory.MONSTER,
                3, 4, null, 2000, 0);

        // should has effect
        Card theCalculator = new Card("The Calculator", "nothing", 1,
                new ArrayList<>(List.of(CardType.THUNDER, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 2, null, 0, 0);

        Card alexandriteDragon = new Card("Alexandrite Dragon", "nothing", 1,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 4, null, 2000, 100);

        // should has effect
        Card mirageDragon = new Card("Mirage Dragon", "nothing", 1,
                new ArrayList<>(List.of(CardType.DRAGON, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 4, null, 1600, 600);

        // should has effect
        Card heraldOfCreation = new Card("Herald of Creation", "nothing", 1,
                new ArrayList<>(List.of(CardType.SPELLCASTER, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 4, null, 1800, 600);

        // should has effect
        Card exploderDragon = new Card("Exploder Dragon", "nothing", 1,
                new ArrayList<>(List.of(CardType.DRAGON, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 3, null, 1000, 0);

        Card warriorDaiGrepher = new Card("Warrior Dai Grepher", "nothing", 1,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 4, null, 1700, 1600);

        Card darkBlade = new Card("Dark Blade", "nothing", 1,
                new ArrayList<>(List.of(CardType.WARRIOR)), CardCategory.MONSTER,
                3, 4, null, 1800, 1500);

        Card wattaildragon = new Card("Wattaildragon", "nothing", 1,
                new ArrayList<>(List.of(CardType.DRAGON)), CardCategory.MONSTER,
                3, 6, null, 2500, 1700);

        // should has effect
        Card terratiger = new Card("Terratiger", "nothing", 1,
                new ArrayList<>(List.of(CardType.WARRIOR, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 4, null, 1800, 1200);

        // should has effect
        Card theTricky = new Card("The Tricky", "nothing", 1,
                new ArrayList<>(List.of(CardType.SPELLCASTER, CardType.EFFECT)), CardCategory.MONSTEREFFECT,
                3, 5, null, 2000, 1200);

        Card spiralSerpent = new Card("Spiral Serpent", "nothing", 1,
                new ArrayList<>(List.of(CardType.SEASERPENT)), CardCategory.MONSTER,
                3, 8, null, 2900, 2900);


    }


}
