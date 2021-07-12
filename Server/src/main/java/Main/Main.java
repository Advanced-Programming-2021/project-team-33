package Main;

import Controller.ChatroomContoller;
import Controller.RegisterController;
import Controller.ScoreboardController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {

    public static void main(String[] args) {
        runApp();
    }

    private static void runApp() {
        try {
            ServerSocket serverSocket = new ServerSocket(7777);
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
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    private static void getInputAndProcess(DataInputStream dataInputStream, DataOutputStream dataOutputStream) throws IOException {
        while (true) {
            String input = dataInputStream.readUTF();
            String result = process(input);
            if (result.equals("")) break;
            dataOutputStream.writeUTF(result);
            dataOutputStream.flush();
        }
    }

    static String process(String command) {
        String[] parts = command.split(" ");
        if (command.startsWith("register")) {
            return String.valueOf(RegisterController.register(parts[1], parts[2], parts[3]));
        }
        else if (command.startsWith("login")) {
            return String.valueOf(RegisterController.login(parts[1], parts[2]));
        }
        else if (command.startsWith("scoreboard")) {
            return ScoreboardController.setScoreBoardList();
        }
        else if (command.startsWith("sendMessage")) {
            return ChatroomContoller.sendMessage(command.substring(12));
        }
        else if (command.startsWith("getMessages")) {
            return ChatroomContoller.getMessages();
        }
        else if (command.startsWith("logout")) {
            return RegisterController.logout(parts[1]);
        }
        return "";
    }
}
