package Model;

import java.util.ArrayList;

public class Card {
    static int id = 0;
    static ArrayList<Card> cards = new ArrayList<>();
    ArrayList<Effect> effects = new ArrayList<>();
    boolean isSelected = false;
    String cardName, description;
    ArrayList<CardType> cardTypes = new ArrayList<>();
    CardPosition cardPosition;
    CardCategory cardCategory;
    int cardId, limit, price, level;
    CardStatus cardStatus;
    int attack;
    int deffense;

    public Card() {
    }

    public Card(String cardName, String description, int price, ArrayList<CardType> cardTypes,
                CardCategory cardCategory, int limit, int level, ArrayList<Effect> effects, int attack, int deffense) {
        this.cardName = cardName;
        this.description = description;
        this.price = price;
        this.cardTypes = cardTypes;
        this.cardCategory = cardCategory;
        this.cardId = ++id;
        this.limit = limit;
        this.level = level;
        this.effects = effects;
        this.attack = attack;
        this.deffense = deffense;
        cards.add(this);
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public String getCardName() {
        return cardName;
    }

    public void setCardPosition(CardPosition cardPosition) {
        this.cardPosition = cardPosition;
    }

    public CardPosition getCardPosition() {
        return cardPosition;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public int getLevel() {
        return level;
    }

    public CardStatus getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(CardStatus cardStatus) {
        this.cardStatus = cardStatus;
    }

    public int getPrice() {
        return price;
    }

    public ArrayList<CardType> getCardType() {
        return this.cardTypes;
    }

    public String getDescription() {
        return description;
    }

    public CardCategory getCardCategory() {
        return cardCategory;
    }

    public int getAttack() {return this.attack;}

    public int getDeffense() {return this.deffense;}

    public void showCard() {
    }

    public static Card getCardByName(String name) {
        for (Card card : cards) {
            if (card.cardName.equals(name)) return card;
        }
        return null;
    }
}
