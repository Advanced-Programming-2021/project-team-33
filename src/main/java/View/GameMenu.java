package View;

import Controller.*;
import Model.*;

import java.util.Random;
import java.util.regex.Matcher;

public class GameMenu {


    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        CardMenu.showSelectedCard(Util.getCommand(input, "card show --selected"));
        selectCard(Util.getCommand(input, "select --(\\S+)( --opponent)? (\\d+)"));
        showGraveyard(Util.getCommand(input, "show graveyard"));
        deSelectCard(Util.getCommand(input, "select -d"));
        activeSpell(Util.getCommand(input, "activate effect"));
        summonMonster(Util.getCommand(input, "summon"));
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
        selectCardForce(Util.getCommand(input, "select --hand (\\S+) --force"));
        summonCardForce(Util.getCommand(input, "summon --force"));
        putOnGraveyard(Util.getCommand(input, "graveyard --force"));
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
                if (GameController.selectedCard.getCardName().equals("Herald of Creation") ||
                        GameController.selectedCard.getCardName().equals("The Tricky")) {
                    GameController.activeSpell();
                } else if (!GameController.selectedCard.getCardCategory().equals(CardCategory.SPELL) &&
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


    private void summonMonster(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (Player.currentPlayer.isInOpponentPhase())
                System.out.println("it’s not your turn to play this kind of moves");
            else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.HAND) ||
                    (!GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) &&
                            !GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)) ||
                    GameController.selectedCard.getCardTypes().contains(CardType.RITUAL))
                System.out.println("you can’t summon this card");
            else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                    !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
                System.out.println("action not allowed in this phase");
            else if (GameController.isMonsterFieldFull()) System.out.println("monster card zone is full");
            else if (RoundController.isSummoned) System.out.println("you already summoned/set on this turn");
            else if (GameController.selectedCard.getCardName().equals("Gate Guardian")) GameController.specialSummon();
            else if (GameController.selectedCard.getCardName().equals("Beast King Barbaros")) summonBarbaros();
            else if (GameController.selectedCard.getCardName().equals("The Tricky"))
                System.out.println("you should activate its effect");
            else if (GameController.selectedCard.getLevel() > 4 && GameController.selectedCard.getLevel() < 7) {
                tributeSummonoLowLevel();
            } else if (GameController.selectedCard.getLevel() > 6 && GameController.selectedCard.getLevel() < 9) {
                tributeSummonHighLevel();
            } else {
                int command = GameController.summonMonster(0, 0);
                if (command != -1) System.out.println("summoned successfully3");
            }

        }
    }

    private void tributeSummonoLowLevel() {
        if (GameController.getMonsterFieldSize() == 0) {
            System.out.println("there are not enough cards for tribute");
            return;
        }
        String input = Communicate.input("Pick Monster for tribute");
        if (input.equals("cancel")) {
            System.out.println("Tribute Canceled");
            return;
        }
        int tribute = Integer.parseInt(Communicate.input("Pick Monster for tribute"));
        tribute = GameController.switchNumberForCurrent(tribute);
        if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute) == null) {
            System.out.println("there are no monsters on this address");
            return;
        }
        int command = GameController.summonMonster(tribute, 0);
        if (command != -1) System.out.println("summoned successfully1");
    }

    private void tributeSummonHighLevel() {
        System.out.println(GameController.getMonsterFieldSize());
        if (GameController.getMonsterFieldSize() < 2) {
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
        if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute) == null ||
                Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute1) == null) {
            System.out.println("there is no monster on one of these addresses");
            return;
        }
        int command = GameController.summonMonster(tribute, tribute1);
        if (command != -1) System.out.println("summoned successfully2");
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
            else if (GameController.selectedCard.getLevel() > 4 && GameController.selectedCard.getLevel() < 7) {
                tributeSummonoLowLevel();
            } else if (GameController.selectedCard.getLevel() > 6 && GameController.selectedCard.getLevel() < 9) {
                tributeSummonHighLevel();
            } else {
                System.out.println("flip summoned successfully");
                GameController.flipSummon();
            }
        }
    }

    private void summonBarbaros() {
        String input = Communicate.input("choose your number of tribute between 0,2 and 3");
        if (input.equals("0")) {
            GameController.selectedCard.setAttack(1900);
            GameController.summonMonster(0, 0);
            System.out.println("summoned successfully3");
        } else if (input.equals("2")) {
            tributeSummonHighLevel();
        } else if (input.equals("3")) {
            GameController.specialSummon();
        } else System.out.println("invalid input");
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
            } else if (GameController.selectedCard.getCardName().equals("The Tricky"))
                System.out.println("you should activate its effect");
            else {
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
            else if(GameController.isThreeLightActive && Player.currentPlayer.equals(GameController.getLightPlayer) ||
                    (GameController.is1500Active && GameController.selectedCard.getAttack() > 1500))
                System.out.println("you can't attack because of enemy spell");
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
        else if (key == 3) System.out.println("Your monster card is destroyed and you received " +
                +damage + " battle damage");
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
            System.out.println(GameController.isThreeLightActive);
            System.out.println(Player.currentPlayer.getNickname());
            System.out.println(GameController.getLightPlayer.getNickname());
            System.out.println(GameController.isThreeLightActive);
            if (Player.currentPlayer.isInOpponentPhase())
                System.out.println("it’s not your turn to play this kind of moves");
            else if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK))
                System.out.println("you can’t attack with this card");
            else if (!Player.currentPlayer.getPhase().equals(Phase.BATTLE))
                System.out.println("you can’t do this action in this phase");
            else if (GameController.selectedCard.isAttacked())
                System.out.println("this card already attacked");
            else if(GameController.isThreeLightActive && Player.currentPlayer.equals(GameController.getLightPlayer) ||
                    (GameController.is1500Active && GameController.selectedCard.getAttack() > 1500))
                System.out.println("you can't attack because of enemy spell");
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
            } else if (matcher.group(1).equals(Player.opponent.getNickname())) {
                Player.currentPlayer.setLifePoint(0);
            } else System.out.println("invalid name");
        }
    }

    private void selectCardForce(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            Card card = Card.getCardByName(matcher.group(1));
            if (!Card.getCards().contains(card)) System.out.println("invalid card name");
            else GameController.selectedCard = card;
        }
    }

    private void summonCardForce(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            GameController.selectedCard.setCardStatus(CardStatus.ATTACK);
            GameController.putMonsterOnField();
        }
    }

    private void putOnGraveyard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;

            Player.currentPlayer.getBoard().getGraveyard().add(GameController.selectedCard);
        }
    }

}
