package Model;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    private static ArrayList<Deck> decks = new ArrayList<>();
    private ArrayList<Card> mainDeck = new ArrayList<>();
    private  ArrayList<Card> sideDeck = new ArrayList<>();
    private String deckName;
    private boolean isDeckActive = false;

    public Deck(String deckName) {
        this.deckName = deckName;
    }

    public void setDeckActive(boolean deckActive) {
        isDeckActive = deckActive;
    }

    public ArrayList<Card> getMainDeck() {
        return mainDeck;
    }

    public ArrayList<Card> getSideDeck() {
        return sideDeck;
    }

    public boolean isDeckActive() {
        return isDeckActive;
    }

    public String getDeckName() {
        return deckName;
    }

    public void addToMainDeck(Card card) {
        mainDeck.add(card);
    }

    public void addToSideDeck(Card card) {
        sideDeck.add(card);
    }

    public void removeFromMainDeck(Card card) {
        for (int i = 0; i < mainDeck.size(); i++) {
            if (mainDeck.get(i).getCardName().equals(card.getCardName())) mainDeck.remove(card);
        }
    }

    public void removeFromSideDeck(Card card) {
        for (int i = 0; i < sideDeck.size(); i++) {
            if (sideDeck.get(i).getCardName().equals(card.getCardName())) sideDeck.remove(card);
        }
    }

    public boolean isMainDeckFull() {
        return mainDeck.size() > 40;
    }

    public boolean isSideDeckFull() {
        return sideDeck.size() > 10;
    }

    public boolean isDeckValid(Deck deck) {
        ArrayList<String> mainCardNames = new ArrayList<>();
        for(int i = 0; i < deck.mainDeck.size(); i++){
            mainCardNames.add(deck.mainDeck.get(i).getCardName());
        }
        for(int i = 0; i < deck.mainDeck.size(); i++){
            if(Collections.frequency(mainCardNames,deck.mainDeck.get(i).getCardName()) > deck.mainDeck.get(i).getLimit())
                return false;
        }
        return true;
    }

    public static Deck getDeckByName(String deckName) {
        for (Deck deck : decks) {
            if (deck.deckName.equals(deckName)) return deck;
        }
        return null;
    }

    public boolean getInvalidCard(String deckName, String cardName) {
        ArrayList<String> cardNames = new ArrayList<>();
        for(int i = 0; i < Player.thePlayer.getDeckByName(deckName).mainDeck.size(); i++){
            cardNames.add(Player.thePlayer.getDeckByName(deckName).mainDeck.get(i).getCardName());
        }
        return Collections.frequency(cardNames, cardName) == 3;
    }

    public static boolean getDeckActivation(Deck deck) {
        return deck.isDeckActive;
    }

}
