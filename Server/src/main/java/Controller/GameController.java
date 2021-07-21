package Controller;

import Model.*;

import java.util.ArrayList;

public class GameController {

    static ArrayList<String> waitingUsers = new ArrayList<>();
    static ArrayList<String> readyUsers = new ArrayList<>();
    static boolean isSecondPlayerCame = false;
    public static Player player1, player2;
    static int count = 0;
    static int gameId = 0;
    public static Card selectedCard = null;
    static ArrayList<String> monsterList1 = new ArrayList<>();
    static ArrayList<String> monsterList2 = new ArrayList<>();
    static ArrayList<String> monsterPosition1 = new ArrayList<>();
    static ArrayList<String> monsterPosition2 = new ArrayList<>();
    static ArrayList<String> spellList1 = new ArrayList<>();
    static ArrayList<String> spellList2 = new ArrayList<>();
    static ArrayList<String> graveyardList1 = new ArrayList<>();
    static ArrayList<String> graveyardList2 = new ArrayList<>();
    static String phase = "", lp1 = "8000" ,lp2 = "8000";
    static boolean isAnimated = false;

    public static void cancelMultiPlayer(){
        waitingUsers.clear();
    }

    public static String setMultiPlayer(String username) {
        if (waitingUsers.size() == 0 || (!waitingUsers.get(0).equals(username) && !isSecondPlayerCame)) {
            if (waitingUsers.size() == 1) isSecondPlayerCame = true;
            waitingUsers.add(username);
        }

        if (waitingUsers.size() > 1) {
            if (waitingUsers.get(0).equals(username)) {
                count++;
                String s = waitingUsers.get(1);
                clearWaitingUsers();
                s = s + " " + Player.getUserByUsername(s).getNickname() + " " +
                        Player.getUserByUsername(s).getScore() + " " + Player.getUserByUsername(s).getProfileID();
                return s;
            } else if (waitingUsers.get(1).equals(username)) {
                count++;
                String s = waitingUsers.get(0);
                clearWaitingUsers();
                s = s + " " + Player.getUserByUsername(s).getNickname() + " " +
                        Player.getUserByUsername(s).getScore() + " " + Player.getUserByUsername(s).getProfileID();
                return s;
            }
        }

        return "0 0 0 0";
    }

    private static void clearWaitingUsers() {
        if (count == 2) {
            gameId++;
            count = 0;
            player1 = Player.getUserByUsername(waitingUsers.get(0));
            player2 = Player.getUserByUsername(waitingUsers.get(1));
            d1(player1.getUsername());
            d2(player2.getUsername());
            waitingUsers.clear();
            isSecondPlayerCame = false;
        }
    }


    public static String isOpponentReady(String player1, String player2) {
        boolean checkMe = true;
        boolean checkOpponent = false;
        for (String readyUser : readyUsers) {
            if (readyUser.equals(player1)) checkMe = false;
            if (readyUser.equals(player2)) checkOpponent = true;
        }
        if (checkMe) readyUsers.add(player1);
        if (checkOpponent) return "1";
        return "0";
    }

    public static String getPlayer1() {
        Player.currentPlayer = player1;
        Player.opponent = player2;
        return player1.getUsername();
    }

    public static String getPlayer2() {
        Player.currentPlayer = player2;
        Player.opponent = player1;
        return player2.getUsername();
    }

    public static void prepareGame() {
        Board board1 = new Board(Player.currentPlayer);
        Player.currentPlayer.setBoard(board1);
        Board board2 = new Board(Player.opponent);
        Player.opponent.setBoard(board2);
        Player.currentPlayer.resetLifePoint();
        Player.opponent.resetLifePoint();
        for (int i = 0; i < 5; i++) {
            drawCard(Player.currentPlayer);
            drawCard(Player.opponent);
        }
        RoundController.drawPhase();
    }

    public static Card drawCard(Player player) {
        if (player.getBoard().getHand().size() < 6) {
            Card card = player.getBoard().getDeck().get(0);
            if (card == null) {
                System.out.println("here");
                player.setLifePoint(0);
            } else {
                card.setCardStatus(CardStatus.HAND);
                player.getBoard().getHand().add(player.getBoard().getDeck().get(0));
                player.getBoard().getDeck().remove(card);
                return card;
            }
        }
        return null;
    }

    public static String getDeck(String username) {
        ArrayList<String> cardName = new ArrayList<>();
        if (username.equals(player1.getUsername())) {
            cardName.addAll(player1.getActiveDeck().getMainDeck());
            return cardName.toString();
        }
        if (username.equals(player2.getUsername())) {
            cardName.addAll(player2.getActiveDeck().getMainDeck());
            return cardName.toString();
        }
        return "0";
    }

    public static void d1(String username) {
        Deck deck = new Deck("deck1");
        Player.getUserByUsername(username).addToDeckList(deck);
        deck.setDeckActive(true);
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Warrior Dai Grepher");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Monster Reborn");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Battle Warrior");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Dark Blade");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Bitron");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Haniwa");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Wattkid");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Baby Dragon");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Crawling Dragon");
    }

    public static void d2(String username) {
        Deck deck = new Deck("deck2");
        Player.getUserByUsername(username).addToDeckList(deck);
        deck.setDeckActive(true);
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Axe Raider");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Battle Ox");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Wattaildragon");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Horn Imp");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Silver Fang");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Spiral Serpent");
        Player.getUserByUsername(username).getActiveDeck().addToMainDeck("Fireyarou");
    }

    public static void createDeck(String username, String deckName) {
        Deck deck = new Deck(deckName);
        Player.getUserByUsername(username).addToDeckList(deck);
        Player.deActiveDecks();
        deck.setDeckActive(true);
    }

    public static void deleteDeck(String username, String deckName) {
        Player.getUserByUsername(username).deleteDeck(deckName);
    }

    public static void activateDeck(String username, String deckName) {
        Player.deActiveDecks();
        Player.getDeckByName(deckName).setDeckActive(true);
    }

    public static void addCardToDeck(String deckName, String cardName) throws CloneNotSupportedException {
        Player.getDeckByName(deckName).addToMainDeck(cardName);
    }

    public static void addCardSideToDeck(String deckName, String cardName) throws CloneNotSupportedException {
        Player.getDeckByName(deckName).addToSideDeck(cardName);
    }

    public static int getProfileId(String username) {
        return (Player.getUserByUsername(username).getProfileID());
    }


    public static void getMonster(String username, String monsterList) {

        String[] parts = monsterList.split(", ");
        if (username.equals(player1.getUsername())) {
            for (int i = 0; i < 5; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll(".*\\[", "");
                monsterList1.set(i, s);
            }
        } else if (username.equals(player2.getUsername())) {
            for (int i = 0; i < 5; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll(".*\\[", "");
                monsterList2.set(i, s);
            }
        }

    }

    public static void getPosition(String username, String monsterPosition) {
        String[] parts = monsterPosition.split(", ");
        if (username.equals(player1.getUsername())) {
            for (int i = 0; i < 5; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll(".*\\[", "");
                monsterPosition1.set(i, s);
            }
        } else if (username.equals(player2.getUsername())) {
            for (int i = 0; i < 5; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll(".*\\[", "");
                monsterPosition2.set(i, s);
            }
        }
    }

    public static String sendMonster(String username) {
        if (username.equals(player1.getUsername())) return monsterList1.toString();
        if (username.equals(player2.getUsername())) return monsterList2.toString();
        return "0";
    }

    public static String sendSpell(String username) {
        if (username.equals(player1.getUsername())) return spellList1.toString();
        if (username.equals(player2.getUsername())) return spellList2.toString();
        return "0";
    }

    public static String sendPosition(String username) {
        if (username.equals(player1.getUsername())) return monsterPosition1.toString();
        if (username.equals(player2.getUsername())) return monsterPosition2.toString();
        return "0";
    }

    public static void getSpell(String username, String spellList) {
        String[] parts = spellList.split(", ");
        if (username.equals(player1.getUsername())) {
            for (int i = 0; i < 5; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll(".*\\[", "");
                spellList1.set(i, s);
            }
        } else if (username.equals(player2.getUsername())) {
            for (int i = 0; i < 5; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll(".*\\[", "");
                spellList2.set(i, s);
            }
        }
    }

    public static void getPhase(String p) {
        phase = p;
    }

    public static String sendPhase() {
        return phase;
    }

    public static void getLp(String lp,String username) {
        if(username.equals(player1.getUsername())) lp1 = lp;
        if(username.equals(player2.getUsername())) lp2 = lp;

    }
    public static String sendLp(String username) {
        if(username.equals(player1.getUsername())) return lp1 ;
        if(username.equals(player2.getUsername())) return lp2 ;
        return "1";
    }

    public static String sendGraveyard(String username) {
        if (username.equals(player1.getUsername())) return graveyardList1.toString();
        if (username.equals(player2.getUsername())) return graveyardList2.toString();
        return "0";
    }

    public static void getGraveyard(String username, String graveyardList) {
        String[] parts = graveyardList.split(", ");
        if (username.equals(player1.getUsername())) {
            graveyardList1.clear();
            for (int i = 0; i < parts.length; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll(".*\\[", "");
                graveyardList1.add(s);
            }
        } else if (username.equals(player2.getUsername())) {
            graveyardList2.clear();
            for (int i = 0; i < parts.length; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll(".*\\[", "");
                graveyardList2.add(s);
            }
        }
        System.out.println(graveyardList);
        System.out.println(graveyardList1);
        System.out.println(graveyardList2);
    }

    public static void getAnimation(){
        isAnimated = true;
    }

    public static String sendAnimation(){
        if(isAnimated) {
            isAnimated = false;
            return "1";
        }
        return "0";
    }

    public static void initialLists() {
        CardController.initialCards();
        ShopController.initialShopStock();
        for (int i = 0; i < 5; i++) {
            monsterList1.add(null);
            monsterList2.add(null);
            monsterPosition1.add(null);
            monsterPosition2.add(null);
            spellList1.add(null);
            spellList2.add(null);
        }

    }

    public static void resetList() {
        for (int i = 0; i < 5; i++) {
            monsterList1.set(i, null);
            monsterList2.set(i, null);
            monsterPosition1.set(i, null);
            monsterPosition2.set(i, null);
        }

    }

}