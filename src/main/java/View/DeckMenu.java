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
            Communicate.output(GameController.createDeck(deckName));
        }
    }

    private void deleteDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String deckName = matcher.group(1);
            if (!ProgramController.isDeckExist(deckName))
                Communicate.output("deck with name " + deckName + " does not exist");
            else {
                GameController.deleteDeck(deckName);
                Communicate.output("deck deleted successfully!");
            }
        }
    }

    private void activateDeck(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String deckName = matcher.group(1);
            if (!ProgramController.isDeckExist(deckName))
                Communicate.output("deck with name " + deckName + " does not exist");
            else {
                GameController.activateDeck(deckName);
                Communicate.output("deck activated successfully");
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
                Communicate.output("deck with name " + deckName + " does not exist");
            else if (!ProgramController.isCardExist(cardName) ||
                    !Player.thePlayer.getListOfCards().contains(Player.getCardByName(cardName)))
                Communicate.output("card with name " + cardName + " does not exist");
            else if (Player.getDeckByName(deckName).isMainDeckFull())
                Communicate.output("main deck is full");
            else if (Player.getDeckByName(deckName).isSideDeckFull())
                Communicate.output("side deck is full");
            else if (Player.getDeckByName(deckName).getInvalidCard(deckName, cardName)) {
                Communicate.output("there are already three cards with name " + cardName +
                        " in deck " + deckName + "name");
            } else {
                GameController.addCardToDeck(deckName, cardName, isSide);
                Communicate.output("card added to deck successfully");
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
                Communicate.output("deck with name " + deckName + " does not exist");
            else if (!ProgramController.isCardExistInMainDeck(cardName, deckName))
                Communicate.output("card with name " + cardName + " does not exist in main deck");
            else if (!ProgramController.isCardExistInMainDeck(cardName, deckName))
                Communicate.output("card with name " + cardName + " does not exist in side deck");
            else {
                GameController.removeCardFromDeck(deckName, cardName, isSide);
                Communicate.output("card removed form deck successfully");
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
                Communicate.output("deck with name " + deckName + " does not exist");
            else if (!isSide) {
                Communicate.output("Deck: " + deckName + "\nMain Deck:\n\nMonsters:");
                for (int i = 0; i < Player.getDeckByName(deckName).getMainDeck().size(); i++) {
                    if (Player.getDeckByName(deckName).getMainDeck().get(i).getCardCategory().equals(CardCategory.MONSTER) ||
                            Player.getDeckByName(deckName).getMainDeck().get(i).getCardCategory().equals(CardCategory.MONSTEREFFECT))
                        Communicate.output(Player.getDeckByName(deckName).getMainDeck().get(i).getCardName() + ": " +
                                Player.getDeckByName(deckName).getMainDeck().get(i).getDescription());
                }
                Communicate.output("\nSpell and Traps:");
                for (int i = 0; i < Player.getDeckByName(deckName).getMainDeck().size(); i++) {
                    if (Player.getDeckByName(deckName).getMainDeck().get(i).getCardCategory().equals(CardCategory.SPELL) ||
                            Player.getDeckByName(deckName).getMainDeck().get(i).getCardCategory().equals(CardCategory.TRAP))
                        Communicate.output(Player.getDeckByName(deckName).getMainDeck().get(i).getCardName() + ": " +
                                Player.getDeckByName(deckName).getMainDeck().get(i).getDescription());
                }
            } else {
                Communicate.output("Deck: " + deckName + "\nSide Deck:\n\nMonsters:");
                for (int i = 0; i < Player.getDeckByName(deckName).getSideDeck().size(); i++) {
                    if (Player.getDeckByName(deckName).getSideDeck().get(i).getCardCategory().equals(CardCategory.MONSTER) ||
                            Player.getDeckByName(deckName).getSideDeck().get(i).getCardCategory().equals(CardCategory.MONSTEREFFECT))
                        Communicate.output(Player.getDeckByName(deckName).getSideDeck().get(i).getCardName() + ": " +
                                Player.getDeckByName(deckName).getSideDeck().get(i).getDescription());
                }
                Communicate.output("\nSpell and Traps:");
                for (int i = 0; i < Player.getDeckByName(deckName).getSideDeck().size(); i++) {
                    if (Player.getDeckByName(deckName).getSideDeck().get(i).getCardCategory().equals(CardCategory.SPELL) ||
                            Player.getDeckByName(deckName).getSideDeck().get(i).getCardCategory().equals(CardCategory.TRAP))
                        Communicate.output(Player.getDeckByName(deckName).getSideDeck().get(i).getCardName() + ": " +
                                Player.getDeckByName(deckName).getSideDeck().get(i).getDescription());
                }
            }

        }
    }

    private void showAllDecks(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String valid = "invalid";
            Communicate.output("Decks:\nActive deck:");
            if (Player.thePlayer.getActiveDeck() != null) {
                Deck activeDeck = Player.thePlayer.getActiveDeck();
                if (activeDeck.isDeckValid(activeDeck)) valid = "valid";
                Communicate.output(activeDeck.getDeckName() + ": main deck " +
                        activeDeck.getMainDeck().size() + ", side deck " +
                        activeDeck.getSideDeck().size() + ", " + valid);
            }
            Communicate.output("Other decks:");
            for (int i = 0; i < Player.thePlayer.getListOfDecks().size(); i++) {
                if (!Player.thePlayer.getListOfDecks().get(i).isDeckActive()) {
                    Deck otherDeck = Player.thePlayer.getListOfDecks().get(i);
                    if (otherDeck.isDeckValid(otherDeck)) valid = "valid";
                    Communicate.output(otherDeck.getDeckName() + ": main deck " +
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
            for (int i = 0; i < Player.thePlayer.getListOfCards().size(); i++) {
                cardNameList.add(Player.thePlayer.getListOfCards().get(i).getCardName()
                        + ": " + Player.thePlayer.getListOfCards().get(i).getDescription());
            }
            cardNameList.sort(Comparator.naturalOrder());
            for (int i = 0; i < cardNameList.size(); i++) {
                Communicate.output(cardNameList.get(i));
            }
        }

    }


}
