package Controller;

import Model.*;
import View.GameMenu;

import java.util.Collections;
import java.util.Random;

public class GameController {

    public static Card selectedCard = null;

    public static int selectCard(String cardPosition, int number, String opponent) {
        if (cardPosition.equals("monster") && Player.currentPlayer.board.getCardFromMonsterField(number) != null) {
            Player.currentPlayer.board.getCardFromMonsterField(number).setSelected(true);
            selectedCard = Player.currentPlayer.board.getCardFromMonsterField(number);
            return 1;
        } else if (cardPosition.equals("monster")) return 0;
        else if (cardPosition.equals("spell") && Player.currentPlayer.board.getCardFromSpellField(number) != null) {
            Player.currentPlayer.board.getCardFromSpellField(number).setSelected(true);
            selectedCard = Player.currentPlayer.board.getCardFromSpellField(number);
            return 1;
        } else if (cardPosition.equals("spell")) return 0;
        else if (cardPosition.equals("hand") && Player.currentPlayer.board.getCardFromHand(number) != null) {
            Player.currentPlayer.board.getCardFromHand(number).setSelected(true);
            selectedCard = Player.currentPlayer.board.getCardFromHand(number);
            return 1;
        } else if (cardPosition.equals("hand")) return 0;
        return -1;
    }

    public static void createDeck(String deckName) {
        Deck deck = new Deck(deckName);
        Player.thePlayer.addToDeckList(deck);
        Player.deActiveDecks();
        deck.setDeckActive(true);
    }

    public static void deleteDeck(String deckName) {
        Player.thePlayer.deleteDeck(deckName);
    }

    public static void activateDeck(String deckName) {
        Player.deActiveDecks();
        Player.getDeckByName(deckName).setDeckActive(true);
    }

    public static void addCardToDeck(String deckName, String cardName, boolean isSide) {
        if (!isSide) {
            Player.getDeckByName(deckName).addToMainDeck(Card.getCardByName(cardName));
        } else {
            Player.getDeckByName(deckName).addToSideDeck(Card.getCardByName(cardName));
        }
        Player.thePlayer.removeFromCardList(cardName);
    }

    public static void removeCardFromDeck(String deckName, String cardName, boolean isSide) {
        if (!isSide) {
            Player.getDeckByName(deckName).removeFromMainDeck(Card.getCardByName(cardName));
        } else {
            Player.getDeckByName(deckName).removeFromSideDeck(Card.getCardByName(cardName));
        }
        Player.thePlayer.addToCardList(Card.getCardByName(cardName));
    }


    public static void deSelectCard() {
        selectedCard.setSelected(false);
        selectedCard = null;
    }

    public static boolean isDeckActive(String user) {
        return Player.getUserByUsername(user).getActiveDeck() != null;
    }

    public static boolean isDeckValid(String user) {
        return true;
    }

    public static void initiateGame(String firstPlayer, String secondPlayer) {
        RoundController.setWhoPlayFirst(firstPlayer, secondPlayer);
        Board board1 = new Board(Player.currentPlayer);
        Player.currentPlayer.setBoard(board1);
        Board board2 = new Board(Player.opponent);
        Player.opponent.setBoard(board2);
        shuffleDeck(Player.currentPlayer);
        shuffleDeck(Player.opponent);
        for (int i = 0; i < 5; i++) {
            drawCard(Player.currentPlayer);
            drawCard(Player.opponent);
        }
        RoundController.drawPhase();
    }

    public static Card drawCard(Player player) {
        Card card = player.getBoard().getDeck().get(0);
        card.setCardPosition(CardPosition.HAND);
        player.getBoard().getHand().add(player.getBoard().getDeck().get(0));
        player.getBoard().getDeck().remove(card);
        return card;
    }

    public static void shuffleDeck(Player player) {
        Collections.shuffle(player.getBoard().getDeck());
    }

    public static void showBoard() {
        System.out.println(Player.opponent.getNickname() + ":" + Player.opponent.getLifePoint());
        System.out.print("\t");
        for (int i = 0; i < Player.opponent.getBoard().getHand().size(); i++) {
            System.out.print("c\t");
        }
        System.out.println();
        System.out.println(Player.opponent.board.deck.size());
        System.out.print("\t");
        for (Card card : Player.opponent.board.fieldCardsForSpellTraps) {
            if (card == null) {
                System.out.print("E\t");
            } else if (card.getCardStatus() == CardStatus.BACK) {
                System.out.print("H\t");
            } else if (card.getCardStatus() == CardStatus.SET) {
                System.out.print("O\t");
            }
        }
        System.out.println();
        System.out.print("\t");
        for (Card card : Player.opponent.board.fieldCardsForMonsters) {
            if (card == null) {
                System.out.print("E\t");
            } else if (card.getCardStatus() == CardStatus.DEFENCE) {
                System.out.print("DO\t");
            } else if (card.getCardStatus() == CardStatus.SET) {
                System.out.print("DH\t");
            } else if (card.getCardStatus() == CardStatus.ATTACK) {
                System.out.print("OO\t");
            }
        }
        System.out.println();
        System.out.print(Player.opponent.board.graveyard.size());
        Util.printNCharacter(6, "\t");
        if (Player.opponent.board.fieldZone.size() == 0) System.out.println("E");
        else System.out.println("O");
        System.out.println();
        Util.printNCharacter(26, "-");
        Util.printNCharacter(2, "\n");

        //-----------------------------

        if (Player.currentPlayer.board.fieldZone.size() == 0) System.out.print("E");
        else System.out.print("O");
        Util.printNCharacter(6, "\t");
        System.out.println(Player.currentPlayer.board.graveyard.size());
        System.out.print("\t");
        for (Card card : Player.currentPlayer.board.fieldCardsForMonsters) {
            if (card == null) {
                System.out.print("E\t");
            } else if (card.getCardStatus() == CardStatus.DEFENCE) {
                System.out.print("DO\t");
            } else if (card.getCardStatus() == CardStatus.SET) {
                System.out.print("DH\t");
            } else if (card.getCardStatus() == CardStatus.ATTACK) {
                System.out.print("OO\t");
            }
        }
        System.out.println();
        System.out.print("\t");
        for (Card card : Player.currentPlayer.board.fieldCardsForSpellTraps) {
            if (card == null) {
                System.out.print("E\t");
            } else if (card.getCardStatus() == CardStatus.BACK) {
                System.out.print("H\t");
            } else if (card.getCardStatus() == CardStatus.SET) {
                System.out.print("O\t");
            }
        }
        System.out.println();
        Util.printNCharacter(6, "\t");
        System.out.println(Player.currentPlayer.board.deck.size());
        for (int i = 0; i < Player.currentPlayer.board.hand.size(); i++) {
            System.out.print("c\t");
        }
        System.out.println();
        System.out.println(Player.currentPlayer.getNickname() + ":" + Player.currentPlayer.getLifePoint());
    }

    public static void summonMonster() {
        Player.currentPlayer.getBoard().getFieldCardsForMonsters().add(selectedCard);
        selectedCard.setCardPosition(CardPosition.FRONT);
    }


}
