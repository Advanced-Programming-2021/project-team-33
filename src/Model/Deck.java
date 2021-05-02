package Model;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    static ArrayList<Deck> decks = new ArrayList<>();
    ArrayList<Card> mainDeck = new ArrayList<>();
    ArrayList<Card> sideDeck = new ArrayList<>();
    String deckName;
    boolean isDeckActive = false;

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
            if (mainDeck.get(i).cardName.equals(card.cardName)) mainDeck.remove(card);
        }
    }

    public void removeFromSideDeck(Card card) {
        for (int i = 0; i < sideDeck.size(); i++) {
            if (sideDeck.get(i).cardName.equals(card.cardName)) sideDeck.remove(card);
        }
    }

    public boolean isMainDeckFull() {
        return mainDeck.size() > 40;
    }

    public boolean isSideDeckFull() {
        return sideDeck.size() > 10;
    }

    public boolean isDeckValid() {
        return true;
    }

    public static Deck getDeckByName(String deckName) {
        for (Deck deck : decks) {
            if (deck.deckName.equals(deckName)) return deck;
        }
        return null;
    }

    public String getInvalidCard() {
        for (int i = 0; i < Card.getCards().size(); i++) {
            if (Collections.frequency(mainDeck, Card.getCards().get(i).cardName) == 3)
                return Card.getCards().get(i).cardName;
        }
        return "";
    }

    public static boolean getDeckActivation(Deck deck) {
        return deck.isDeckActive;
    }

}
