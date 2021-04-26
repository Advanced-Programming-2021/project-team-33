package Model;

import java.util.ArrayList;

public class Board {
    Player player;
    static ArrayList<Card> fieldCardsForMonsters = new ArrayList<>();
    static ArrayList<Card> fieldCardsForSpellTraps = new ArrayList<>();
    static ArrayList<Card> graveyard = new ArrayList<>();
    ArrayList<Card> deck = new ArrayList<>();
    static ArrayList<Card> hand = new ArrayList<>();

    public Board(Player player) {
        deck = player.activeDeck.mainDeck;
    }

    public Player getPlayer() {
        return player;
    }

    public static Card getCardFromMonsterField(int number) {
        if (fieldCardsForMonsters.get(number) != null) return fieldCardsForMonsters.get(number);
        return null;
    }

    public static Card getCardFromSpellField(int number) {
        if (fieldCardsForSpellTraps.get(number) != null) return fieldCardsForSpellTraps.get(number);
        return null;
    }

    public static Card getCardFromHand(int number) {
        if (hand.size() < number) return null;
        else if (hand.get(number) != null) return hand.get(number);
        return null;
    }


}
