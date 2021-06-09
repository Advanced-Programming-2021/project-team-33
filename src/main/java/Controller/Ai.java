package Controller;

import Model.*;
import View.ChangeCardsMenu;
import View.Communicate;

import static Model.Player.theAi;

public class Ai {


    static Deck deck = new Deck("aiDeck");

    public static void initiateGameWithAi(String firstPlayer, int round) {
        theAi = new Player("Ai","Ai","Ai");
        RoundController.otherPlayer = theAi;
        theAi.getPlayers().remove(theAi);
        setActiveDeckForAi();
        RoundController.setRound(round);
        prepareGame();
    }

    private static void setActiveDeckForAi() {
        theAi.addToDeckList(deck);
        deck.setDeckActive(true);
        deck.addToMainDeck(Card.getCardByName("Battle Ox"));
        deck.addToMainDeck(Card.getCardByName("Pot of Greed"));
        deck.addToMainDeck(Card.getCardByName("Call of the Haunted"));
        deck.addToMainDeck(Card.getCardByName("Axe Raider"));
        deck.addToMainDeck(Card.getCardByName("Silver Fang"));
        deck.addToMainDeck(Card.getCardByName("Horn Imp"));
        deck.addToMainDeck(Card.getCardByName("Curtain of Dark Ones"));
        deck.addToMainDeck(Card.getCardByName("Fireyarou"));
    }


    public static void setNextGame() {
        ChangeCardsMenu changeCardsMenu = new ChangeCardsMenu();
        changeCardsMenu.changeDeck(Player.thePlayer.getUsername());
        prepareGame();
    }

    private static void prepareGame() {
        Player.currentPlayer = Player.thePlayer;
        Player.opponent = theAi;
        Board board1 = new Board(Player.currentPlayer);
        Player.currentPlayer.setBoard(board1);
        Board board2 = new Board(Player.opponent);
        Player.opponent.setBoard(board2);
        Player.currentPlayer.resetLifePoint();
        Player.opponent.resetLifePoint();
        GameController.shuffleDeck(Player.currentPlayer);
        GameController.shuffleDeck(Player.opponent);
        for (int i = 0; i < 5; i++) {
            GameController.drawCard(Player.currentPlayer);
            GameController.drawCard(Player.opponent);
        }
        RoundController.drawPhase();
    }

    public static void aiAction() {
        RoundController.drawPhase();
        if (isTrapPossible()) doTrap();
        if (isSpellPossible()) doSpell();
        if (isMonsterPossible()) summonMonster();
        RoundController.battlePhase();
        if (isDirectAttackPossible()) directAttack();
        else if (isAttackPossible()) attack();
        RoundController.mainPhase2();
        RoundController.endPhase();
    }

    private static boolean isTrapPossible() {
        for (int i = 0; i < Player.currentPlayer.getBoard().getHand().size(); i++) {
            if (Player.currentPlayer.getBoard().getHand().get(i).getCardCategory().equals(CardCategory.TRAP)) {
                GameController.selectedCard = Player.currentPlayer.getBoard().getHand().get(i);
                return true;
            }

        }
        return false;
    }

    private static boolean isSpellPossible() {
        for (int i = 0; i < Player.currentPlayer.getBoard().getHand().size(); i++) {
            if (Player.currentPlayer.getBoard().getHand().get(i).getCardCategory().equals(CardCategory.SPELL)) {
                GameController.selectedCard = Player.currentPlayer.getBoard().getHand().get(i);
                return true;
            }

        }
        return false;
    }

    private static boolean isMonsterPossible() {
        for (int i = 0; i < Player.currentPlayer.getBoard().getHand().size(); i++) {
            if (Player.currentPlayer.getBoard().getHand().get(i).getCardCategory().equals(CardCategory.MONSTER)) {
                GameController.selectedCard = Player.currentPlayer.getBoard().getHand().get(i);
                return true;
            }

        }
        return false;
    }

    private static boolean isAttackPossible() {
        for (int i = 0; i < 5; i++) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null)
                return true;
        }
        return false;
    }


    private static boolean isDirectAttackPossible() {
        boolean isChecked = false;
        for (int i = 0; i < 5; i++) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null) {
                isChecked = true;
                GameController.selectedCard = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i);
            }
            if (Player.opponent.getBoard().getFieldCardsForMonsters().get(i) != null) return false;
        }
        return isChecked;
    }

    private static void doTrap() {
        if (Player.currentPlayer.getBoard().getGraveyard().size() != 0) {
            GameController.activeSpell();
        }
    }

    private static void doSpell() {
        if (Player.currentPlayer.getBoard().getDeck().size() > 1) {
            GameController.activeSpell();
        }
    }

    private static void summonMonster() {
        GameController.selectedCard.setCardStatus(CardStatus.ATTACK);
        Player.currentPlayer.getBoard().getHand().remove(GameController.selectedCard);
        GameController.putMonsterOnField();
    }

    private static void attack() {
        for (int i = 0; i < 5; i++) {
            if (Player.opponent.getBoard().getFieldCardsForMonsters().get(i) != null){
                GameController.attackMonster(i);
                break;
            }
        }
    }

    private static void directAttack() {
        GameController.attackDirect();
    }


}
