package Model;

import java.util.ArrayList;

public class Card {
    String cardName, description;
    int price;
    CardType cardType;
    CardStatus cardStatus = CardStatus.OUT;
    ArrayList<Card> cards = new ArrayList<>();

    public Card() {
    }

    public Card(String cardName, String description, int price, CardType cardType) {
        this.cardName = cardName;
        this.description = description;
        this.price = price;
        this.cardType = cardType;
        cards.add(this);
    }

    public String getCardName(){
        return cardName;
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
