package Main;

import Controller.ChatroomContoller;
import Controller.GameController;
import Controller.RegisterController;
import Controller.ScoreboardController;
import Model.Player;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {


    public static void main(String[] args) {
        runApp();
    }


    private static void runApp() {
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
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();
                ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());
                getInputAndProcess(dataInputStream, dataOutputStream, objectInputStream, objectOutputStream);
                dataInputStream.close();
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    static Player pl1 = GameController.player1;
    static Player pl2 = GameController.player2;

    private static void getInputAndProcess(DataInputStream dataInputStream, DataOutputStream dataOutputStream,
                                           ObjectInputStream objectInputStream, ObjectOutputStream objectOutputStream) throws IOException {

        while (true) {
            String input = dataInputStream.readUTF();
            if (input.equals("sendPlayer")) {
                try {
                    Player holder = (Player) objectInputStream.readObject();
                    if(GameController.player1.getUsername().equals(holder.getUsername())) pl1 = holder;
                    if(GameController.player2.getUsername().equals(holder.getUsername())) pl2 = holder;
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            if (input.startsWith("getPlayer")) {
                String[] parts = input.split(" ");
                if(parts[1].equals(GameController.player1.getUsername())) objectOutputStream.writeObject(pl1);
                else if(parts[1].equals(GameController.player2.getUsername())) objectOutputStream.writeObject(pl2);
            }
            String result = process(input);
            //if (result.equals("")) break;
            dataOutputStream.writeUTF(result);
            dataOutputStream.flush();

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
        }
        return "";
    }
}
