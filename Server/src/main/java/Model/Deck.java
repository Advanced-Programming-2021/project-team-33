package Model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

public class Deck implements Serializable {
@Serial
private static final long serialVersionUID = 6529685098267757699L;
    static ArrayList<Deck> decks = new ArrayList<>();
    ArrayList<String> mainDeck = new ArrayList<>();
    ArrayList<String> sideDeck = new ArrayList<>();
    String deckName;
    boolean isDeckActive = false;

    public Deck(String deckName) {
        this.deckName = deckName;
    }

    public void setDeckActive(boolean deckActive) {
        isDeckActive = deckActive;
    }

    public ArrayList<String> getMainDeck() {
        return mainDeck;
    }

    public ArrayList<String> getSideDeck() {
        return sideDeck;
    }

    public boolean isDeckActive() {
        return isDeckActive;
    }

    public String getDeckName() {
        return deckName;
    }

    public void addToMainDeck(String card) {
        mainDeck.add(card);
    }

    public void addToSideDeck(String card) {
        sideDeck.add(card);
    }

    public void removeFromMainDeck(String cardName) {
        for (int i = 0; i < mainDeck.size(); i++) {
            if (mainDeck.get(i).equals(cardName)) {
                mainDeck.remove(i);
                break;
            }
        }
    }

    public void removeFromSideDeck(String cardName) {
        for (int i = 0; i < sideDeck.size(); i++) {
            if (sideDeck.get(i).equals(cardName)) {
                sideDeck.remove(i);
                break;
            }
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
            mainCardNames.add(deck.mainDeck.get(i));
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
            cardNames.add(Player.thePlayer.getDeckByName(deckName).mainDeck.get(i));
        }
        return Collections.frequency(cardNames, cardName) == 3;
    }

    public static boolean getDeckActivation(Deck deck) {
        return deck.isDeckActive;
    }

}
