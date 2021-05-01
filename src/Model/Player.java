package Model;

import java.util.ArrayList;

public class Player {
    static ArrayList<Player> players = new ArrayList<>();
    public static Player thePlayer;
    public static Player currentPlayer, opponent;
    String username, password, nickname;
    int money, score, lifePoint = 8000;
    ArrayList<Deck> listOfDecks = new ArrayList<>();
    Deck activeDeck;
    ArrayList<Card> listOfCards = new ArrayList<>();
    public Board board;

    public Player(String username, String password, String nickname) {
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        players.add(this);
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
        return activeDeck;
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

    public void resetLifePoint() {
        lifePoint = 8000;
    }

    public void increaseOrDecreaseLifePoint(int point) {
        lifePoint += point;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public void addToDeckList(String deckName){
        listOfDecks.add(Deck.getDeckByName(deckName));
    }

    public static Player getUserByUsername(String name) {
        for (Player player : players) {
            if (player.username.equals(name)) return player;
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
