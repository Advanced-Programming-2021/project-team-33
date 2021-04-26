package Model;

import java.lang.reflect.AnnotatedArrayType;
import java.util.ArrayList;

public class Deck {
    static ArrayList<Deck> decks = new ArrayList<>();
    ArrayList<Card> mainDeck = new ArrayList<>();
    ArrayList<Card> sideDeck = new ArrayList<>();
    String deckName;
    boolean isDeckValid = false;

    public Deck(String deckName) {
        this.deckName = deckName;
        decks.add(this);
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

    public static Deck getDeckByName(String deckName){
        for (Deck deck : decks) {
            if (deck.deckName.equals(deckName)) return deck;
        }
        return null;
    }

    public static boolean getDeckValidation(Deck deck){
        return deck.isDeckValid;
    }

}
