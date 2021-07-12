package Controller;

import Model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class RegisterController {
    public static synchronized String register(String username,
                                  String nickname,
                                  String password) {
        if (ProgramController.isUserExist(username))
            return Util.showError("user with username " + username + " already exists");
        else if (ProgramController.isNicknameExist(nickname))
            return Util.showError("user with nickname " + nickname + " already exists");
        else {
            ProgramController.createUser(username, nickname, password);
            System.out.println(username);
            return Util.success("user created successfully!");
        }
    }

    public synchronized static String login(String username, String password) {
        if (Player.loggedInPlayers == null) Player.loggedInPlayers = new HashMap<>();
        for (Map.Entry<String, Player> playerEntry : Player.loggedInPlayers.entrySet()) {
            if(playerEntry.getValue().getUsername().equals(username)) return Util.showError("Username and password didn't match!");
        }
        for (Player player: Player.getPlayers()) {
            if (player.getUsername().equals(username) && player.getPassword().equals(password)) {
                String token = UUID.randomUUID().toString();
                Player.loggedInPlayers.put(token, player);
                return Util.success(token);
            }
        }
        return Util.showError("Username and password didn't match!");
    }

    public static String logout(String token) {
        Player.loggedInPlayers.remove(token);
        System.out.println(token);
        return Util.success("logout");
    }
}
