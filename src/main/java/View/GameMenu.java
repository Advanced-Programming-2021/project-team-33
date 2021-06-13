package View;

import Controller.*;
import Model.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;

public class GameMenu {


    public ImageView cardShow, hand1, hand2, hand3, hand4, hand5, hand6, enemyHand1, enemyHand2, enemyHand3, enemyHand4, enemyHand5, enemyHand6;
    public ImageView monster1, monster2, monster3, monster4, monster5, spell1, spell2, spell3, spell4, spell5, enemySpell1, enemySpell2, enemySpell3;
    public ImageView enemySpell4, enemySpell5, enemyMonster1, enemyMonster2, enemyMonster3, enemyMonster4, enemyMonster5, graveyard, enemyGraveyard;
    public Label firstPlayerName, secondPlayerName, firstPlayerLP, secondPlayerLP, attack, defence, description, turn;
    public ImageView button1, button2, button3, button4;
    public Label massage;
    private ArrayList<ImageView> currentHand, enemyHand, currentMonster, enemyMonster, currentSpell, enemySpell;


    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("gameMenu.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        secondPlayerName.setText(RoundController.otherPlayer.getNickname());
        firstPlayerName.setText(Player.thePlayer.getNickname());
        makeSpellList();
        currentHand = makeHandList();
        enemyHand = makeEnemyHandList();
        currentMonster = makeMonsterList();
        updateBoard(currentHand, enemyHand, currentMonster);
        selectCard();
    }

    private void updateBoard(ArrayList<ImageView> currentHand, ArrayList<ImageView> enemyHand, ArrayList<ImageView> currentMonster) {
        if (Player.currentPlayer.getBoard().getGraveyard().size() != 0)
            graveyard.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
        else graveyard.setImage(null);
        if (Player.opponent.getBoard().getGraveyard().size() != 0)
            graveyard.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
        else graveyard.setImage(null);
        firstPlayerLP.setText(Integer.toString(Player.thePlayer.getLifePoint()));
        secondPlayerLP.setText(Integer.toString(RoundController.otherPlayer.getLifePoint()));
        turn.setText("Now is " + Player.currentPlayer.getNickname() + "'s turn");
        for (int i = 0; i < Player.currentPlayer.getBoard().getHand().size(); i++) {
            currentHand.get(i).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
        }
        for (int i = 0; i < Player.opponent.getBoard().getHand().size(); i++) {
            enemyHand.get(i).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
        }
        for (int i = 0; i < 5; i++) {
            int k = 4;
            if (i == 1) k = 2;
            else if (i == 2) k = 1;
            else if (i == 3) k = 3;
            else if (i == 4) k = 5;
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(i) != null)
                currentMonster.get(k - 1).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                        Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(0).getCardName().replaceAll("\\s+", "")
                        + ".jpg")));
            else currentMonster.get(k - 1).setImage(null);
            if (Player.currentPlayer.getBoard().getFieldCardsForSpellTraps().get(i) != null)
                currentMonster.get(k - 1).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                        Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(0).getCardName().replaceAll("\\s+", "")
                        + ".jpg")));
            else currentMonster.get(k - 1).setImage(null);
            if (i == 0) k = 5;
            if (i == 1) k = 3;
            if (i == 3) k = 2;
            if (i == 4) k = 4;
            if (Player.opponent.getBoard().getFieldCardsForMonsters().get(i) != null)
                currentMonster.get(k - 1).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                        Player.opponent.getBoard().getFieldCardsForMonsters().get(0).getCardName().replaceAll("\\s+", "")
                        + ".jpg")));
            else currentMonster.get(k - 1).setImage(null);
            if (Player.opponent.getBoard().getFieldCardsForSpellTraps().get(i) != null)
                currentMonster.get(k - 1).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                        Player.opponent.getBoard().getFieldCardsForMonsters().get(0).getCardName().replaceAll("\\s+", "")
                        + ".jpg")));
            else currentMonster.get(k - 1).setImage(null);
        }
    }

    private ArrayList<ImageView> makeMonsterList() {
        currentMonster = new ArrayList<>();
        enemyMonster = new ArrayList<>();
        currentMonster.add(monster1);
        currentMonster.add(monster2);
        currentMonster.add(monster3);
        currentMonster.add(monster4);
        currentMonster.add(monster5);
        enemyMonster.add(enemyMonster1);
        enemyMonster.add(enemyMonster2);
        enemyMonster.add(enemyMonster3);
        enemyMonster.add(enemyMonster4);
        enemyMonster.add(enemyMonster5);
        return currentMonster;
    }

    private void makeSpellList() {
        currentSpell = new ArrayList<>();
        enemySpell = new ArrayList<>();
        currentSpell.add(spell1);
        currentSpell.add(spell2);
        currentSpell.add(spell3);
        currentSpell.add(spell4);
        currentSpell.add(spell5);
        enemySpell.add(enemySpell1);
        enemySpell.add(enemySpell2);
        enemySpell.add(enemySpell3);
        enemySpell.add(enemySpell4);
        enemySpell.add(enemySpell5);
    }

    private ArrayList<ImageView> makeEnemyHandList() {
        enemyHand = new ArrayList<>();
        enemyHand.add(enemyHand1);
        enemyHand.add(enemyHand2);
        enemyHand.add(enemyHand3);
        enemyHand.add(enemyHand4);
        enemyHand.add(enemyHand5);
        enemyHand.add(enemyHand6);
        return enemyHand;
    }

    private ArrayList<ImageView> makeHandList() {
        ArrayList<ImageView> currentHand = new ArrayList<>();
        currentHand.add(hand1);
        currentHand.add(hand2);
        currentHand.add(hand3);
        currentHand.add(hand4);
        currentHand.add(hand5);
        currentHand.add(hand6);
        return currentHand;
    }

    private void selectCard() {
        hand1.setOnMouseClicked(event -> {
            Card card = Player.currentPlayer.getBoard().getHand().get(0);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand2.setOnMouseClicked(event -> {
            Card card = Player.currentPlayer.getBoard().getHand().get(1);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand3.setOnMouseClicked(event -> {
            Card card = Player.currentPlayer.getBoard().getHand().get(2);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand4.setOnMouseClicked(event -> {
            Card card = Player.currentPlayer.getBoard().getHand().get(3);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand5.setOnMouseClicked(event -> {
            Card card = Player.currentPlayer.getBoard().getHand().get(4);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand6.setOnMouseClicked(event -> {
            Card card = Player.currentPlayer.getBoard().getHand().get(5);
            setViewForSelected(card);
            setButtonsForHand();
        });
    }

    private void setViewForSelected(Card card) {
        updateBoard(currentHand, enemyHand, currentMonster);
        cardShow.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/" +
                card.getCardName().replaceAll("\\s+", "")
                + ".jpg")));
        if (card.getCardCategory().equals(CardCategory.MONSTER) ||
                card.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
            attack.setText(Integer.toString(card.getAttack()));
            defence.setText(Integer.toString(card.getAttack()));
        } else {
            attack.setText("---");
            defence.setText("---");
        }
        description.setText(card.getDescription());
        GameController.selectedCard = Player.currentPlayer.getBoard().getHand().get(0);
    }

    private void setButtonsForHand() {
        button1.setImage(new Image(getClass().getResourceAsStream("/PNG/Summon.png")));
        button2.setImage(new Image(getClass().getResourceAsStream("/PNG/set.png")));
        button3.setImage(new Image(getClass().getResourceAsStream("/PNG/active.png")));
        button4.setImage(new Image(getClass().getResourceAsStream("/PNG/nextPhase.png")));
    }


    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        CardMenu.showSelectedCard(Util.getCommand(input, "card show --selected"));
        selectCard(Util.getCommand(input, "select --(\\S+)( --opponent)? (\\d+)"));
        showGraveyard(Util.getCommand(input, "show graveyard"));
        deSelectCard(Util.getCommand(input, "select -d"));
        activeSpell(Util.getCommand(input, "activate effect"));
        flipSummon(Util.getCommand(input, "flip-summon"));
        setCard(Util.getCommand(input, "set"));
        setPosition(Util.getCommand(input, "set --position ((attack)|(defence))"));
        attackToMonster(Util.getCommand(input, "attack (\\d+)"));
        attackDirect(Util.getCommand(input, "attack direct"));
        goToNextPhase(Util.getCommand(input, "next phase"));
        showBoard(Util.getCommand(input, "showBoard"));
        surrender(Util.getCommand(input, "surrender"));
        cancel(Util.getCommand(input, "cancel"));
        increaseMoney(Util.getCommand(input, "increase --money (\\d+)"));
        increaseLifePoint(Util.getCommand(input, "increase --LP (\\d+)"));
        winTheGame(Util.getCommand(input, "duel set-winner (\\S+)"));
        RoundController.checkEndOfRound();
    }


    private void goToNextPhase(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (Player.currentPlayer.getPhase().equals(Phase.MAIN1)) RoundController.battlePhase();
            else if (Player.currentPlayer.getPhase().equals(Phase.BATTLE)) RoundController.mainPhase2();
            else if (Player.currentPlayer.getPhase().equals(Phase.MAIN2)) {
                RoundController.endPhase();
                System.out.println("It's " + Player.currentPlayer.getUsername() + "'s turn");
            }
        }
    }

    public void endPhaseMassage() {
        System.out.println("It's " + Player.currentPlayer.getUsername() + "'s turn");
    }

    private void showGraveyard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (Player.currentPlayer.getBoard().getGraveyard().get(0) == null)
                System.out.println("graveyard empty");
            else {
                GameController.printGraveyardCards();
                MainMenu.menu = "Graveyard";
            }
        }
    }

    private void showBoard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            GameController.showBoard();
        }
    }


    public void rollDice(int first, int second, String currentPlayer, String firstPlayer, String secondPlayer) {
        System.out.println(firstPlayer + " get " + first);
        System.out.println(secondPlayer + " get " + second);
        System.out.println(currentPlayer + "'s turn\n\n");
    }

    public void printMiddleChange() {
        System.out.println("now it will be " + Player.opponent.getUsername() + "’s turn");
    }

    public String changePhaseInMiddle() {
        return Communicate.input("do you want to active your spell or trap?(yes or no)");
    }

    public void informEndOfGame(Player winner, int score) {
        System.out.println(winner.getUsername() + " won the whole match with score: " + score + " - 0\n\n");
    }

    public void informEndOfRound(Player winner, int score, int remainingRounds) {
        System.out.println("Round " + remainingRounds + " ended");
        System.out.println(winner.getUsername() + " won the game with score: " + score + " - 0");
        remainingRounds--;
        if (remainingRounds != 1)
            System.out.println("Now is time for round " + remainingRounds + "\n\n");
    }


    public void informPhase(Phase phase) {
        switch (phase) {
            case DRAW -> System.out.println("phase: Draw Phase");
            case STANDBY -> System.out.println("phase: Standby Phase");
            case MAIN1 -> System.out.println("phase: Main Phase 1");
            case BATTLE -> System.out.println("phase: Battle Phase");
            case MAIN2 -> System.out.println("phase: Main Phase 2");
            case END -> System.out.println("phase: End Phase");
        }
    }

    public void drawCard(Card card) {
        System.out.println("new card added to the hand : " + card.getCardName());
    }


    private void activeSpell(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (Player.currentPlayer.isInOpponentPhase()) {
                if (!GameController.selectedCard.getCardCategory().equals(CardCategory.SPELL) &&
                        !GameController.selectedCard.getCardCategory().equals(CardCategory.TRAP))
                    System.out.println("activate effect is only for spell cards.");
                    //other things needed for if blow
                else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.BACK) &&
                        !GameController.selectedCard.getCardStatus().equals(CardStatus.HAND))
                    System.out.println("you can't active this card");
                else {
                    if (GameController.selectedCard.getCardCategory().equals(CardCategory.TRAP))
                        System.out.println("trap activated");
                    else System.out.println("spell activated");
                    GameController.activeSpell();
                    GameController.getBackFromMiddleChange();
                }
            } else {
                if (!GameController.selectedCard.getCardCategory().equals(CardCategory.SPELL) &&
                        !GameController.selectedCard.getCardCategory().equals(CardCategory.TRAP))
                    System.out.println("activate effect is only for spell cards.");
                else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                        !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
                    System.out.println("you can’t activate an effect on this turn");
                else if (GameController.selectedCard.isActivated())
                    System.out.println("you have already activated this card");
                else if (GameController.isSpellTrapFieldFull() //&& !isForFieldZone
                ) System.out.println("spell card zone is full");
                    //else if( !isActivable) System.out.println("preparations of this spell are not done yet");
                else {
                    System.out.println("spell activated");
                    GameController.activeSpell();
                }
            }

        }
    }

    private void summonMonster() {


        if (Player.currentPlayer.isInOpponentPhase())
            showError("it’s not your turn to play this kind of moves");
        else if (GameController.selectedCard == null) showError("no card is selected yet");
        else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.HAND) ||
                (!GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) &&
                        !GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)))
            showError("you can’t summon this card");
        else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
            showError("action not allowed in this phase");
        else if (GameController.isMonsterFieldFull()) showError("monster card zone is full");
        else if (RoundController.isSummoned) showError("you already summoned/set on this turn");
        else if (GameController.selectedCard.getLevel() > 4 && GameController.selectedCard.getLevel() < 7) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().size() == 0) {
                showError("there are not enough cards for tribute");
                return;
            }
            String input = Communicate.input("Pick Monster for tribute");
            if (input.equals("cancel")) {
                showError("Tribute Canceled");
                return;
            }
            int tribute = Integer.parseInt(Communicate.input("Pick Monster for tribute"));
            tribute = GameController.switchNumberForCurrent(tribute);
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute) == null) {
                showError("there are no monsters on this address");
                return;
            }
            int command = GameController.summonMonster(tribute, 0);
            if (command != -1) System.out.println("summoned successfully1");
        } else if (GameController.selectedCard.getLevel() > 6 && GameController.selectedCard.getLevel() < 9) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().size() < 2) {
                showError("there are not enough cards for tribute");
                return;
            }
            String input = Communicate.input("Pick Monster for tribute");
            if (input.equals("cancel")) {
                showError("Tribute Canceled");
                return;
            }
            int tribute = Integer.parseInt(input);
            tribute = GameController.switchNumberForCurrent(tribute);
            input = Communicate.input("Pick another Monster for tribute");
            if (input.equals("cancel")) {
                showError("Tribute Canceled");
                return;
            }
            int tribute1 = Integer.parseInt(input);
            tribute1 = GameController.switchNumberForCurrent(tribute1);
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute) == null ||
                    Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute1) == null) {
                showError("there is no monster on one of these addresses");
                return;
            }
            int command = GameController.summonMonster(tribute, tribute1);
            if (command != -1) System.out.println("summoned successfully2");
        } else {
            int command = GameController.summonMonster(0, 0);
            if (command != -1) System.out.println("summoned successfully3");
        }

    }


    private void flipSummon(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (Player.currentPlayer.isInOpponentPhase())
                System.out.println("it’s not your turn to play this kind of moves");
            else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK) &&
                    !GameController.selectedCard.getCardStatus().equals(CardStatus.DEFENCE) &&
                    !GameController.selectedCard.getCardStatus().equals(CardStatus.SET))
                System.out.println("you can’t change this card position");
            else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                    !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
                System.out.println("action not allowed in this phase");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.SET) ||
                    GameController.selectedCard.isSummoned())
                System.out.println("you can’t flip summon this card");
            else {
                System.out.println("flip summoned successfully");
                GameController.flipSummon();
            }
        }
    }

    private void setCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (Player.currentPlayer.isInOpponentPhase())
                System.out.println("it’s not your turn to play this kind of moves");
            else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.HAND))
                System.out.println("you can’t set this card");
            else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                    !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
                System.out.println("action not allowed in this phase");
            else if (GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) ||
                    GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
                if (GameController.isMonsterFieldFull()) System.out.println("monster card zone is full");
                else if (RoundController.isSummoned) System.out.println("you already summoned/set on this turn");
                else {
                    GameController.setMonster();
                    System.out.println("set successfully1");
                }
            } else {
                if (GameController.isSpellTrapFieldFull()) System.out.println("spell card zone is full");
                else {
                    GameController.setSpell();
                    System.out.println("set successfully2");
                }
            }
        }
    }

    private void setPosition(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String position = matcher.group(1);
            if (Player.currentPlayer.isInOpponentPhase())
                System.out.println("it’s not your turn to play this kind of moves");
            else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK) &&
                    !GameController.selectedCard.getCardStatus().equals(CardStatus.DEFENCE))
                System.out.println("you can’t change this card position");
            else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                    !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
                System.out.println("action not allowed in this phase");
            else if (GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK) && position.equals("attack"))
                System.out.println("this card is already in the wanted position");
            else if (GameController.selectedCard.getCardStatus().equals(CardStatus.DEFENCE) && position.equals("defence"))
                System.out.println("this card is already in the wanted position");
            else if (GameController.selectedCard.isChanged())
                System.out.println("you already changed this card position in this turn");
            else {
                GameController.changeCardPosition(position);
                System.out.println("monster card position changed successfully");
            }
        }
    }

    private void attackToMonster(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            int enemyMonsterIndex = GameController.switchNumberForCurrent(Integer.parseInt(matcher.group(1)));
            if (Player.currentPlayer.isInOpponentPhase())
                System.out.println("it’s not your turn to play this kind of moves");
            else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK))
                System.out.println("you can’t attack with this card");
            else if (!Player.currentPlayer.getPhase().equals(Phase.BATTLE))
                System.out.println("you can’t do this action in this phase");
            else if (GameController.selectedCard.isAttacked())
                System.out.println("this card already attacked");
            else if (Player.opponent.getBoard().getFieldCardsForMonsters().get(enemyMonsterIndex) == null)
                System.out.println("there is no card to attack here");
            else {
                GameController.attackMonster(enemyMonsterIndex);
            }
        }
    }

    public void printMonsterAttacks(int key, int damage, int enemyMonsterIndex) {
        if (key == 1) System.out.println("your opponent’s monster is destroyed and your" +
                " opponent receives " + damage + " battle damage");
        else if (key == 2) System.out.println("both you and your opponent monster cards are destroyed and no\n" +
                "one receives damage");
        else if (key == 3) System.out.println("Your monster card is destroyed and you received" +
                " opponent receives " + damage + " battle damage");
        else if (key == 4) System.out.println("the defense position monster is destroyed");
        else if (key == 5) System.out.println("no card is destroyed");
        else if (key == 6) System.out.println("no card is destroyed and you received " + damage + " battle damage");
        else if (key == 7) System.out.println("opponent’s monster card was " +
                Player.opponent.getBoard().getFieldCardsForMonsters().get(enemyMonsterIndex).getCardName() +
                " and the defense position monster is destroyed");
        else if (key == 8) System.out.println("opponent’s monster card was " +
                Player.opponent.getBoard().getFieldCardsForMonsters().get(enemyMonsterIndex).getCardName() +
                " and no card is destroyed");
        else if (key == 9) System.out.println("opponent’s monster card was " +
                Player.opponent.getBoard().getFieldCardsForMonsters().get(enemyMonsterIndex).getCardName() +
                " and no card is destroyed and you received " + damage + " battle damage");

    }

    private void attackDirect(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (Player.currentPlayer.isInOpponentPhase())
                System.out.println("it’s not your turn to play this kind of moves");
            else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK))
                System.out.println("you can’t attack with this card");
            else if (!Player.currentPlayer.getPhase().equals(Phase.BATTLE))
                System.out.println("you can’t do this action in this phase");
            else if (GameController.selectedCard.isAttacked())
                System.out.println("this card already attacked");
            else if (!GameController.isEnemyMonsterFieldEmpty())
                System.out.println("you can’t attack the opponent directly");
            else {
                int damage = GameController.attackDirect();
                if (damage != -1) System.out.println("your opponent receives " + damage + " battle damage");
            }
        }
    }

    public void selectCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String opponent = "";
            String cardPosition = matcher.group(1);
            if (matcher.group(2) != null) opponent = matcher.group(2);
            int number = Integer.parseInt(matcher.group(3));
            int massage = GameController.selectCard(cardPosition, number, opponent);
            if (massage == 1) System.out.println("card selected");
            else if (massage == 0) System.out.println("no card found in the given position");
            else System.out.println("invalid selection");
            if (massage == 1 && !opponent.equals("")) GameController.setIsOpponentCardSelected(true);
        }
    }

    public void deSelectCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else {
                System.out.println("card deselected");
                GameController.deSelectCard();
            }
        }
    }

    private void cancel(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;

        }
    }

    private void surrender(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            Player.currentPlayer.setLifePoint(0);
        }
    }

    private void increaseMoney(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            Player.currentPlayer.increaseMoney(Integer.parseInt(matcher.group(1)));
        }
    }

    private void increaseLifePoint(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            Player.currentPlayer.increaseLifePoint(Integer.parseInt(matcher.group(1)));
        }
    }

    private void winTheGame(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (matcher.group(1).equals(Player.currentPlayer.getNickname())) {
                Player.opponent.setLifePoint(0);
            }
        }
    }

    private void showError(String error) {
        massage.setText(error);
    }

}
