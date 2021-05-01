package Controller;

import Model.*;

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
            selectedCard = Player.currentPlayer.board.getCardFromMonsterField(number);
            return 1;
        } else if (cardPosition.equals("spell")) return 0;
        else if (cardPosition.equals("hand") && Player.currentPlayer.board.getCardFromHand(number) != null) {
            Player.currentPlayer.board.getCardFromHand(number).setSelected(true);
            selectedCard = Player.currentPlayer.board.getCardFromMonsterField(number);
            return 1;
        } else if (cardPosition.equals("hand")) return 0;
        return -1;
    }

    public static void deSelectCard() {
        selectedCard.setSelected(false);
    }

    public static boolean isDeckActive(String user) {
        return Player.getUserByUsername(user).getActiveDeck() != null;
    }

    public static boolean isDeckValid(String user) {
        return Deck.getDeckValidation(Player.getUserByUsername(user).getActiveDeck());
    }

    public static void showBoard() {
        System.out.println(Player.opponent.getNickname() + ":" + Player.opponent.getLifePoint());
        System.out.print("\t");
        for (int i = 0; i < Player.opponent.board.hand.size(); i++) {
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
        Util.printNCharacter(5, "\t");
        if (Player.opponent.board.fieldZone.size()==0) System.out.println("E");
        else System.out.println("O");
        System.out.println();
        Util.printNCharacter(26, "-");
        Util.printNCharacter(2, "\n");

        //-----------------------------

        if (Player.currentPlayer.board.fieldZone.size()==0) System.out.println("E");
        else System.out.println("O");
        Util.printNCharacter(5, "\t");
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


}
