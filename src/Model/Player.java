package Model;

import View.Phase;

import java.util.ArrayList;

public class Player {
    static ArrayList<Player> players = new ArrayList<>();
    public ArrayList<Deck> listOfDecks = new ArrayList<>();
    public ArrayList<Card> listOfCards = new ArrayList<>();
    public Phase phase;
    public Board board;
    public static Player thePlayer;
    public static Player currentPlayer, opponent;
    boolean isInOpponentPhase;
    String username, password, nickname;
    int money, score, lifePoint = 8000;
    Deck activeDeck;

    public Player() {

    }

    public Player(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        listOfCards.addAll(Card.cards);
        players.add(this);
    }

    public ArrayList<Deck> getListOfDecks() {
        return listOfDecks;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Board getBoard() {
        return board;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }

    public Phase getPhase() {
        return phase;
    }

    public void setInOpponentPhase(boolean inOpponentPhase) {
        isInOpponentPhase = inOpponentPhase;
    }

    public boolean isInOpponentPhase() {
        return isInOpponentPhase;
    }

    public String getUsername() {
        return this.username;
    }

    public String getNickname() {
        return this.nickname;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getScore() {
        return score;
    }

    public int getMoney() {
        return money;
    }

    public void setActiveDeck(Deck activeDeck) {
        this.activeDeck = activeDeck;
    }

    public Deck getActiveDeck() {
        for (Deck listOfDeck : listOfDecks) {
            if (listOfDeck.isDeckActive())
                return listOfDeck;
        }
        return null;
    }

    public void increaseMoney(int money) {
        this.money += money;
    }

    public void increaseScore(int score) {
        this.score += score;
    }

    public void setNickname(String name) {
        this.nickname = name;
    }

    public void setLifePoint(int lifePoint) {
        this.lifePoint = lifePoint;
    }

    public void resetLifePoint() {
        lifePoint = 8000;
    }

    public void increaseOrDecreaseLifePoint(int point) {
        lifePoint += point;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void addToCardList(Card card) {
        listOfCards.add(card);
    }

    public void removeFromCardList(String cardName) {
        for (int i = 0; i < listOfCards.size(); i++) {
            if (listOfCards.get(i).getCardName().equals(cardName))
                listOfCards.remove(i);
        }
    }

    public void increaseLifePoint(int lifePoint){
        this.lifePoint += lifePoint;
    }

    public void addToDeckList(Deck deck) {
        listOfDecks.add(deck);
    }

    public void deleteDeck(String deckName) {
        for (int i = 0; i < listOfDecks.size(); i++) {
            if (listOfDecks.get(i).getDeckName().equals(deckName))
                listOfDecks.remove(i);
        }
    }

    public static void deActiveDecks() {
        for (Deck listOfDeck : thePlayer.listOfDecks) {
            listOfDeck.setDeckActive(false);
        }
    }

    public static Player getUserByUsername(String name) {
        for (Player player : players) {
            if (player.username.equals(name)) return player;
        }
        return null;
    }

    public static Deck getDeckByName(String deckName) {
        for (Deck deck : thePlayer.listOfDecks) {
            if (deck.deckName.equals(deckName)) return deck;
        }
        return null;
    }


    public static Card getCardByName(String name) {
        for (Card card : thePlayer.listOfCards) {
            if (card.cardName.equals(name)) return card;
        }
        return null;
    }

    public static Player getUserByNickname(String name) {
        for (Player player : players) {
            if (player.nickname.equals(name)) return player;
        }
        return null;
    }


}
