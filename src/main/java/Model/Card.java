package Model;

import java.util.ArrayList;

public class Card {
    static int id = 0;
    static ArrayList<Card> cards = new ArrayList<>();
    ArrayList<Effect> effects = new ArrayList<>();
    boolean isSelected = false, isChanged = false, isSummoned = false,
            isAttacked = false, isActivated = false, destroyed = false,
            isBuffed = false;

    String cardName, description;
    ArrayList<CardType> cardTypes = new ArrayList<>();
    CardCategory cardCategory;
    int cardId, limit, price, level;
    CardStatus cardStatus;
    int attack, defence;

    public Card() {

    }

    public Card(String cardName, String description, int price, ArrayList<CardType> cardTypes,
                CardCategory cardCategory, int limit, int level, ArrayList<Effect> effects,
                int attack, int defence) {
        this.cardName = cardName;
        this.description = description;
        this.price = price;
        this.cardStatus = CardStatus.OUT;
        this.cardTypes = cardTypes;
        this.cardCategory = cardCategory;
        this.cardId = ++id;
        this.limit = limit;
        this.level = level;
        this.effects = effects;
        this.attack = attack;
        this.defence = defence;
        cards.add(this);
    }

    public int getLimit() {
        return limit;
    }

    public static ArrayList<Card> getCards() {
        return cards;
    }

    public int getDefence() {
        return defence;
    }

    public boolean isActivated() {
        return isActivated;
    }

    public void setActivated(boolean activated) {
        isActivated = activated;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public boolean isBuffed() {
        return isBuffed;
    }

    public void setBuffed(boolean buffed) {
        isBuffed = buffed;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public String getCardName() {
        return cardName;
    }

    public boolean isAttacked() {
        return isAttacked;
    }

    public void setAttacked(boolean attacked) {
        isAttacked = attacked;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isChanged() {
        return isChanged;
    }

    public void setChanged(boolean changed) {
        isChanged = changed;
    }

    public void setSummoned(boolean summoned) {
        isSummoned = summoned;
    }

    public boolean isSummoned() {
        return isSummoned;
    }

    public int getLevel() {
        return level;
    }

    public ArrayList<Effect> getEffects() {
        return effects;
    }

    public void setDestroyed(boolean destroyed) {
        this.destroyed = destroyed;
    }

    public boolean isDestroyed() {
        return destroyed;
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

    public ArrayList<CardType> getCardTypes() {
        return this.cardTypes;
    }

    public String getDescription() {
        return description;
    }

    public CardCategory getCardCategory() {
        return cardCategory;
    }

    public int getAttack() {
        return this.attack;
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
