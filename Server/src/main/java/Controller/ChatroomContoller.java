package Controller;

import Model.Message;
import com.google.gson.Gson;

import java.util.ArrayList;

public class ChatroomContoller {
    public static ArrayList<Message> allMessages = new ArrayList<>();
    public static synchronized String sendMessage(String string) {
        Gson gson = new Gson();
        Message message = gson.fromJson(string, Message.class);
        allMessages.add(message);
        return Util.success(gson.toJson(allMessages));
    }
    public static String getMessages() {
        Gson gson = new Gson();
        return Util.success(gson.toJson(allMessages));
    }
}
