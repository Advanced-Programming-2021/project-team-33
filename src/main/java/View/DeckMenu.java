package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.Util;
import Model.Card;
import Model.CardCategory;
import Model.Deck;
import Model.Player;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.regex.Matcher;

public class DeckMenu {

    public void start(){

    }


    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        createDeck(Util.getCommand(input, "deck create (\\S+)"));
        deleteDeck(Util.getCommand(input, "deck delete (\\S+)"));
        activateDeck(Util.getCommand(input, "deck set-activate (\\S+)"));
        addCardToDeck(Util.getCommand(input, "deck add-card --card (.+?) --deck (\\S+)( --side)?"));
        removeCardFromDeck(Util.getCommand(input, "deck rm-card --card (.+?) --deck (\\S+)( --side)?"));
        showAllDecks(Util.getCommand(input, "deck show --all"));
        showDeck(Util.getCommand(input, "deck show --deck-name (\\S+)( --side)?"));
        showAllCards(Util.getCommand(input, "deck show --cards"));
        CardMenu.showCard(Util.getCommand(input, "card show (.+?)"));
        MainMenu.exitMenu(Util.getCommand(input, "menu exit"));
    }

    private void createDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String deckName = matcher.group(1);
            if (ProgramController.isDeckExist(deckName))
                System.out.println("deck with name " + deckName + " already exists");
            else {
                GameController.createDeck(deckName);
                System.out.println("deck created successfully!");
            }
        }
    }

    private void deleteDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String deckName = matcher.group(1);
            if (!ProgramController.isDeckExist(deckName))
                System.out.println("deck with name " + deckName + " does not exist");
            else {
                GameController.deleteDeck(deckName);
                System.out.println("deck deleted successfully!");
            }
        }
    }

    private void activateDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String deckName = matcher.group(1);
            if (!ProgramController.isDeckExist(deckName))
                System.out.println("deck with name " + deckName + " does not exist");
            else {
                GameController.activateDeck(deckName);
                System.out.println("deck activated successfully");
            }
        }
    }

    private void addCardToDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String cardName = matcher.group(1);
            String deckName = matcher.group(2);
            boolean isSide = false;
            if (matcher.group(3) != null) isSide = true;
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
                GameController.addCardToDeck(deckName, cardName, isSide);
                System.out.println("card added to deck successfully");
            }
        }
    }

    private void removeCardFromDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String cardName = matcher.group(1);
            String deckName = matcher.group(2);
            boolean isSide = false;
            if (matcher.group(3) != null) isSide = true;
            if (!ProgramController.isDeckExist(deckName))
                System.out.println("deck with name " + deckName + " does not exist");
            else if (!ProgramController.isCardExistInMainDeck(cardName, deckName))
                System.out.println("card with name " + cardName + " does not exist in main deck");
            else if (!ProgramController.isCardExistInMainDeck(cardName, deckName))
                System.out.println("card with name " + cardName + " does not exist in side deck");
            else {
                GameController.removeCardFromDeck(deckName, cardName, isSide);
                System.out.println("card removed form deck successfully");
            }
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


}
