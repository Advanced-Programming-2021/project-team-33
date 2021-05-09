package Controller;

import Model.*;
import View.GameMenu;
import View.MainMenu;

import java.util.ArrayList;
import java.util.Collections;

public class GameController {

    public static Card selectedCard = null;
    public static boolean isOpponentCardSelected = false;


    public static int selectCard(String cardPosition, int number, String opponent) {
        Player player = Player.currentPlayer;
        if (!opponent.equals("")) player = Player.opponent;
        if (selectedCard != null) deSelectCard();
        if (cardPosition.equals("monster") && player.getBoard().getCardFromMonsterField(number) != null) {
            player.getBoard().getCardFromMonsterField(number).setSelected(true);
            selectedCard = player.getBoard().getCardFromMonsterField(number);
            return 1;
        } else if (cardPosition.equals("monster")) return 0;
        else if (cardPosition.equals("spell") && player.getBoard().getCardFromSpellField(number) != null) {
            player.getBoard().getCardFromSpellField(number).setSelected(true);
            selectedCard = player.getBoard().getCardFromSpellField(number);
            return 1;
        } else if (cardPosition.equals("spell")) return 0;
        else if (cardPosition.equals("hand") && player.getBoard().getCardFromHand(number) != null) {
            player.getBoard().getCardFromHand(number).setSelected(true);
            selectedCard = player.getBoard().getCardFromHand(number);
            return 1;
        } else if (cardPosition.equals("hand")) return 0;
        return -1;
    }

    public static void selectCardFromGraveyard(int index) {
        Player.currentPlayer.getBoard().getGraveyard().get(index).setSelected(true);
        selectedCard = Player.currentPlayer.getBoard().getGraveyard().get(index);
    }


    public static void setIsOpponentCardSelected(boolean isOpponentCardSelected) {
        GameController.isOpponentCardSelected = isOpponentCardSelected;
    }

    public static void deSelectCard() {
        setIsOpponentCardSelected(false);
        selectedCard.setSelected(false);
        selectedCard = null;
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


    public static boolean isDeckActive(String user) {
        return Player.getUserByUsername(user).getActiveDeck() != null;
    }

    public static boolean isDeckValid(String user) {
        return true;
    }

    public static void initiateGame(String firstPlayer, String secondPlayer, int round) {
        RoundController.otherPlayer = Player.getUserByUsername(secondPlayer);
        RoundController.setRound(round);
        prepareGame(firstPlayer, secondPlayer);
    }

    public static void setNextGame(String firstPlayer, String secondPlayer) {
        //changeDeck
        prepareGame(firstPlayer, secondPlayer);
    }

    private static void prepareGame(String firstPlayer, String secondPlayer) {
        RoundController.setWhoPlayFirst(firstPlayer, secondPlayer);
        Board board1 = new Board(Player.currentPlayer);
        Player.currentPlayer.setBoard(board1);
        Board board2 = new Board(Player.opponent);
        Player.opponent.setBoard(board2);
        Player.currentPlayer.resetLifePoint();
        Player.opponent.resetLifePoint();
        shuffleDeck(Player.currentPlayer);
        shuffleDeck(Player.opponent);
        for (int i = 0; i < 5; i++) {
            drawCard(Player.currentPlayer);
            drawCard(Player.opponent);
        }
        System.out.println(Player.currentPlayer.getBoard().getDeck().size());
        RoundController.drawPhase();
    }


    public static Card drawCard(Player player) {
        if (player.getBoard().getHand().size() < 6) {
            Card card = player.getBoard().getDeck().get(0);
            card.setCardStatus(CardStatus.HAND);
            player.getBoard().getHand().add(player.getBoard().getDeck().get(0));
            player.getBoard().getDeck().remove(card);
            return card;
        }
        return null;
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

    public static void printGraveyardCards() {
        for (int i = 0; i < Player.currentPlayer.getBoard().getGraveyard().size(); i++) {
            System.out.println(Player.currentPlayer.getBoard().getGraveyard().get(i).getCardName() + ": " +
                    Player.currentPlayer.getBoard().getGraveyard().get(i).getDescription());
        }
    }

    public static boolean isMonsterFieldFull() {
        int check = 0;
        for (int i = 0; i < 5; i++) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null) check++;
        }
        return check == 5;
    }

    public static boolean isSpellTrapFieldFull() {
        int check = 0;
        for (int i = 0; i < 5; i++) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null) check++;
        }
        return check == 5;
    }

    public static void summonMonster(int firstTribute, int secondTribute) {
        if (firstTribute != 0 && secondTribute == 0) {
            Card tributeCard = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(firstTribute);
            Player.currentPlayer.getBoard().getGraveyard().add(tributeCard);
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().remove(firstTribute);
        } else if (secondTribute != 0) {
            Card tributeCard1 = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(firstTribute);
            Card tributeCard2 = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(secondTribute);
            Player.currentPlayer.getBoard().getGraveyard().add(tributeCard1);
            Player.currentPlayer.getBoard().getGraveyard().add(tributeCard2);
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().remove(firstTribute);
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().remove(secondTribute);
        }
        Player.currentPlayer.getBoard().getHand().remove(selectedCard);
        selectedCard.setCardStatus(CardStatus.ATTACK);
        putMonsterOnField();
        callEffects();
        deSelectCard();
        RoundController.isSummoned = true;
    }

    public static void callEffects() {
        if (selectedCard.getCardTypes().contains(CardType.EFFECT)) {
            for (Effect effect : selectedCard.getEffects()) {
                effect.enableEffect(null);
            }
        }
    }

    public static void setMonster() {
        Player.currentPlayer.getBoard().getHand().remove(selectedCard);
        selectedCard.setCardStatus(CardStatus.SET);
        selectedCard.setSummoned(true);
        putMonsterOnField();
        deSelectCard();
        RoundController.isSummoned = true;
    }

    public static void setSpell() {
        Player.currentPlayer.getBoard().getHand().remove(selectedCard);
        selectedCard.setCardStatus(CardStatus.SET);
        selectedCard.setSummoned(true);
        putSpellTrapOnField();
        deSelectCard();
        RoundController.isSummoned = true;
    }

    public static void putSpellTrapOnField() {
        if (Player.thePlayer.getBoard().getFieldCardsForSpellTraps().get(2) == null)
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(2, selectedCard);
        else if (Player.thePlayer.getBoard().getFieldCardsForSpellTraps().get(3) == null)
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(3, selectedCard);
        else if (Player.thePlayer.getBoard().getFieldCardsForSpellTraps().get(1) == null)
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(1, selectedCard);
        else if (Player.thePlayer.getBoard().getFieldCardsForSpellTraps().get(4) == null)
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(4, selectedCard);
        else
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(0, selectedCard);
    }

    public static void putMonsterOnField() {
        if (Player.thePlayer.getBoard().getFieldCardsForMonsters().get(2) == null)
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(2, selectedCard);
        else if (Player.thePlayer.getBoard().getFieldCardsForMonsters().get(3) == null)
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(3, selectedCard);
        else if (Player.thePlayer.getBoard().getFieldCardsForMonsters().get(1) == null)
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(1, selectedCard);
        else if (Player.thePlayer.getBoard().getFieldCardsForMonsters().get(4) == null)
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(4, selectedCard);
        else
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(0, selectedCard);
    }

    public static void changeCardPosition(String cardStatus) {
        if (cardStatus.equals("attack")) selectedCard.setCardStatus(CardStatus.ATTACK);
        else selectedCard.setCardStatus(CardStatus.DEFENCE);
        selectedCard.setChanged(true);
        deSelectCard();
    }

    public static void flipSummon() {
        selectedCard.setCardStatus(CardStatus.ATTACK);
        callEffects();
        selectedCard.setSummoned(true);
        deSelectCard();
    }

    public static void setAllCardsUnchanged() {
        for (int i = 0; i < 5; i++) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null)
                Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i).setChanged(false);
        }
    }

    public static void setAllCardsUnAttacked() {
        for (int i = 0; i < 5; i++) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null)
                Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i).setAttacked(false);
        }
    }

    public static void setAllCardUnSummoned() {
        for (int i = 0; i < 5; i++) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null)
                Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i).setSummoned(false);
        }
    }

    public static void attackMonster(int enemyMonsterIndex) {
        selectedCard.setAttacked(true);
        GameMenu gameMenu = new GameMenu();
        Card enemyCard = Player.opponent.getBoard().getFieldCardsForMonsters().get(enemyMonsterIndex);
        if (enemyCard.getCardStatus().equals(CardStatus.ATTACK)) {
            if (enemyCard.getAttack() < selectedCard.getAttack()) {
                if (checkMonsterEffects(enemyCard)) return;
                putMonsterOnGraveYard(enemyCard, Player.opponent);
                int damage = selectedCard.getAttack() - enemyCard.getAttack();
                Player.opponent.increaseLifePoint(-1 * damage);
                gameMenu.printMonsterAttacks(1, damage, enemyMonsterIndex);
            } else if (enemyCard.getAttack() == selectedCard.getAttack()) {
                if (checkMonsterEffects(enemyCard)) return;
                putMonsterOnGraveYard(enemyCard, Player.opponent);
                putMonsterOnGraveYard(selectedCard, Player.currentPlayer);
                gameMenu.printMonsterAttacks(2, 0, enemyMonsterIndex);
            } else if (enemyCard.getAttack() > selectedCard.getAttack()) {
                if (checkMonsterEffects(enemyCard)) return;
                putMonsterOnGraveYard(selectedCard, Player.currentPlayer);
                int damage = enemyCard.getAttack() - selectedCard.getAttack();
                Player.currentPlayer.increaseLifePoint(-1 * damage);
                gameMenu.printMonsterAttacks(3, damage, enemyMonsterIndex);

            }
        } else {
            if (enemyCard.getDefence() < selectedCard.getAttack()) {
                if (checkMonsterEffects(enemyCard)) return;
                putMonsterOnGraveYard(enemyCard, Player.opponent);
                if (enemyCard.getCardStatus().equals(CardStatus.DEFENCE))
                    gameMenu.printMonsterAttacks(4, 0, enemyMonsterIndex);
                else gameMenu.printMonsterAttacks(7, 0, enemyMonsterIndex);
            } else if (enemyCard.getDefence() == selectedCard.getAttack()) {
                if (checkMonsterEffects(enemyCard)) return;
                if (enemyCard.getCardStatus().equals(CardStatus.DEFENCE))
                    gameMenu.printMonsterAttacks(5, 0, enemyMonsterIndex);
                else gameMenu.printMonsterAttacks(8, 0, enemyMonsterIndex);
            } else if (enemyCard.getDefence() > selectedCard.getAttack()) {
                if (checkMonsterEffects(enemyCard)) return;
                int damage = enemyCard.getDefence() - selectedCard.getAttack();
                Player.currentPlayer.increaseLifePoint(-1 * damage);
                if (enemyCard.getCardStatus().equals(CardStatus.DEFENCE))
                    gameMenu.printMonsterAttacks(6, damage, enemyMonsterIndex);
                else gameMenu.printMonsterAttacks(9, damage, enemyMonsterIndex);
            }
        }
        deSelectCard();
    }

    public static void attackDirect() {
        Player.opponent.increaseLifePoint(-1 * selectedCard.getAttack());
    }

    public static boolean canDestroyMonster(Card enemyCard) {
        if (enemyCard.getCardStatus().equals(CardStatus.ATTACK)) {
            if (enemyCard.getAttack() < selectedCard.getAttack()) {
                return true;
            } else if (enemyCard.getAttack() == selectedCard.getAttack()) {
                return true;
            } else if (enemyCard.getAttack() > selectedCard.getAttack()) {
                return false;
            } else return false;
        } else {
            if (enemyCard.getDefence() < selectedCard.getAttack()) {
                return true;
            } else if (enemyCard.getDefence() == selectedCard.getAttack()) {
                return false;
            } else if (enemyCard.getDefence() > selectedCard.getAttack()) {
                return false;
            } else return false;
        }
    }

    public static boolean checkMonsterEffects(Card enemyCard) {
        if (enemyCard.getCardTypes().contains(CardType.EFFECT)) {
            switch (enemyCard.getCardName()) {
                case "Command Knight":
                    for (Card fieldCardsForMonster : Player.opponent.getBoard().getFieldCardsForMonsters()) {
                        if (fieldCardsForMonster != null &&
                                !fieldCardsForMonster.getCardName().equals("Command Knight"))
                            return true;
                    }
                    return false;
                // not complete
                case "Yomi Ship":
                    if(canDestroyMonster(enemyCard)) {
                        putMonsterOnGraveYard(selectedCard, Player.currentPlayer);
                        return true;
                    }
                    return false;
                default:
                    return false;
            }
        } else {
            return false;
        }
    }

    public static void putMonsterOnGraveYard(Card card, Player player) {
        player.getBoard().getGraveyard().add(card);
        player.getBoard().getFieldCardsForMonsters().remove(card);
    }

    public static boolean isEnemyMonsterFieldEmpty() {
        for (int i = 0; i < 5; i++) {
            if (Player.opponent.getBoard().getFieldCardsForMonsters().get(i) != null) return false;
        }
        return true;
    }

    public static void checkOpponentSpellTraps() {
        ArrayList<Card> cardList = Player.opponent.getBoard().getFieldCardsForSpellTraps();
        boolean isChecked = false;
        if (cardList.get(0) != null) {
            for (int i = 0; i < 5; i++) {
                //other things could happen in this if too
                if (cardList.get(i).getCardTypes().equals(CardType.QUICKPLAY)) {
                    isChecked = true;
                    break;
                }
            }
        }
        if (isChecked) {
            GameMenu gameMenu = new GameMenu();
            gameMenu.printMiddleChange();
            RoundController.changeTurn();
            showBoard();
            if (gameMenu.changePhaseInMiddle().equals("no")) {
                gameMenu.printMiddleChange();
                RoundController.changeTurn();
                showBoard();
            } else Player.currentPlayer.setInOpponentPhase(true);
        }
    }

    public static void getBackFromMiddleChange() {
        GameMenu gameMenu = new GameMenu();
        gameMenu.printMiddleChange();
        RoundController.changeTurn();
    }

    public static void setWinner(int lifePoint, Player winner, Player looser) {
        int score;
        if (lifePoint != -1) {
            score = RoundController.rounds * 1000;
            winner.increaseScore(score);
            winner.increaseMoney(score + lifePoint * RoundController.rounds);
        } else {
            score = RoundController.rounds * 1000;
            winner.increaseMoney(score);
        }
        looser.increaseMoney(RoundController.rounds * 100);
        GameMenu gameMenu = new GameMenu();
        gameMenu.informEndOfGame(winner, score);
        MainMenu.menu = "main";
    }


}
