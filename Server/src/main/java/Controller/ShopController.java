package Controller;

import Model.Card;
import Model.Player;

import java.util.ArrayList;

public class ShopController {

    public static ArrayList<Card> shopStock = new ArrayList<>();
    public static ArrayList<Card> forbiddenCards = new ArrayList<>();
    public static Player admin;

    public static void initialShopStock() {
        shopStock.addAll(Card.getCards());
        shopStock.addAll(Card.getCards());
        shopStock.addAll(Card.getCards());
        shopStock.addAll(Card.getCards());
        shopStock.addAll(Card.getCards());
    }

    public static String getNumberOfCards(String cardName) {
        int count = 0;
        for (int i = 0; i < shopStock.size(); i++) {
           if(shopStock.get(i).getCardName().equals(cardName)) count++;
        }
        return String.valueOf(count);
    }

    public static void reduceCard(String cardName) {
        for (int i = 0; i < shopStock.size(); i++) {
            if(shopStock.get(i).getCardName().equals(cardName)) {
                shopStock.remove(i);
                break;
            }
        }
    }

    public static String checkAdmin(String username) {
        if(username.equals(Player.getPlayers().get(0).getUsername())) return "1";
        return "";
    }

    public static void addCard(String cardName) {
        shopStock.add(Card.getCardByName(cardName));
    }

    public static void forbidCard(String cardName) {
        forbiddenCards.add(Card.getCardByName(cardName));
    }

    public static void permitCard(String cardName) {
        forbiddenCards.remove(Card.getCardByName(cardName));
    }


    public static String getForbiddenCard(String cardName){
        boolean isChecked= false;
        for (Card forbiddenCard : forbiddenCards) {
            if (forbiddenCard.getCardName().equals(cardName)) {
                isChecked = true;
                break;
            }
        }
        System.out.println(isChecked);
        if(isChecked) return "1";
        return "";
    }


}
