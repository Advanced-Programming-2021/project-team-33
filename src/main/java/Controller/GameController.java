package Controller;

import Model.*;
import View.*;

import java.util.ArrayList;
import java.util.Collections;


public class GameController {

    public static Card selectedCard = null;
    public static Card lastSelectedCard = null;
    public static boolean isOpponentCardSelected = false;
    public static boolean isAttackTrap = false;
    public static boolean isSpellTrap = false;
    public static boolean isSummonTrap = false;
    public static boolean isCyberseActive = false;
    public static boolean isTrapFrozen = false;
    public static boolean isHeartActive = false;
    public static boolean isThreeLightActive = false;
    public static boolean is1500Active = false;
    public static Player getLightPlayer = null;
    public static int reserve = 0;


    public static int selectCard(String cardPosition, int number, String opponent) {
        Player player = Player.currentPlayer;
        if (player.isInOpponentPhase() && isAttackTrap) return selectAttackTrap(cardPosition, number);
        if (player.isInOpponentPhase() && isSummonTrap) return selectSummonTrap(cardPosition, number);
        if (player.isInOpponentPhase() && isSpellTrap) return selectSpellTrap(cardPosition, number);
        if (opponent != null && !opponent.equals("")) player = Player.opponent;
        if (selectedCard != null) deSelectCard();
        if (cardPosition.equals("hand") && player.getBoard().getCardFromHand(number) != null) {
            player.getBoard().getCardFromHand(number).setSelected(true);
            selectedCard = player.getBoard().getCardFromHand(number);
            return 1;
        } else if (cardPosition.equals("hand")) return 0;
        else if (cardPosition.equals("field") && player.getBoard().getFieldZone().get(0) != null) {
            player.getBoard().getFieldZone().get(0).setSelected(true);
            selectedCard = player.getBoard().getFieldZone().get(0);
            return 1;
        } else if (cardPosition.equals("field")) return 0;
        number = switchNumberForCurrent(number);
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

        return -1;
    }

    public static int switchNumberForCurrent(int number) {
        if (number == 1) return 2;
        if (number == 2) return 3;
        if (number == 3) return 1;
        if (number == 4) return 4;
        return 0;
    }


    private static int selectAttackTrap(String cardPosition, int number) {
        number = switchNumberForCurrent(number);
        if (Player.currentPlayer.getBoard().getCardFromSpellField(number) == null) return 0;
        String cardName = Player.currentPlayer.getBoard().getCardFromSpellField(number).getCardName();
        if (cardPosition.equals("spell") && (cardName.equals("Magic Cylinder") || cardName.equals("Mirror Force")) ||
                cardName.equals("Negate Attack")) {
            Player.currentPlayer.getBoard().getCardFromSpellField(number).setSelected(true);
            selectedCard = Player.currentPlayer.getBoard().getCardFromSpellField(number);
            return 1;
        }

        return -1;
    }

    public static int selectSpellTrap(String cardPosition, int number) {
        number = switchNumberForCurrent(number);
        if (Player.currentPlayer.getBoard().getCardFromSpellField(number) == null) return 0;
        String cardName = Player.currentPlayer.getBoard().getCardFromSpellField(number).getCardName();
        if (cardPosition.equals("spell") && cardName.equals("Magic Jammer")) {
            Player.currentPlayer.getBoard().getCardFromSpellField(number).setSelected(true);
            selectedCard = Player.currentPlayer.getBoard().getCardFromSpellField(number);
            return 1;
        }
        return -1;
    }

    private static int selectSummonTrap(String cardPosition, int number) {
        number = switchNumberForCurrent(number);
        if (Player.currentPlayer.getBoard().getCardFromSpellField(number) == null) return 0;
        String cardName = Player.currentPlayer.getBoard().getCardFromSpellField(number).getCardName();
        if (cardPosition.equals("spell") && (cardName.equals("Trap Hole") || cardName.equals("Torrential Tribute")) ||
                cardName.equals("Solemn Warning")) {
            Player.currentPlayer.getBoard().getCardFromSpellField(number).setSelected(true);
            selectedCard = Player.currentPlayer.getBoard().getCardFromSpellField(number);
            return 1;
        }
        return -1;
    }

    public static void activeSpellsByName() {
        EffectController.spellAbsorption();
    }

    public static void activeSpell() {
        if (isSpellTrap()) return;
        activeSpellsByName();
        for (Effect effect : selectedCard.getEffects()) {
            effect.enableEffect(null);
        }
    }

    public static void deActiveSpell()
    {
        for (Effect effect : Card.getCardByName("Change of Heart").getEffects()) {
            effect.disableEffect(null);
        }

    }
    public static void activeThreeLight() {
        for (Effect effect : Card.getCardByName("Swords of Revealing Light").getEffects()) {
            effect.enableEffect(null);
        }
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

    public static String createDeck(String deckName) {

        if (ProgramController.isDeckExist(deckName))
            return "deck with name " + deckName + " already exists";
        else {
            Deck deck = new Deck(deckName);
            Player.thePlayer.addToDeckList(deck);
            Player.deActiveDecks();
            deck.setDeckActive(true);
            return "deck created successfully!";
        }
    }

    public static void deleteDeck(String deckName) {
        Player.thePlayer.deleteDeck(deckName);
    }

    public static void activateDeck(String deckName) {
        Player.deActiveDecks();
        Player.getDeckByName(deckName).setDeckActive(true);
    }

    public static void addCardToDeck(String deckName, String cardName, boolean isSide) throws CloneNotSupportedException {
        if (!isSide) {
            Player.getDeckByName(deckName).addToMainDeck((Card) Card.getCardByName(cardName).clone());
        } else {
            Player.getDeckByName(deckName).addToSideDeck((Card) Card.getCardByName(cardName).clone());
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
        return Player.getUserByUsername(user).getActiveDeck().getMainDeck().size() >= 0;
    }

    public static void initiateGame(String firstPlayer, String secondPlayer, int round) {
        RoundController.otherPlayer = Player.getUserByUsername(secondPlayer);
        RoundController.setRound(round);
        prepareGame(firstPlayer, secondPlayer);
    }

    public static void setNextGame(String firstPlayer, String secondPlayer) {
        if(Player.currentPlayer.equals(Player.theAi) || Player.opponent.equals(Player.theAi)){
            Ai.setNextGame();
        }
        ChangeCardsMenu changeCardsMenu = new ChangeCardsMenu();
        changeCardsMenu.changeDeck(firstPlayer);
        changeCardsMenu.changeDeck(secondPlayer);
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
        //Collections.shuffle(player.getBoard().getDeck());
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
        for (int i = 4; i >= 0; i--) {
            Card card = Player.opponent.getBoard().getFieldCardsForSpellTraps().get(i);
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
        for (int i = 4; i >= 0; i--) {
            Card card = Player.opponent.getBoard().getFieldCardsForMonsters().get(i);
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
        if (Player.opponent.getBoard().getFieldZone().get(0) == null) System.out.println("E");
        else System.out.println("O");
        System.out.println();
        Util.printNCharacter(26, "-");
        Util.printNCharacter(2, "\n");

        //-----------------------------

        if (Player.currentPlayer.getBoard().getFieldZone().get(0) == null) System.out.print("E");
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

    public static int summonMonster(int firstTribute, int secondTribute) {
        if (isSummonTrap()) return -1;
        if (firstTribute != 0 && secondTribute == 0) {
            Card tributeCard = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(firstTribute);
            Player.currentPlayer.getBoard().getGraveyard().add(tributeCard);
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(firstTribute, null);
        } else if (secondTribute != 0) {
            Card tributeCard1 = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(firstTribute);
            Card tributeCard2 = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(secondTribute);
            Player.currentPlayer.getBoard().getGraveyard().add(tributeCard1);
            Player.currentPlayer.getBoard().getGraveyard().add(tributeCard2);
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(firstTribute, null);
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(secondTribute, null);
        }
        Player.currentPlayer.getBoard().getHand().remove(selectedCard);
        selectedCard.setCardStatus(CardStatus.ATTACK);
        putMonsterOnField();
        callSummonEffects();
        deSelectCard();
        RoundController.isSummoned = true;
        return 0;
    }


    public static void callSummonEffects() {
        if (selectedCard.getCardTypes().contains(CardType.EFFECT)) {
            for (Effect effect : selectedCard.getEffects()) {
                effect.enableEffect(null);
            }
        }
        checkCommandKnight();
    }


    private static void checkCommandKnight() {
        Card card = Card.getCardByName("Command Knight");
        if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().contains(card)) {
            for (Effect effect : card.getEffects()) {
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
        selectedCard.setCardStatus(CardStatus.BACK);
        selectedCard.setSummoned(true);
        if (selectedCard.getCardTypes().contains(CardType.FIELD) &&
                Player.currentPlayer.getBoard().getFieldZone().get(0) == null) {
            Player.currentPlayer.getBoard().getFieldZone().set(0, selectedCard);
        } else if (selectedCard.getCardTypes().contains(CardType.FIELD)) {
            Player.currentPlayer.getBoard().getGraveyard().add(Player.currentPlayer.getBoard().getFieldZone().get(0));
            Player.currentPlayer.getBoard().getFieldZone().set(0, selectedCard);
        } else putSpellTrapOnField();
        deSelectCard();
    }

    public static void putSpellTrapOnField() {
        if (Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().get(2) == null)
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(2, selectedCard);
        else if (Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().get(3) == null)
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(3, selectedCard);
        else if (Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().get(1) == null)
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(1, selectedCard);
        else if (Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().get(4) == null)
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(4, selectedCard);
        else
            Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().set(0, selectedCard);

    }

    public static void putMonsterOnField() {
        if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(2) == null)
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(2, selectedCard);
        else if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(3) == null)
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(3, selectedCard);
        else if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(1) == null)
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(1, selectedCard);
        else if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(4) == null)
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
        selectedCard.setSummoned(true);
        if (isSummonTrap()) return;
        selectedCard.setCardStatus(CardStatus.ATTACK);
        callSummonEffects();
        deSelectCard();
    }

    public static void specialSummon() {
        GameMenu gameMenu = new GameMenu();
        if (selectedCard.getCardName().equals("Gate Guardian")) {
            summonGateGuardian();
        } else if (selectedCard.getCardName().equals("Beast King Barbaros")) {
            activeSpell();
            summonBarbaros();
        }
    }

    private static void summonBarbaros() {
        if (getMonsterFieldSize() < 3) {
            System.out.println("there are not enough cards for tribute");
            return;
        }
        String input = Communicate.input("Pick Monster for tribute");
        if (input.equals("cancel")) {
            System.out.println("Tribute Canceled");
            return;
        }
        int tribute = Integer.parseInt(input);
        tribute = GameController.switchNumberForCurrent(tribute);
        input = Communicate.input("Pick another Monster for tribute");
        if (input.equals("cancel")) {
            System.out.println("Tribute Canceled");
            return;
        }
        int tribute1 = Integer.parseInt(input);
        tribute1 = GameController.switchNumberForCurrent(tribute1);

        input = Communicate.input("Pick another Monster for tribute");
        if (input.equals("cancel")) {
            System.out.println("Tribute Canceled");
            return;
        }
        int tribute2 = Integer.parseInt(input);
        tribute2 = GameController.switchNumberForCurrent(tribute2);
        if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute) == null ||
                Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute1) == null ||
                Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute2) == null) {
            System.out.println("there is no monster on one of these addresses");
            return;
        }
        Card tributeCard1 = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute);
        Card tributeCard2 = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute1);
        Card tributeCard3 = Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute2);
        Player.currentPlayer.getBoard().getGraveyard().add(tributeCard1);
        Player.currentPlayer.getBoard().getGraveyard().add(tributeCard2);
        Player.currentPlayer.getBoard().getGraveyard().add(tributeCard3);
        Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(tribute, null);
        Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(tribute1, null);
        Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(tribute2, null);
        summonMonster(0, 0);
    }

    private static void summonGateGuardian() {
        if (getMonsterFieldSize() < 2) {
            System.out.println("there are not enough cards for tribute");
            return;
        }
        int check = 0;
        ArrayList<Integer> tributeIndex = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            String input = Communicate.input("Pick Monster for tribute");
            if (input.equals("cancel")) {
                System.out.println("Tribute Canceled");
                return;
            }
            int tribute = Integer.parseInt(input);
            tribute = GameController.switchNumberForCurrent(tribute);
            tributeIndex.add(tribute);
        }
        for (int i = 0; i < 3; i++) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tributeIndex.get(i)) == null) {
                System.out.println("there is no monster on one of these addresses");
                return;
            }
        }
        for (int i = 0; i < 3; i++) {
            Player.currentPlayer.getBoard().getGraveyard().add(Player.currentPlayer.getBoard().
                    getFieldCardsForMonsters().get(tributeIndex.get(i)));
            Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(tributeIndex.get(i), null);
        }
        selectedCard.setCardStatus(CardStatus.ATTACK);
        putMonsterOnField();
        System.out.println("special summoned successfully");
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
        if (isAttackTrap()) return;
        if (enemyCard.getCardStatus().equals(CardStatus.ATTACK)) {

            if (checkEffects(enemyCard)) return;
            if (isMonsterChain(enemyCard, enemyMonsterIndex)) return;
            if (enemyCard.getAttack() < selectedCard.getAttack()) {
                putMonsterOnGraveYard(enemyCard, Player.opponent);
                int damage = selectedCard.getAttack() - enemyCard.getAttack();
                Player.opponent.increaseLifePoint(-1 * damage);
                gameMenu.printMonsterAttacks(1, damage, enemyMonsterIndex);
            } else if (enemyCard.getAttack() == selectedCard.getAttack()) {
                putMonsterOnGraveYard(enemyCard, Player.opponent);
                putMonsterOnGraveYard(selectedCard, Player.currentPlayer);
                gameMenu.printMonsterAttacks(2, 0, enemyMonsterIndex);
            } else {
                putMonsterOnGraveYard(selectedCard, Player.currentPlayer);
                int damage = enemyCard.getAttack() - selectedCard.getAttack();
                Player.currentPlayer.increaseLifePoint(-1 * damage);
                gameMenu.printMonsterAttacks(3, damage, enemyMonsterIndex);
            }
        } else {
            if (checkEffects(enemyCard)) return;
            if (isMonsterChain(enemyCard, enemyMonsterIndex)) return;
            if (enemyCard.getDefence() < selectedCard.getAttack()) {
                if (enemyCard.getCardStatus().equals(CardStatus.DEFENCE))
                    gameMenu.printMonsterAttacks(4, 0, enemyMonsterIndex);
                else gameMenu.printMonsterAttacks(7, 0, enemyMonsterIndex);
                putMonsterOnGraveYard(enemyCard, Player.opponent);
            } else if (enemyCard.getDefence() == selectedCard.getAttack()) {
                if (enemyCard.getCardStatus().equals(CardStatus.DEFENCE))
                    gameMenu.printMonsterAttacks(5, 0, enemyMonsterIndex);
                else gameMenu.printMonsterAttacks(8, 0, enemyMonsterIndex);
            } else if (enemyCard.getDefence() > selectedCard.getAttack()) {
                int damage = enemyCard.getDefence() - selectedCard.getAttack();
                Player.currentPlayer.increaseLifePoint(-1 * damage);
                if (enemyCard.getCardStatus().equals(CardStatus.DEFENCE))
                    gameMenu.printMonsterAttacks(6, damage, enemyMonsterIndex);
                else gameMenu.printMonsterAttacks(9, damage, enemyMonsterIndex);
            }
        }
        deSelectCard();
    }

    public static int attackDirect() {
        if (isAttackTrap()) return -1;
        selectedCard.setAttacked(true);
        Player.opponent.increaseLifePoint(-1 * selectedCard.getAttack());
        return selectedCard.getAttack();
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

    public static boolean isMonsterChain(Card enemyCard, int enemyMonsterIndex) {
        GameMenu gameMenu = new GameMenu();
        if (enemyCard.getCardName().equals("Yomi Ship") &&
                (enemyCard.getAttack() < selectedCard.getAttack() ||
                        enemyCard.getDefence() < selectedCard.getAttack())) {
            putMonsterOnGraveYard(enemyCard, Player.opponent);
            putMonsterOnGraveYard(selectedCard, Player.currentPlayer);
            gameMenu.printMonsterAttacks(2, 0, enemyMonsterIndex);
            return true;
        }
        if (enemyCard.getCardName().equals("Suijin")) {
            RoundController.changeTurn();
            showBoard();
            String input = Communicate.input("do you want to use card effect? (yes/no)");
            if (input.equals("yes")) {
                int reservedAttack = selectedCard.getAttack();
                selectedCard.setAttack(0);
                putMonsterOnGraveYard(selectedCard, Player.currentPlayer);
                int damage = enemyCard.getAttack() - selectedCard.getAttack();
                Player.currentPlayer.increaseLifePoint(-1 * damage);
                gameMenu.printMonsterAttacks(3, damage, enemyMonsterIndex);
                selectedCard.setAttack(reservedAttack);
                RoundController.changeTurn();
                return true;
            } else {
                RoundController.changeTurn();
                return false;
            }
        }
        if (enemyCard.getCardName().equals("Marshmallon")) {
            if (enemyCard.getCardStatus().equals(CardStatus.SET)) {
                Player.currentPlayer.setLifePoint(Player.currentPlayer.getLifePoint() - 1000);
            }
            return true;
        }
        if (enemyCard.getCardName().equals("Texchanger") && isCyberseActive) {
            isCyberseActive = false;
            RoundController.changeTurn();
            showBoard();
            String input = Communicate.input("do you want to use card effect? (yes/no)");
            if (input.equals("yes")) {
                lastSelectedCard = selectedCard;
                selectedCard = enemyCard;
                activeSpell();
                selectedCard = lastSelectedCard;
            }
            RoundController.changeTurn();
            return true;
        }
        return false;
    }

    public static boolean checkEffects(Card enemyCard) {
        if (EffectController.messengerOfPeace()) return true;
        switch (enemyCard.getCardName()) {
            case "Command Knight":
                for (Card fieldCardsForMonster : Player.opponent.getBoard().getFieldCardsForMonsters()) {
                    if (fieldCardsForMonster != null &&
                            !fieldCardsForMonster.getCardName().equals("Command Knight")) {
                        CardMenu.printCardMassage("Command Knight");
                        return true;
                    }
                }
                return false;
            case "Exploder Dragon":
                if ((enemyCard.getCardStatus().equals(CardStatus.ATTACK) && selectedCard.getAttack() > enemyCard.getAttack()) ||
                        (enemyCard.getCardStatus().equals(CardStatus.DEFENCE) && selectedCard.getAttack() > enemyCard.getDefence()) ||
                        (enemyCard.getCardStatus().equals(CardStatus.SET) && selectedCard.getAttack() > enemyCard.getDefence())) {
                    Player.currentPlayer.getBoard().getGraveyard().add(selectedCard);
                    int index = Player.currentPlayer.getBoard().getFieldCardsForMonsters().indexOf(selectedCard);
                    Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(index, null);
                    Player.opponent.getBoard().getGraveyard().add(enemyCard);
                    index = Player.opponent.getBoard().getFieldCardsForMonsters().indexOf(enemyCard);
                    Player.opponent.getBoard().getFieldCardsForMonsters().set(index, null);
                    return true;
                }
                return false;

        }

        if (selectedCard.getCardName().equals("Exploder Dragon")) {
            if ((enemyCard.getCardStatus().equals(CardStatus.ATTACK) && selectedCard.getAttack() < enemyCard.getAttack()) ||
                    (enemyCard.getCardStatus().equals(CardStatus.DEFENCE) && selectedCard.getAttack() < enemyCard.getDefence()) ||
                    (enemyCard.getCardStatus().equals(CardStatus.SET) && selectedCard.getAttack() < enemyCard.getDefence())) {
                Player.currentPlayer.getBoard().getGraveyard().add(selectedCard);
                int index = Player.currentPlayer.getBoard().getFieldCardsForMonsters().indexOf(selectedCard);
                Player.currentPlayer.getBoard().getFieldCardsForMonsters().set(index, null);
                Player.opponent.getBoard().getGraveyard().add(enemyCard);
                index = Player.opponent.getBoard().getFieldCardsForMonsters().indexOf(enemyCard);
                Player.opponent.getBoard().getFieldCardsForMonsters().set(index, null);
                return true;
            }
        }
        return false;
    }



    public static boolean isAttackTrap() {
        if (!isTrapFrozen) {
            ArrayList<Card> trapList = Player.opponent.getBoard().getFieldCardsForSpellTraps();
            if (trapList.contains(Card.getCardByName("Magic Cylinder")) ||
                    trapList.contains(Card.getCardByName("Mirror Force")) ||
                    trapList.contains(Card.getCardByName("Negate Attack"))) {
                lastSelectedCard = selectedCard;
                isAttackTrap = true;
                return isChangedTurnInMiddle();
            }
            return false;
        }
        return false;
    }

    public static boolean isSpellTrap() {
        if (!isTrapFrozen) {
            ArrayList<Card> trapList = Player.opponent.getBoard().getFieldCardsForSpellTraps();
            if (trapList.contains(Card.getCardByName("Magic Jammer"))) {
                lastSelectedCard = selectedCard;
                isSpellTrap = true;
                return isChangedTurnInMiddle();
            }

        }
        return false;
    }

    public static boolean isSummonTrap() {
        if (!isTrapFrozen) {
            ArrayList<Card> trapList = Player.opponent.getBoard().getFieldCardsForSpellTraps();
            lastSelectedCard = selectedCard;
            if (selectedCard.getAttack() >= 1000 && trapList.contains(Card.getCardByName("Trap Hole"))) {
                isSummonTrap = true;

                return isChangedTurnInMiddle();
            } else if (trapList.contains(Card.getCardByName("Torrential Tribute"))) {
                isSummonTrap = true;

                return isChangedTurnInMiddle();
            } else if (trapList.contains(Card.getCardByName("Solemn Warning"))) {
                isSummonTrap = true;

                return isChangedTurnInMiddle();
            }
            return false;
        }
        return false;
    }

    private static void callTrapEffect(Card card) {
        for (Effect effect : card.getEffects()) {
            effect.enableEffect(null);
        }
    }

    public static void putMonsterOnGraveYard(Card card, Player player) {
        player.getBoard().getGraveyard().add(card);
        int index = player.getBoard().getFieldCardsForMonsters().indexOf(card);
        player.getBoard().getFieldCardsForMonsters().set(index, null);
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
        if (isChecked) isChangedTurnInMiddle();
    }

    private static boolean isChangedTurnInMiddle() {
        GameMenu gameMenu = new GameMenu();
        gameMenu.printMiddleChange();
        RoundController.changeTurn();
        showBoard();
        if (Player.currentPlayer.equals(Player.theAi)) return true;
        if (gameMenu.changePhaseInMiddle().equals("no")) {
            gameMenu.printMiddleChange();
            RoundController.changeTurn();
            showBoard();
            return false;
        } else {
            Player.currentPlayer.setInOpponentPhase(true);
            return true;
        }
    }

    public static void getBackFromMiddleChange() {
        GameMenu gameMenu = new GameMenu();
        Player.currentPlayer.setInOpponentPhase(false);
        gameMenu.printMiddleChange();
        RoundController.changeTurn();
        showBoard();
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

    public static int getMonsterFieldSize() {
        int numberOfMonsters = 0;
        for (int i = 0; i < 5; i++) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null)
                numberOfMonsters++;
        }
        return numberOfMonsters;
    }

}
