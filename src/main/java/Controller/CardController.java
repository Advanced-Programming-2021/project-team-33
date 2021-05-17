package Controller;

import Model.*;
import Model.Effects.*;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                new ArrayList<>(List.of(new AttackDirectToOpponent())), 0, 0);

        Card mirrorForce = new Card("Mirror Force",
                "When an opponent's monster declares an attack: Destroy all your opponent's Attack Position monsters.", 2000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroyAllOpponentMonsters())), 0, 0);

        Card mindCrush = new Card("Mind Crush",
                "Declare 1 card name; if that card is in your opponent's hand," +
                        " they must discard all copies of it, otherwise you discard 1 random card", 2000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new GuessOpponentCard())), 0, 0);

        Card trapHole = new Card("Trap Hole",
                "When your opponent Normal or Flip Summons 1 monster with 1000 or more ATK:" +
                        " Target that monster; destroy that target.", 2000,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroySelectedMonster())), 0, 0);

        Card torrentialTribute = new Card("Torrential Tribute", "nothing", 1,
                new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 3, 0,
                new ArrayList<>(List.of(new DestroyAllMonsters())), 0, 0);

        Card timeSeal = new Card("Time Seal", "Skip the Draw Phase of your opponent's next turn.",
                2000, new ArrayList<>(List.of(CardType.NORMAL)), CardCategory.TRAP, 1, 0,
                new ArrayList<>(List.of(new TakeNextRoundFromOpponent())), 0, 0);

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
        File file = new File("Monster.csv");
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Pattern pattern = Pattern.compile("(.+),(\\d+),(.+),(.+),(.+),(\\d+),(\\d+),(.+),(\\d+)");
                Matcher matcher = pattern.matcher(scanner.nextLine().replace("\"", ""));
                ArrayList<CardType> cardTypes;
                ArrayList<Effect> effects;
                CardCategory cardCategory;
                while (matcher.find()) {
                    if (matcher.group(1).equals("Command Knight")) {
                        effects = new ArrayList<>(List.of(new IncreaseAttack()));
                    } else {
                        effects = null;
                    }
                    if (matcher.group(5).equals("Normal")) {
                        cardCategory = CardCategory.MONSTER;
                    } else {
                        cardCategory = CardCategory.MONSTEREFFECT;
                    }
                    cardTypes = new ArrayList<>();
                    if (matcher.group(4).equals("Beast-Warrior")) cardTypes.add(CardType.BEASTWARRIOR);
                    else {
                        if (matcher.group(4).equals("Sea Serpent")) cardTypes.add(CardType.SEASERPENT);
                        else cardTypes.add(CardType.valueOf(matcher.group(4).toUpperCase()));
                    }
                    Card card = new Card(
                            matcher.group(1),
                            matcher.group(8),
                            Integer.parseInt(matcher.group(9)),
                            cardTypes,
                            cardCategory,
                            3,
                            Integer.parseInt(matcher.group(2)),
                            effects,
                            Integer.parseInt(matcher.group(6)),
                            Integer.parseInt(matcher.group(7)));
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Error");
        }
    }
}
