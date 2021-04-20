package Model;

import java.util.ArrayList;

public class Player {
    static ArrayList<Player> players = new ArrayList<>();
    String username,password,nickname;
    int money,score,lifePoint = 8000;
    ArrayList<Deck> listOfDecks = new ArrayList<>();
    Deck activeDeck;
    ArrayList<Card> listOfCards = new ArrayList<>();

    public Player(String username,String password,String nickname){
        this.username = username;
        this.password = password;
        this.nickname = nickname;
    }

    public String getUsername(){
        return this.username;
    }

    public String getNickname(){
        return this.nickname;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void setNickname(String name){
        this.nickname = name;
    }

    public void resetLifePoint(){
        lifePoint = 8000;
    }

    public void increaseOrDecreaseLifePoint(int point){
        lifePoint += point;
    }

    public int getLifePoint() {
        return lifePoint;
    }

    public static Player getUserByUsername(String name){
        for (Player player : players) {
            if (player.username.equals(name)) return player;
        }
        return null;
    }
}
