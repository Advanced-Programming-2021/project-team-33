package Model;

import java.util.ArrayList;

public class Card {
    static int id = 0;
    static ArrayList<Card> cards = new ArrayList<>();
    ArrayList<String> functions = new ArrayList<>();
    boolean isSelected = false;
    String cardName, description;
    CardType cardType;
    CardCategory cardCategory;
    int cardId, limit, price, level;
    CardStatus cardStatus;

    public Card() {
    }

    public Card(String cardName, String description, int price, CardType cardType,
                CardCategory cardCategory, int limit, int level) {
        this.cardName = cardName;
        this.description = description;
        this.price = price;
        this.cardType = cardType;
        this.cardCategory = cardCategory;
        this.cardId = ++id;
        this.limit = limit;
        this.level = level;
        cards.add(this);
    }

    public String getCardName() {
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

    public CardCategory getCardCategory() {
        return cardCategory;
    }

    public void showCard() {
    }

    public static Card getCardByName(String name) {
        for (Card card : cards) {
            if (card.cardName.equals(name)) return card;
        }
        return null;
    }
}
