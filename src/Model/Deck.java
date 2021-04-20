package Model;

import java.util.ArrayList;

public class Deck {
    ArrayList<Card> mainDeck = new ArrayList<>();
    ArrayList<Card> sideDeck = new ArrayList<>();
    String deckName;

    public Deck(String deckName) {
        this.deckName = deckName;
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

}
