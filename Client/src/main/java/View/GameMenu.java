package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.RoundController;
import Controller.Util;
import Model.*;
import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;

public class GameMenu {


    public ImageView cardShow, hand1, hand2, hand3, hand4, hand5, hand6, enemyHand1, enemyHand2, enemyHand3, enemyHand4, enemyHand5, enemyHand6;
    public ImageView monster1, monster2, monster3, monster4, monster5, spell1, spell2, spell3, spell4, spell5, enemySpell1, enemySpell2, enemySpell3;
    public ImageView enemySpell4, enemySpell5, enemyMonster1, enemyMonster2, enemyMonster3, enemyMonster4, enemyMonster5, graveyard, enemyGraveyard;
    public Label firstPlayerName, secondPlayerName, firstPlayerLP, secondPlayerLP, attack, defence, description, turn;
    public ImageView button1, button2, button3, button4;
    public Text massage;
    public ImageView phase;
    public ProgressBar lp1, lp2;
    public Circle avatar1, avatar2;
    public Rectangle background;
    public ImageView surrender, pause;
    public Text pauseText;
    public AnchorPane scene;
    public ImageView exclamation;
    public GridPane graveyardList;
    public Rectangle graveyardBack;
    public ImageView exit;
    public ImageView explosion;
    private ArrayList<ImageView> currentHand, enemyHand, currentMonster, enemyMonster, currentSpell, enemySpell;
    boolean isHandSelected = false, isMonsterSelected = false, isSpellSelected = false, isOneTributeActive = false,
            isTwoTributeActive = false, isAttackActive = false, isTrapActive = false, isPaused = false;
    AtomicInteger tribute = new AtomicInteger();
    public static Player otherPlayer;
    public static  Timeline timeline;

    public void start() throws IOException {
        sendPhase("main1");
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/gameMenu.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    @FXML
    public void initialize() {
        exit.toBack();
        setPauseScene();
        setCheat();
        loadImageForProfile(Player.thePlayer, avatar1);
        if (!RoundController.otherPlayer.getUsername().equals("Ai"))
            loadImageForProfile(RoundController.otherPlayer, avatar2);
        secondPlayerName.setText(RoundController.otherPlayer.getNickname());
        firstPlayerName.setText(Player.thePlayer.getNickname());
        makeSpellList();
        currentHand = makeHandList();
        enemyHand = makeEnemyHandList();
        currentMonster = makeMonsterList();
        button1.setOnMouseClicked(event -> callButton1());
        button2.setOnMouseClicked(event -> callButton2());
        button3.setOnMouseClicked(event -> callButton3());
        graveyard.setOnMouseClicked(event -> showGraveYard(Player.currentPlayer));
        enemyGraveyard.setOnMouseClicked(event -> showGraveYard(Player.opponent));
        goToNextPhase();
        ub(currentHand, enemyHand, currentMonster);
        updateBoard(currentHand, enemyHand, currentMonster);
        animatePhase();
        selectCard();
        exit.setOnMouseClicked(event -> {
            exit.toBack();
            graveyardBack.toBack();
            graveyardList.toBack();
        });
    }

    private void showGraveYard(Player player) {
        if (Player.currentPlayer.getUsername().equals(Player.thePlayer.getUsername())) {
            int count = player.getBoard().getGraveyard().size();
            exit.toFront();
            graveyardBack.toFront();
            graveyardList.toFront();
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 10; j++) {
                    count--;
                    if (count < 0) return;
                    Image image = Util.getImage(player.getBoard().getGraveyard().get(count).getCardName());
                    ImageView imageView = new ImageView(image);
                    imageView.setFitHeight(124);
                    imageView.setFitWidth(75);
                    imageView.setId("mainDeckCard" + i + "_" + j);
                    graveyardList.add(imageView, j, i);
                    int finalCount = count;
                    imageView.setOnMouseClicked(event -> {
                        cardShow.setImage(Util.getImage(player.getBoard().getGraveyard().get(finalCount).getCardName()));
                    });
                }
            }
        } else showError("It's not your turn!");
    }

    private void setCheat() {
        exclamation.setOnMouseClicked(event -> {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Cheat Console");
            dialog.setHeaderText("Cheat Console");
            dialog.setContentText("Enter your cheat code:");
            Optional<String> result = dialog.showAndWait();

            if (result.get().matches("increase --money (\\d+)"))
                increaseMoney(Util.getCommand(result.get(), "increase --money (\\d+)"));
            else if (result.get().matches("increase --LP (\\d+)"))
                increaseLifePoint(Util.getCommand(result.get(), "increase --LP (\\d+)"));
            else if (result.get().matches("duel set-winner (\\S+)")) {
                winTheGame(Util.getCommand(result.get(), "duel set-winner (\\S+)"));
                updateBoard(currentHand, enemyHand, currentMonster);
            }

        });
    }

    private void setPauseScene() {
        background.setFill(null);
        surrender.setImage(null);
        pauseText.setText(null);
        surrender.toBack();
        pause.setOnMouseClicked(event -> {
            if (!isPaused) {
                background.setFill(Color.GRAY);
                background.setOpacity(.6);
                pauseText.setText("Pause");
                pauseText.toFront();
                surrender.toFront();
                surrender.setImage(new Image(getClass().getResourceAsStream("/PNG/surrender.png")));
                isPaused = true;
            } else {
                background.setFill(null);
                surrender.setImage(null);
                surrender.toBack();

                pauseText.setText(null);
                isPaused = false;
            }
        });
        surrender.setOnMouseClicked(event -> surrender());
    }

    private void animatePhase() {
        FadeTransition fade = new FadeTransition();
        fade.setDuration(Duration.millis(1000));
        fade.setFromValue(10);
        fade.setToValue(0);
        fade.setAutoReverse(true);
        fade.setNode(phase);
        fade.play();
    }


    private void goToNextPhase() {
        if (Player.currentPlayer.getPhase().equals(Phase.DRAW)) {
            RoundController.mainPhase1();
            animateNextPhase("main");
        }
        button4.setOnMouseClicked(event -> {
            if (Player.currentPlayer.getUsername().equals(Player.thePlayer.getUsername())) {
                MainMenu.playSound(Util.CLICK_MUSIC);
                if (!isOneTributeActive && !isTwoTributeActive) {
                    if (Player.currentPlayer.getPhase().equals(Phase.MAIN1)) {
                        sendPhase("battle");
                        RoundController.battlePhase();
                        phase.setImage(new Image(getClass().getResourceAsStream("/PNG/battle.png")));
                        animatePhase();
                    } else if (Player.currentPlayer.getPhase().equals(Phase.BATTLE)) {
                        sendPhase("main2");
                        RoundController.mainPhase2();
                        phase.setImage(new Image(getClass().getResourceAsStream("/PNG/main.png")));
                        animatePhase();
                    } else if (Player.currentPlayer.getPhase().equals(Phase.MAIN2)) {
                        sendPhase("end");
                        RoundController.endPhase();
                        phase.setImage(new Image(getClass().getResourceAsStream("/PNG/end.png")));
                        animatePhase();
                        updateBoard(currentHand, enemyHand, currentMonster);
                        animateNextPhase("draw");
                        goToNextPhase();
                    }
                }
            } else showError("It's not your turn!");
        });
    }


    private void loadImageForProfile(Player player, Circle avatar) {
        ImagePattern backgroundProfile;
        int ID = player.getProfileID();
        if (player.getProfileAddress() == null) {
            if (ID <= 50) {
                backgroundProfile = new ImagePattern(new Image(getClass().getResourceAsStream("/PNG/Profile/Profile (" + ID + ").png")));
                avatar.setFill(backgroundProfile);
            }
        } else {
            File file = new File(player.getProfileAddress());
            try {
                backgroundProfile = new ImagePattern(new Image(file.toURI().toURL().toExternalForm()));
                avatar.setFill(backgroundProfile);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    private void animateNextPhase(String nextPhase) {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        phase.setImage(new Image(getClass().getResourceAsStream("/PNG/" + nextPhase + ".png")));
                        animatePhase();
                    }
                },
                1000
        );
    }

    private void ub(ArrayList<ImageView> currentHand, ArrayList<ImageView> enemyHand, ArrayList<ImageView> currentMonster) {
         timeline = new Timeline(new KeyFrame(Duration.seconds(1), ev -> {

            if (!Player.thePlayer.equals(Player.currentPlayer)) {
                getPhase();
                getMonster();
                getSpell();
                getLp();
                getGraveyard1();
                getGraveyard2();
                getAnimation();
            }
            if (Player.thePlayer.getBoard().getGraveyard().size() != 0)
                graveyard.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
            else graveyard.setImage(null);
            if (otherPlayer.getBoard().getGraveyard().size() != 0)
                enemyGraveyard.setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
            else enemyGraveyard.setImage(null);
            lp1.setProgress(Player.thePlayer.getLifePoint() / 8000.0);
            lp2.setProgress(RoundController.otherPlayer.getLifePoint() / 8000.0);
            firstPlayerLP.setText(Player.thePlayer.getUsername());
            secondPlayerLP.setText(RoundController.otherPlayer.getUsername());
            turn.setText("Now is " + Player.currentPlayer.getNickname() + "'s turn");

            for (int i = 0; i < 6; i++) {
                currentHand.get(i).setImage(null);
                for (int j = 0; j < Player.thePlayer.getBoard().getHand().size(); j++) {
                    currentHand.get(j).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
                }
            }
            for (int i = 0; i < otherPlayer.getBoard().getHand().size(); i++) {
                enemyHand.get(i).setImage(null);
                for (int j = 0; j < otherPlayer.getBoard().getHand().size(); j++) {
                    enemyHand.get(j).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
                }
            }
            for (int i = 0; i < 5; i++) {
                if (Player.thePlayer.getBoard().getFieldCardsForMonsters().get(i) == null)
                    currentMonster.get(i).setImage(null);
                else if (Player.thePlayer.getBoard().getFieldCardsForMonsters().get(i).getCardStatus() == CardStatus.ATTACK) {
                    currentMonster.get(i).setRotate(0);
                    currentMonster.get(i).setImage(Util.getImage(Player.thePlayer.getBoard().getFieldCardsForMonsters().get(i).getCardName()));
                } else if (Player.thePlayer.getBoard().getFieldCardsForMonsters().get(i).getCardStatus() == CardStatus.SET) {
                    currentMonster.get(i).setRotate(90.0);
                    currentMonster.get(i).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
                } else if (Player.thePlayer.getBoard().getFieldCardsForMonsters().get(i).getCardStatus() == CardStatus.DEFENCE) {
                    currentMonster.get(i).setRotate(90.0);
                    currentMonster.get(i).setImage(Util.getImage(Player.thePlayer.getBoard().getFieldCardsForMonsters().get(i).getCardName()));
                }
                if (Player.thePlayer.getBoard().getFieldCardsForSpellTraps().get(i) != null)
                    currentSpell.get(i).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
                else currentSpell.get(i).setImage(null);
                if (otherPlayer.getBoard().getFieldCardsForMonsters().get(i) == null)
                    enemyMonster.get(i).setImage(null);
                else if (otherPlayer.getBoard().getFieldCardsForMonsters().get(i).getCardStatus() == CardStatus.ATTACK) {
                    enemyMonster.get(i).setRotate(0);
                    enemyMonster.get(i).setImage(Util.getImage(otherPlayer.getBoard().getFieldCardsForMonsters().get(i).getCardName()));
                } else if (otherPlayer.getBoard().getFieldCardsForMonsters().get(i).getCardStatus() == CardStatus.DEFENCE) {
                    enemyMonster.get(i).setRotate(90);
                    enemyMonster.get(i).setImage(Util.getImage(otherPlayer.getBoard().getFieldCardsForMonsters().get(i).getCardName()));
                } else if (otherPlayer.getBoard().getFieldCardsForMonsters().get(i).getCardStatus() == CardStatus.SET) {
                    enemyMonster.get(i).setRotate(90);
                    enemyMonster.get(i).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
                }
                if (otherPlayer.getBoard().getFieldCardsForSpellTraps().get(i) != null)
                    enemySpell.get(i).setImage(new Image(getClass().getResourceAsStream("/PNG/Cards/Monsters/Unknown.jpg")));
                else enemySpell.get(i).setImage(null);
            }
            RoundController.checkEndOfRound();

        }));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFrom(Duration.millis(1000));


    }

    private void updateBoard(ArrayList<ImageView> currentHand, ArrayList<ImageView> enemyHand, ArrayList<ImageView> currentMonster) {


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
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            enemyMonster.get(i).setOnMouseClicked(event -> {
                Card card = otherPlayer.getBoard().getFieldCardsForMonsters().get(finalI);

                if (isAttackActive) detectEnemyToAttack(finalI);
                else if (card != null && !card.getCardStatus().equals(CardStatus.SET)) setViewForSelected(card);

            });
            currentMonster.get(i).setOnMouseClicked(event -> {
                tribute.set(finalI);
                Card card = Player.thePlayer.getBoard().getFieldCardsForMonsters().get(finalI);
                if (event.getButton() == MouseButton.SECONDARY && card.getCardStatus().equals(CardStatus.SET))
                    flipSummon();
                else if (event.getButton() == MouseButton.SECONDARY && card.getCardStatus().equals(CardStatus.ATTACK))
                    setPosition("defence");
                else if (event.getButton() == MouseButton.SECONDARY && card.getCardStatus().equals(CardStatus.DEFENCE))
                    setPosition("attack");
                if (card != null) setViewForSelected(card);
                if (!isOneTributeActive && !isTwoTributeActive) setButtonsForMonster();
            });
            currentSpell.get(i).setOnMouseClicked(event -> {
                Card card = Player.thePlayer.getBoard().getFieldCardsForSpellTraps().get(finalI);
                if (card != null) setViewForSelected(card);
                if (!isOneTributeActive && !isTwoTributeActive) setButtonsForMonster();
            });
            enemySpell.get(i).setOnMouseClicked(event -> {
                Card card = otherPlayer.getBoard().getFieldCardsForSpellTraps().get(finalI);
                if (card != null) setViewForSelected(card);

            });
        }

        hand1.setOnMouseClicked(event -> {
            Card card = Player.thePlayer.getBoard().getHand().get(0);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand2.setOnMouseClicked(event -> {
            Card card = Player.thePlayer.getBoard().getHand().get(1);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand3.setOnMouseClicked(event -> {
            Card card = Player.thePlayer.getBoard().getHand().get(2);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand4.setOnMouseClicked(event -> {
            Card card = Player.thePlayer.getBoard().getHand().get(3);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand5.setOnMouseClicked(event -> {
            Card card = Player.thePlayer.getBoard().getHand().get(4);
            setViewForSelected(card);
            setButtonsForHand();
        });
        hand6.setOnMouseClicked(event -> {
            Card card = Player.thePlayer.getBoard().getHand().get(5);
            setViewForSelected(card);
            setButtonsForHand();
        });
    }

    private void setViewForSelected(Card card) {
        updateBoard(currentHand, enemyHand, currentMonster);
        cardShow.setImage(Util.getImage(card.getCardName()));
        if (card.getCardCategory().equals(CardCategory.MONSTER) ||
                card.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
            attack.setText(Integer.toString(card.getAttack()));
            defence.setText(Integer.toString(card.getAttack()));
        } else {
            attack.setText("---");
            defence.setText("---");
        }
        description.setText(card.getDescription());
        GameController.selectedCard = card;
    }

    private void setButtonsForHand() {
        setAction("hand");
        button1.setImage(new Image(getClass().getResourceAsStream("/PNG/Summon.png")));
        button2.setImage(new Image(getClass().getResourceAsStream("/PNG/set.png")));
        button3.setImage(new Image(getClass().getResourceAsStream("/PNG/active.png")));
        button4.setImage(new Image(getClass().getResourceAsStream("/PNG/nextPhase.png")));
    }

    private void setButtonsForMonster() {
        setAction("monster");
        button1.setImage(new Image(getClass().getResourceAsStream("/PNG/attack.png")));
        button2.setImage(new Image(getClass().getResourceAsStream("/PNG/direct.png")));
        button3.setImage(new Image(getClass().getResourceAsStream("/PNG/active.png")));
        button4.setImage(new Image(getClass().getResourceAsStream("/PNG/nextPhase.png")));
    }


    private void callButton1() {
        if (Player.currentPlayer.getUsername().equals(Player.thePlayer.getUsername())) {
            MainMenu.playSound(Util.CLICK_MUSIC);
            if (isHandSelected) summonMonster();
            else if (isOneTributeActive) callTributeOne();
            else if (isMonsterSelected) attackToMonster();
            updateBoard(currentHand, enemyHand, currentMonster);
        } else showError("It's not your turn!");
    }

    private void callButton2() {
        if (Player.currentPlayer.getUsername().equals(Player.thePlayer.getUsername())) {
            getPlayer(Lobby.playerName2);
            MainMenu.playSound(Util.CLICK_MUSIC);
            if (isHandSelected) setCard();
            if (isOneTributeActive) cancel();
            else if (isMonsterSelected) attackDirect();
            updateBoard(currentHand, enemyHand, currentMonster);
        } else showError("It's not your turn!");

    }

    private void callButton3() {
        if (Player.currentPlayer.getUsername().equals(Player.thePlayer.getUsername())) {
            MainMenu.playSound(Util.CLICK_MUSIC);
            if (isMonsterSelected || isHandSelected) activeSpell();
            updateBoard(currentHand, enemyHand, currentMonster);
        } else showError("It's not your turn!");

    }


    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        CardMenu.showSelectedCard(Util.getCommand(input, "card show --selected"));
        selectCard(Util.getCommand(input, "select --(\\S+)( --opponent)? (\\d+)"));
        showGraveyard(Util.getCommand(input, "show graveyard"));
        deSelectCard(Util.getCommand(input, "select -d"));
        showBoard(Util.getCommand(input, "showBoard"));
        cancel(Util.getCommand(input, "cancel"));
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
        System.out.println("now it will be " + Player.opponent.getUsername() + "???s turn");
    }

    public void changePhaseInMiddle() {
        showError("do you want to active your spell or trap?(yes or no)");
        setAction("trap");
        button1.setImage(new Image(getClass().getResourceAsStream("/PNG/Yes.png")));
        button2.setImage(new Image(getClass().getResourceAsStream("/PNG/No.png")));
        button3.setImage(null);
        button4.setImage(null);
    }

    public void informEndOfGame(Player winner, int score) {
        massage = new Text();
        showError(winner.getUsername() + " won the whole match with score: " + score + " - 0\n\n");
    }

    public void informEndOfRound(Player winner, int score, int remainingRounds) {
        massage = new Text();
        showError("Round " + remainingRounds + " ended");
        showError(winner.getUsername() + " won the game with score: " + score + " - 0");
        remainingRounds--;
        if (remainingRounds != 1)
            showError("Now is time for round " + remainingRounds + "\n\n");
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


    private void activeSpell() {
        if (GameController.selectedCard == null) showError("no card is selected yet");
        else if (Player.currentPlayer.isInOpponentPhase()) {
            if (!GameController.selectedCard.getCardCategory().equals(CardCategory.SPELL) &&
                    !GameController.selectedCard.getCardCategory().equals(CardCategory.TRAP))
                showError("activate effect is only for spell cards.");
                //other things needed for if blow
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.BACK) &&
                    !GameController.selectedCard.getCardStatus().equals(CardStatus.HAND))
                showError("you can't active this card");
            else {
                if (GameController.selectedCard.getCardCategory().equals(CardCategory.TRAP))
                    showError("trap activated");
                showError("spell activated");
                GameController.activeSpell();
                GameController.getBackFromMiddleChange();
            }
        } else {
            if (!GameController.selectedCard.getCardCategory().equals(CardCategory.SPELL) &&
                    !GameController.selectedCard.getCardCategory().equals(CardCategory.TRAP))
                showError("activate effect is only for spell cards.");
            else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                    !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
                showError("you can???t activate an effect on this turn");
            else if (GameController.selectedCard.isActivated())
                showError("you have already activated this card");
            else if (GameController.isSpellTrapFieldFull() //&& !isForFieldZone
            ) showError("spell card zone is full");
                //else if( !isActivable) System.out.println("preparations of this spell are not done yet");
            else {
                showError("spell activated");
                GameController.activeSpell();
            }
        }
    }

    private void summonMonster() {
        if (Player.currentPlayer.isInOpponentPhase())
            showError("it???s not your turn to play this kind of moves");
        else if (GameController.selectedCard == null) showError("no card is selected yet");
        else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.HAND) ||
                (!GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) &&
                        !GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)))
            showError("you can???t summon this card");
        else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
            showError("action not allowed in this phase");
        else if (GameController.isMonsterFieldFull()) showError("monster card zone is full");
        else if (RoundController.isSummoned) showError("you already summoned/set on this turn");
        else if (RoundController.isRoundFreeze) {
            showError("you can't summon because of time seal trap");
            RoundController.isRoundFreeze = false;
        } else if (GameController.selectedCard.getLevel() > 4 && GameController.selectedCard.getLevel() < 7) {
            if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().size() == 0) {
                showError("there are not enough cards for tribute");
                return;
            }
            tributeOne();
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
            if (command != -1) {
                showError("summoned successfully2");
                MainMenu.playSound("src/main/resources/music/summon.wav");
                sendMonster();
            }
        } else {
            int command = GameController.summonMonster(-1, -1);
            if (command != -1) showError("summoned successfully3");
            MainMenu.playSound("src/main/resources/music/summon.wav");
            sendMonster();

        }
    }

    public void setAction(String act) {
        isHandSelected = false;
        isSpellSelected = false;
        isOneTributeActive = false;
        isTwoTributeActive = false;
        isMonsterSelected = false;
        isAttackActive = false;
        isTrapActive = false;
        if (act.equals("oneTribute")) isOneTributeActive = true;
        if (act.equals("twoTribute")) isTwoTributeActive = true;
        if (act.equals("hand")) isHandSelected = true;
        if (act.equals("monster")) isMonsterSelected = true;
        if (act.equals("spell")) isSpellSelected = true;
        if (act.equals("attack")) isAttackActive = true;
        if (act.equals("trap")) isTrapActive = true;

    }

    private void tributeOne() {
        showError("Pick Monster for tribute");
        setAction("oneTribute");
        button1.setImage(new Image(getClass().getResourceAsStream("/PNG/tribute.png")));
        button2.setImage(new Image(getClass().getResourceAsStream("/PNG/Cancel.png")));
        button3.setImage(null);
        button4.setImage(null);
    }

    private void callTributeOne() {
        if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute.get()) == null) {
            showError("there are no monsters on this address");
            return;
        }
        int command = GameController.summonMonster(tribute.get(), -1);
        if (command != -1) showError("summoned successfully");
        setButtonsForHand();
        //GameController.showBoard();
    }

    private void cancel() {
        GameController.selectedCard = null;
        showError("tribute canceled");
        setButtonsForHand();
    }


    private void flipSummon() {

        if (Player.currentPlayer.isInOpponentPhase())
            showError("it???s not your turn to play this kind of moves");
        else if (GameController.selectedCard == null) showError("no card is selected yet");
        else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK) &&
                !GameController.selectedCard.getCardStatus().equals(CardStatus.DEFENCE) &&
                !GameController.selectedCard.getCardStatus().equals(CardStatus.SET))
            showError("you can???t change this card position");
        else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
            showError("action not allowed in this phase");
        else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.SET) ||
                GameController.selectedCard.isSummoned())
            showError("you can???t flip summon this card");
        else {
            GameController.flipSummon();
            sendMonster();
            showError("flip summoned successfully");
        }

    }

    private void setCard() {
        if (Player.currentPlayer.isInOpponentPhase())
            showError("it???s not your turn to play this kind of moves");
        else if (GameController.selectedCard == null) showError("no card is selected yet");
        else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.HAND))
            showError("you can???t set this card");
        else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
            showError("action not allowed in this phase");
        else if (GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) ||
                GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)) {
            if (GameController.isMonsterFieldFull()) showError("monster card zone is full");
            else if (RoundController.isSummoned) showError("you already summoned/set on this turn");
            else {
                GameController.setMonster();
                sendMonster();
                showError("set successfully1");
            }
        } else {
            if (GameController.isSpellTrapFieldFull()) showError("spell card zone is full");
            else {
                GameController.setSpell();
                sendSpell();
                showError("set successfully2");
            }
        }
    }

    private void setPosition(String position) {
        if (Player.currentPlayer.isInOpponentPhase())
            showError("it???s not your turn to play this kind of moves");
        else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
        else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK) &&
                !GameController.selectedCard.getCardStatus().equals(CardStatus.DEFENCE))
            showError("you can???t change this card position");
        else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
            showError("action not allowed in this phase");
        else if (GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK) && position.equals("attack"))
            showError("this card is already in the wanted position");
        else if (GameController.selectedCard.getCardStatus().equals(CardStatus.DEFENCE) && position.equals("defence"))
            showError("this card is already in the wanted position");
        else if (GameController.selectedCard.isChanged())
            showError("you already changed this card position in this turn");
        else {
            GameController.changeCardPosition(position);
            sendMonster();
            showError("monster card position changed successfully");
        }
    }

    private void attackToMonster() {
        if (Player.currentPlayer.isInOpponentPhase())
            showError("it???s not your turn to play this kind of moves");
        else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
        else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK))
            showError("you can???t attack with this card");
        else if (!Player.currentPlayer.getPhase().equals(Phase.BATTLE))
            showError("you can???t do this action in this phase");
        else if (GameController.selectedCard.isAttacked())
            showError("this card already attacked");
        else {
            showError("select enemy card to attack");
            setAction("attack");
        }

    }

    private void detectEnemyToAttack(int enemyMonsterIndex) {
        if (Player.opponent.getBoard().getFieldCardsForMonsters().get(enemyMonsterIndex) == null)
            showError("there is no card to attack here");
        else {

            GameController.attackMonster(enemyMonsterIndex);
            sendAnimation();
            explosion();
            MainMenu.playSound("src/main/resources/music/attack.wav");
            updateBoard(currentHand, enemyHand, currentMonster);
            sendMonster();
            sendLp();
            sendGraveyard1();
            sendGraveyard2();

        }
    }

    private void explosion() {
        explosion.toFront();
        ScaleTransition trans = new ScaleTransition();
        trans.setDuration(Duration.millis(600));
        trans.setFromX(1);
        trans.setFromY(1);
        trans.setToX(0);
        trans.setToY(0);
        trans.setAutoReverse(true);
        trans.setNode(explosion);
        trans.play();
    }

    public void printMonsterAttacks(int key, int damage, int enemyMonsterIndex) {
        if (key == 1) System.out.println("your opponent???s monster is destroyed and your" +
                " opponent receives " + damage + " battle damage");
        else if (key == 2) System.out.println("both you and your opponent monster cards are destroyed and no\n" +
                "one receives damage");
        else if (key == 3) System.out.println("Your monster card is destroyed and you received" +
                " opponent receives " + damage + " battle damage");
        else if (key == 4) System.out.println("the defense position monster is destroyed");
        else if (key == 5) System.out.println("no card is destroyed");
        else if (key == 6) System.out.println("no card is destroyed and you received " + damage + " battle damage");
        else if (key == 7) System.out.println("opponent???s monster card was " +
                Player.opponent.getBoard().getFieldCardsForMonsters().get(enemyMonsterIndex).getCardName() +
                " and the defense position monster is destroyed");
        else if (key == 8) System.out.println("opponent???s monster card was " +
                Player.opponent.getBoard().getFieldCardsForMonsters().get(enemyMonsterIndex).getCardName() +
                " and no card is destroyed");
        else if (key == 9) System.out.println("opponent???s monster card was " +
                Player.opponent.getBoard().getFieldCardsForMonsters().get(enemyMonsterIndex).getCardName() +
                " and no card is destroyed and you received " + damage + " battle damage");

    }

    private void attackDirect() {

        if (Player.currentPlayer.isInOpponentPhase())
            showError("it???s not your turn to play this kind of moves");
        else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
        else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK))
            showError("you can???t attack with this card");
        else if (!Player.currentPlayer.getPhase().equals(Phase.BATTLE))
            showError("you can???t do this action in this phase");
        else if (GameController.selectedCard.isAttacked())
            showError("this card already attacked");
        else if (!GameController.isEnemyMonsterFieldEmpty())
            showError("you can???t attack the opponent directly");
        else {
            int damage = GameController.attackDirect();
            if (damage != -1) showError("your opponent receives " + damage + " battle damage");
            MainMenu.playSound("src/main/resources/music/attack.wav");
            sendMonster();
            sendLp();
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

    private void surrender() {
        Player.currentPlayer.setLifePoint(0);

        sendLp();
    }

    private void increaseMoney(Matcher matcher) {
        if (matcher.matches()) {
            MainMenu.checked = true;
            Player.currentPlayer.increaseMoney(Integer.parseInt(matcher.group(1)));
        }
    }

    private void increaseLifePoint(Matcher matcher) {
        if (matcher.matches()) {
            MainMenu.checked = true;
            Player.currentPlayer.increaseLifePoint(Integer.parseInt(matcher.group(1)));
        }
    }

    private void winTheGame(Matcher matcher) {
        if (matcher.matches()) {

            if (matcher.group(1).equals(Player.currentPlayer.getNickname())) {
                Player.opponent.setLifePoint(0);
            } else if (matcher.group(1).equals(Player.opponent.getNickname())) {
                Player.currentPlayer.setLifePoint(0);
            } else Communicate.output("invalid name");
        }
    }


    public void showError(String error) {
        massage.setText(error);
    }

    public ArrayList<Card> getOtherPlayerHand(String where) {
        String result = "";
        ArrayList<Card> handList = new ArrayList<>();
        try {
            ProgramController.dataOutputStream.writeUTF(where + " " + Lobby.playerName2);
            ProgramController.dataOutputStream.flush();
            result = ProgramController.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String[] parts = result.split(", ");

        for (int i = 0; i < parts.length; i++) {
            String s = parts[i].replaceAll("]", "").replaceAll("\\[", "");
            handList.add(Card.getCardByName(s));
        }
        return handList;
    }

    public static void sendPlayer(Player player, String username) {
        try {
            ProgramController.dataOutputStream.writeUTF("sendPlayer " + username);
            //ProgramController.objectOutputStream.writeObject(player);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendMonster() {
        sendMonsters(Player.thePlayer);
        sendMonsters(otherPlayer);
    }

    public static void sendMonsters(Player player) {
        try {
            ArrayList<String> monsterList = new ArrayList<>();
            ArrayList<String> monsterPositions = new ArrayList<>();
            for (int i = 0; i < 5; i++) {
                monsterList.add(null);
                if (player.getBoard().getFieldCardsForMonsters().get(i) != null)
                    monsterList.set(i, player.getBoard().getFieldCardsForMonsters().get(i).getCardName());
                else monsterList.set(i, null);
            }
            for (int i = 0; i < 5; i++) {
                monsterPositions.add(null);
                if (player.getBoard().getFieldCardsForMonsters().get(i) != null)
                    monsterPositions.set(i, player.getBoard().getFieldCardsForMonsters().get(i).getCardStatus().toString());
                else monsterPositions.set(i, null);
            }

            ProgramController.dataOutputStream.writeUTF("sendMonster " + player.getUsername()
                    + " " + monsterList.toString());
            ProgramController.dataOutputStream.flush();
            ProgramController.dataInputStream.readUTF();
            ProgramController.dataOutputStream.writeUTF("sendPosition " + player.getUsername()
                    + " " + monsterPositions.toString());
            ProgramController.dataOutputStream.flush();
            ProgramController.dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendSpell() {
        try {
            ArrayList<String> spellList = new ArrayList<>();

            for (int i = 0; i < 5; i++) {
                spellList.add(null);
                if (Player.thePlayer.getBoard().getFieldCardsForSpellTraps().get(i) != null)
                    spellList.set(i, Player.thePlayer.getBoard().getFieldCardsForSpellTraps().get(i).getCardName());
                else spellList.set(i, null);
            }
            ProgramController.dataOutputStream.writeUTF("sendSpell " + Player.thePlayer.getUsername()
                    + " " + spellList.toString());
            ProgramController.dataOutputStream.flush();
            ProgramController.dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendPhase(String phase) {
        try {
            ProgramController.dataOutputStream.writeUTF("sendPhase " + phase);
            ProgramController.dataOutputStream.flush();
            ProgramController.dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendLp() {
        sendLps(Player.thePlayer);
        sendLps(otherPlayer);
    }

    public static void sendLps(Player player) {
        try {
            ProgramController.dataOutputStream.writeUTF("sendLp " + player.getLifePoint() + " " +
                    player.getUsername());
            ProgramController.dataOutputStream.flush();
            ProgramController.dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendGraveyard1() {
        try {
            ArrayList<String> graveyardList1 = new ArrayList<>();

            for (int i = 0; i < Player.thePlayer.getBoard().getGraveyard().size(); i++) {
                if (Player.thePlayer.getBoard().getGraveyard().get(i) != null)
                    graveyardList1.add(i, Player.thePlayer.getBoard().getGraveyard().get(i).getCardName());
                else graveyardList1.add(i, null);
            }

            ProgramController.dataOutputStream.writeUTF("sendGraveyard1 " + Player.thePlayer.getUsername()
                    + " " + graveyardList1.toString());
            ProgramController.dataOutputStream.flush();
            ProgramController.dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendGraveyard2() {
        try {
            ArrayList<String> graveyardList2 = new ArrayList<>();

            for (int i = 0; i < Player.opponent.getBoard().getGraveyard().size(); i++) {
                if (Player.opponent.getBoard().getGraveyard().get(i) != null)
                    graveyardList2.add(i, Player.opponent.getBoard().getGraveyard().get(i).getCardName());
                else graveyardList2.add(i, null);
            }

            ProgramController.dataOutputStream.writeUTF("sendGraveyard2 " + Player.opponent.getUsername()
                    + " " + graveyardList2.toString());
            ProgramController.dataOutputStream.flush();
            ProgramController.dataInputStream.readUTF();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void sendAnimation() {
        try {
            ProgramController.dataOutputStream.writeUTF("sendAnimation");
            ProgramController.dataOutputStream.flush();
            ProgramController.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getAnimation() {
        try {
            ProgramController.dataOutputStream.writeUTF("getAnimation");
            ProgramController.dataOutputStream.flush();
            String result = ProgramController.dataInputStream.readUTF();
            if (result.equals("1")) {
                explosion();
                MainMenu.playSound("src/main/resources/music/attack.wav");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getGraveyard1() {
        try {
            ProgramController.dataOutputStream.writeUTF("getGraveyard1 " + Player.thePlayer.getUsername());
            ProgramController.dataOutputStream.flush();
            String graveyardList = ProgramController.dataInputStream.readUTF();
            String[] parts = graveyardList.split(", ");
            Player.thePlayer.getBoard().getGraveyard().clear();
            for (int i = 0; i < parts.length; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll("\\[", "");
                if (!s.equals("") && !s.equals("null"))
                    Player.thePlayer.getBoard().getGraveyard().add(i, Card.getCardByName(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getGraveyard2() {
        try {
            ProgramController.dataOutputStream.writeUTF("getGraveyard2 " + Lobby.playerName2);
            ProgramController.dataOutputStream.flush();
            String graveyardList = ProgramController.dataInputStream.readUTF();
            String[] parts = graveyardList.split(", ");
            otherPlayer.getBoard().getGraveyard().clear();
            for (int i = 0; i < parts.length; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll("\\[", "");
                if (!s.equals("") && !s.equals("null"))
                    otherPlayer.getBoard().getGraveyard().add(i, Card.getCardByName(s));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getLp() {
        getLps(Player.thePlayer);
        getLps(otherPlayer);
    }

    public static void getLps(Player player) {
        try {
            ProgramController.dataOutputStream.writeUTF("getLp " + player.getUsername());
            ProgramController.dataOutputStream.flush();
            String lp = ProgramController.dataInputStream.readUTF();
            player.setLifePoint(Integer.parseInt(lp));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getSpell() {
        try {
            ProgramController.dataOutputStream.writeUTF("getSpell " + Lobby.playerName2);
            ProgramController.dataOutputStream.flush();
            String spellList = ProgramController.dataInputStream.readUTF();
            String[] parts = spellList.split(", ");
            for (int i = 0; i < parts.length; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll("\\[", "");
                otherPlayer.getBoard().getFieldCardsForSpellTraps().set(i, Card.getCardByName(s));
                if (otherPlayer.getBoard().getFieldCardsForSpellTraps().get(i) != null) {
                    otherPlayer.getBoard().getFieldCardsForSpellTraps().get(i).setCardStatus(CardStatus.BACK);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getMonster() {
        getMonsters(Player.thePlayer);
        getMonsters(otherPlayer);
    }

    public static void getMonsters(Player player) {
        try {
            ProgramController.dataOutputStream.writeUTF("getMonster " + player.getUsername());
            ProgramController.dataOutputStream.flush();
            String monsterList = ProgramController.dataInputStream.readUTF();
            String[] parts = monsterList.split(", ");
            ProgramController.dataOutputStream.writeUTF("getPosition " + player.getUsername());
            ProgramController.dataOutputStream.flush();
            String monsterPosition = ProgramController.dataInputStream.readUTF();
            String[] parts1 = monsterPosition.split(", ");

            for (int i = 0; i < parts.length; i++) {
                String s = parts[i].replaceAll("]", "").replaceAll("\\[", "");
                String s1 = parts1[i].replaceAll("]", "").replaceAll("\\[", "");
                player.getBoard().getFieldCardsForMonsters().set(i, Card.getCardByName(s));
                if (player.getBoard().getFieldCardsForMonsters().get(i) != null && s1.equals("ATTACK")) {
                    player.getBoard().getFieldCardsForMonsters().get(i).setCardStatus(CardStatus.ATTACK);
                } else if (player.getBoard().getFieldCardsForMonsters().get(i) != null && s1.equals("DEFENCE")) {
                    player.getBoard().getFieldCardsForMonsters().get(i).setCardStatus(CardStatus.DEFENCE);
                } else if (player.getBoard().getFieldCardsForMonsters().get(i) != null && s1.equals("SET")) {
                    player.getBoard().getFieldCardsForMonsters().get(i).setCardStatus(CardStatus.SET);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getPhase() {
        try {
            ProgramController.dataOutputStream.writeUTF("getPhase");
            ProgramController.dataOutputStream.flush();
            String p = ProgramController.dataInputStream.readUTF();

            if (p.equals("battle") && !Player.thePlayer.getPhase().equals(Phase.BATTLE)) {
                RoundController.battlePhase();
                phase.setImage(new Image(getClass().getResourceAsStream("/PNG/battle.png")));
                animatePhase();
            } else if (p.equals("main2") && !Player.thePlayer.getPhase().equals(Phase.MAIN2)) {
                RoundController.mainPhase2();
                phase.setImage(new Image(getClass().getResourceAsStream("/PNG/main.png")));
                animatePhase();
            } else if (p.equals("end") && !Player.thePlayer.getPhase().equals(Phase.END) &&
                    !Player.thePlayer.getPhase().equals(Phase.DRAW) &&
                    !Player.thePlayer.getPhase().equals(Phase.MAIN1)) {
                sendPhase("main1");
                phase.setImage(new Image(getClass().getResourceAsStream("/PNG/end.png")));
                animatePhase();
                RoundController.endPhase();
                updateBoard(currentHand, enemyHand, currentMonster);
                animateNextPhase("draw");
                goToNextPhase();

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void getPlayer(String name) {
        /*try {
            ProgramController.dataOutputStream.writeUTF("getPlayer " + name);
            String input = ProgramController.dataInputStream.readUTF();
            try {
                //otherPlayer = (Player) ProgramController.objectInputStream.readObject();
                //System.out.println(otherPlayer.getUsername());

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }*/

    }

}
