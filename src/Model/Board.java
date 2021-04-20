package Model;

import java.util.ArrayList;

public class Board {
    Player player;
    public ArrayList<Card> fieldCardsForMonsters = new ArrayList<>();
    public ArrayList<Card> fieldCardsForSpellTraps = new ArrayList<>();
    public ArrayList<Card> graveyard = new ArrayList<>();
    ArrayList<Card> deck = new ArrayList<>();
    public ArrayList<Card> hand = new ArrayList<>();

    public Board(Player player){
        deck = player.activeDeck.mainDeck;
    }

    public Player getPlayer(){
        return player;
    }


}
