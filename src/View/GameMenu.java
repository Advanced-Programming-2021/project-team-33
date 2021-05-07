package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.RoundController;
import Controller.Util;
import Model.*;

import java.util.Random;
import java.util.regex.Matcher;

public class GameMenu {


    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        CardMenu.showSelectedCard(Util.getCommand(input, "card show --selected"));
        summonMonster(Util.getCommand(input, "summon"));
        flipSummon(Util.getCommand(input, "flip-summon"));
        setMonster(Util.getCommand(input, "set"));
        setPosition(Util.getCommand(input, "set --position ((attack)|(defence))"));
        selectCard(Util.getCommand(input, "select --(\\S+)( --\\D)* (\\d+)"));
        deSelectCard(Util.getCommand(input, "select -d"));
        attackToMonster(Util.getCommand(input, "attack (\\d+)"));
        attackDirect(Util.getCommand(input, "attack direct"));
        activeSpell(Util.getCommand(input, "activate effect"));
        goToNextPhase(Util.getCommand(input, "next phase"));
        showBoard(Util.getCommand(input, "showBoard"));
    }

    private void goToNextPhase(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (Player.currentPlayer.getPhase().equals(Phase.MAIN1)) RoundController.battlePhase();
            else if (Player.currentPlayer.getPhase().equals(Phase.BATTLE)) RoundController.mainPhase2();
            else if (Player.currentPlayer.getPhase().equals(Phase.MAIN2)) {
                RoundController.endPhase();
                System.out.println("It's" + Player.currentPlayer.getUsername() + "'s turn");
                RoundController.drawPhase();
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
            // GameController.selectedCard.run() ????
        }
    }


    private void summonMonster(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.HAND) ||
                    (!GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) &&
                            !GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)))
                System.out.println("you can’t summon this card");
            else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                    !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
                System.out.println("action not allowed in this phase");
            else if (GameController.isMonsterFieldFull()) System.out.println("monster card zone is full");
            else if (RoundController.isSummoned) System.out.println("you already summoned/set on this turn");
            else if (GameController.selectedCard.getLevel() > 4 && GameController.selectedCard.getLevel() < 7) {
                if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().size() == 0) {
                    System.out.println("there are not enough cards for tribute");
                    return;
                }
                int tribute = Integer.parseInt(Communicate.input("Pick Monster for tribute"));
                if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute) == null) {
                    System.out.println("there are no monsters on this address");
                    return;
                }
                GameController.summonMonster(tribute, 0);
                System.out.println("summoned successfully1");
            } else if (GameController.selectedCard.getLevel() > 6 && GameController.selectedCard.getLevel() < 9) {
                if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().size() < 2) {
                    System.out.println("there are not enough cards for tribute");
                    return;
                }
                int tribute = Integer.parseInt(Communicate.input("Pick Monster for tribute"));
                int tribute1 = Integer.parseInt(Communicate.input("Pick another Monster for tribute"));
                if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute) == null ||
                        Player.currentPlayer.getBoard().getFieldCardsForMonsters().get(tribute1) == null) {
                    System.out.println("there is no monster on one of these addresses");
                    return;
                }
                GameController.summonMonster(tribute, tribute1);
                System.out.println("summoned successfully2");
            } else {
                GameController.summonMonster(0, 0);
                System.out.println("summoned successfully3");
            }

        }
    }

    private void flipSummon(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
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

    private void setMonster(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.HAND) ||
                    (!GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) &&
                            !GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)))
                System.out.println("you can’t set this card");
            else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                    !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
                System.out.println("action not allowed in this phase");
            else if (GameController.isMonsterFieldFull()) System.out.println("monster card zone is full");
            else if (RoundController.isSummoned) System.out.println("you already summoned/set on this turn");
            else {
                GameController.setMonster();
                System.out.println("set successfully");
            }
        }
    }

    private void setPosition(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String position = matcher.group(1);
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
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
            int enemyMonsterIndex = Integer.parseInt(matcher.group(1));
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
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
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else if (!GameController.selectedCard.getCardStatus().equals(CardStatus.ATTACK))
                System.out.println("you can’t attack with this card");
            else if (!Player.currentPlayer.getPhase().equals(Phase.BATTLE))
                System.out.println("you can’t do this action in this phase");
            else if (GameController.selectedCard.isAttacked())
                System.out.println("this card already attacked");
            else if (!GameController.isEnemyMonsterFieldEmpty())
                System.out.println("you can’t attack the opponent directly");
            else {
                GameController.attackDirect();
            }
        }
    }

    public void selectCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            String cardPosition = matcher.group(1);
            String opponent = matcher.group(2);
            int number = Integer.parseInt(matcher.group(3));
            int massage = GameController.selectCard(cardPosition, number, opponent);
            if (massage == 1) System.out.println("card selected");
            else if (massage == 0) System.out.println("no card found in the given position");
            else System.out.println("invalid selection");
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

}
