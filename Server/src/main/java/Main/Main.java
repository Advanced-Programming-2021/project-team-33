package Main;

import Controller.*;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        runApp();
    }

    private static void runApp() {
        GameController.initialLists();
        try {
            ServerSocket serverSocket = new ServerSocket(7776);
            while (true) {
                Socket socket = serverSocket.accept();
                startNewThread(serverSocket, socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void startNewThread(ServerSocket serverSocket, Socket socket) {
        new Thread(() -> {
            try {
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                getInputAndProcess(dataInputStream, dataOutputStream);
                dataInputStream.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void getInputAndProcess(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {

        while (true) {

            //if (!dataInputStream.readUTF().equals("close")){
                try{
                    String input = dataInputStream.readUTF();
                    String result = process(input);
                    dataOutputStream.writeUTF(result);
                    dataOutputStream.flush();
                } catch (EOFException e){
                    dataInputStream.close();
                    dataOutputStream.close();
                    System.out.println("Server closed successfully!");
                    break;
                }

            //} else return;


        }
    }

    static String process(String command) {
        String[] parts = command.split(" ");
        if (command.startsWith("register")) {
            return String.valueOf(RegisterController.register(parts[1], parts[2], parts[3]));
        } else if (command.startsWith("login")) {
            return String.valueOf(RegisterController.login(parts[1], parts[2]));
        } else if (command.startsWith("scoreboard")) {
            return ScoreboardController.setScoreBoardList();
        } else if (command.startsWith("sendMessage")) {
            return ChatroomContoller.sendMessage(command.substring(12));
        } else if (command.startsWith("getMessages")) {
            return ChatroomContoller.getMessages();
        } else if (command.startsWith("logout")) {
            return RegisterController.logout(parts[1]);
        } else if (command.startsWith("multiplayer")) {
            return GameController.setMultiPlayer(parts[1]);
        } else if (command.startsWith("ready")) {
            return GameController.isOpponentReady(parts[1], parts[2]);
        } else if (command.startsWith("player1")) {
            return GameController.getPlayer1();
        } else if (command.startsWith("player2")) {
            return GameController.getPlayer2();
        } else if (command.startsWith("getDeck")) {
            return GameController.getDeck(parts[1]);
        } else if (command.startsWith("createDeck")) {
            GameController.createDeck(parts[1], parts[2]);
        } else if (command.startsWith("deleteDeck")) {
            GameController.deleteDeck(parts[1], parts[2]);
        } else if (command.startsWith("activeDeck")) {
            GameController.activateDeck(parts[1], parts[2]);
        } else if (command.startsWith("addCard")) {
            try {
                GameController.addCardToDeck(parts[1], parts[2]);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        } else if (command.startsWith("addCardSide")) {
            try {
                GameController.addCardSideToDeck(parts[1], parts[2]);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        } else if (command.startsWith("profileId")) {
            return String.valueOf(GameController.getProfileId(parts[1]));
        } else if (command.startsWith("sendMonster")) {
            GameController.getMonster(parts[1], command);
        } else if (command.startsWith("getMonster")) {
            return GameController.sendMonster(parts[1]);
        } else if (command.startsWith("sendPosition")) {
            GameController.getPosition(parts[1], command);
        } else if (command.startsWith("getPosition")) {
            return GameController.sendPosition(parts[1]);
        } else if (command.startsWith("resetList")) {
            GameController.resetList();
        } else if (command.startsWith("sendSpell")) {
            GameController.getSpell(parts[1], command);
        } else if (command.startsWith("getSpell")) {
            return GameController.sendSpell(parts[1]);
        } else if (command.startsWith("sendPhase")) {
            GameController.getPhase(parts[1]);
        } else if (command.startsWith("getPhase")) {
            return GameController.sendPhase();
        } else if (command.startsWith("sendLp")) {
            GameController.getLp(parts[1], parts[2]);
        } else if (command.startsWith("getLp")) {
            return GameController.sendLp(parts[1]);
        } else if (command.startsWith("sendGraveyard1")) {
            GameController.getGraveyard(parts[1], command);
        } else if (command.startsWith("sendGraveyard2")) {
            GameController.getGraveyard(parts[1], command);
        } else if (command.startsWith("getGraveyard1")) {
            return GameController.sendGraveyard(parts[1]);
        } else if (command.startsWith("getGraveyard2")) {
            return GameController.sendGraveyard(parts[1]);
        } else if (command.startsWith("sendAnimation")) {
            GameController.getAnimation();
        } else if (command.startsWith("getAnimation")) {
            return GameController.sendAnimation();
        } else if (command.startsWith("setScore")) {
            ProgramController.setScore(parts[1], parts[2]);
        } else if (command.startsWith("cancel")) {
            GameController.cancelMultiPlayer();
        } else if (command.startsWith("cardCount")) {
            return ShopController.getNumberOfCards(command.replaceAll("cardCount ", ""));
        } else if (command.startsWith("reduceCard")) {
            ShopController.reduceCard(command.replaceAll("reduceCard ", ""));
        } else if (command.startsWith("adminCheck")) {
            return ShopController.checkAdmin(parts[1]);
        } else if (command.startsWith("addToShop")) {
            ShopController.addCard(command.replaceAll("addToShop ", ""));
        } else if (command.startsWith("forbidCard")) {
            ShopController.forbidCard(command.replaceAll("forbidCard ", ""));
        } else if (command.startsWith("permitCard")) {
            ShopController.permitCard(command.replaceAll("permitCard ", ""));
        } else if (command.startsWith("getForbiddenCard")) {
            return ShopController.getForbiddenCard(command.replaceAll("getForbiddenCard ", ""));
        }
        return "";
    }
}
