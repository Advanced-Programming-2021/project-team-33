package View;

import Controller.GameController;
import Controller.ProgramController;
import Controller.RoundController;
import Controller.Util;
import Model.*;

import java.util.Random;
import java.util.regex.Matcher;

public class GameMenu {
    boolean checked = false;

    public void run(String input) {
        checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        CardMenu.showCard(Util.getCommand(input, "card show (.+)"));
        summonMonster(Util.getCommand(input, "summon"));
        selectCard(Util.getCommand(input, "select --(\\S+)( --\\D)* (\\d+)"));
        deSelectCard(Util.getCommand(input, "select -d"));
        activeSpell(Util.getCommand(input, "activate effect"));
        showBoard(Util.getCommand(input, "showBoard"));
    }

    private void showBoard(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
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
        if (!checked && matcher.matches()) {
            checked = true;
            // GameController.selectedCard.run() ????
        }
    }

    private void summonMonster(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
            if (GameController.selectedCard == null) {
                System.out.println("no card is selected yet");
                return;
            }
            boolean isSummonPossible = GameController.isSummonPossible();
            if (!GameController.selectedCard.getCardStatus().equals(CardStatus.HAND) ||
                    (!GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTER) &&
                            !GameController.selectedCard.getCardCategory().equals(CardCategory.MONSTEREFFECT)))
                System.out.println("you can’t summon this card");
            else if (!Player.currentPlayer.getPhase().equals(Phase.MAIN1) &&
                    !Player.currentPlayer.getPhase().equals(Phase.MAIN2))
                System.out.println("action not allowed in this phase");
            else if (Player.currentPlayer.getBoard().getFieldCardsForMonsters().size() > 5) // E in board must be Null
                System.out.println("monster card zone is full");
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

    public void selectCard(Matcher matcher) {
        if (!checked && matcher.matches()) {
            checked = true;
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
        if (!checked && matcher.matches()) {
            checked = true;
            if (GameController.selectedCard == null) System.out.println("no card is selected yet");
            else {
                System.out.println("card deselected");
                GameController.deSelectCard();
            }
        }
    }

}
