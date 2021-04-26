package Model;

import java.util.ArrayList;

public class Card {
    static int id = 0;
    static ArrayList<Card> cards = new ArrayList<>();
    ArrayList<String> functions = new ArrayList<>();
    boolean isSelected = false;
    String cardName, description;
    int price;
    CardType cardType;
    CardCategory cardCategory;
    int cardId;
    CardStatus cardStatus;
    int limit;

    public Card() {
    }

    public Card(String cardName, String description, int price, CardType cardType,
                CardCategory cardCategory, int limit) {
        this.cardName = cardName;
        this.description = description;
        this.price = price;
        this.cardType = cardType;
        this.cardCategory = cardCategory;
        this.cardId = ++id;
        this.limit = limit;
        cards.add(this);
    }

    public String getCardName(){
        return cardName;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
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

    public CardType getCardType() {
        return cardType;
    }

    public String getDescription() {
        return description;
    }

    public void showCard(){
    }

    public Card getCardByName(String name){
        for (Card card : cards) {
            if (card.cardName.equals(name)) return card;
        }
        return null;
    }
}
