package Model;

import java.util.ArrayList;

public class Board {
    Player player;
    public ArrayList<Card> fieldCardsForMonsters = new ArrayList<>();
    public ArrayList<Card> fieldCardsForSpellTraps = new ArrayList<>();
    public ArrayList<Card> graveyard = new ArrayList<>();
    public ArrayList<Card> deck;
    public ArrayList<Card> hand = new ArrayList<>();
    public ArrayList<Card> fieldZone = new ArrayList<>();

    public Board(Player player) {
        deck = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            fieldCardsForMonsters.add(null);
            fieldCardsForSpellTraps.add(null);
        }
        fieldZone.add(null);
        deck.addAll(player.getActiveDeck().getMainDeck());
    }

    public Player getPlayer() {
        return player;
    }

    public Card getCardFromMonsterField(int number) {
        if (fieldCardsForMonsters.get(number) != null) return fieldCardsForMonsters.get(number);
        return null;
    }

    public ArrayList<Card> getFieldZone() {
        return fieldZone;
    }

    public ArrayList<Card> getGraveyard() {
        return graveyard;
    }

    public ArrayList<Card> getFieldCardsForMonsters() {
        return fieldCardsForMonsters;
    }

    public ArrayList<Card> getFieldCardsForSpellTraps() {
        return fieldCardsForSpellTraps;
    }

    public ArrayList<Card> getDeck() {
        return deck;
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public Card getCardFromSpellField(int number) {
        if (fieldCardsForSpellTraps.get(number) != null) return fieldCardsForSpellTraps.get(number);
        return null;
    }

    public Card getCardFromHand(int number) {
        if (hand.size() < number) return null;
        else if (hand.get(number) != null) return hand.get(number);
        return null;
    }


}
