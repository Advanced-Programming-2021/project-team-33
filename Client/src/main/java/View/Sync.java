package View;

import Controller.ProgramController;
import Model.Player;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;


public class Sync extends TimerTask {

    public static Timer timer;

    public void start() {
        timer = new Timer();
        timer.schedule(new View.Sync(), 2000, 500);
    }

    @Override
    public void run() {
        sync();
    }


    public void sync() {
        String result = "0";
        try {
            ProgramController.dataOutputStream.writeUTF("multiplayer " + Player.thePlayer.getUsername());
            ProgramController.dataOutputStream.flush();
            result = ProgramController.dataInputStream.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(result);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(!result.equals("0")){
            Lobby.playerName1 = Player.thePlayer.getUsername();
            Lobby.playerName2 = result;
            try {
                new Lobby().start();
            } catch (Exception e) {
                e.printStackTrace();
            }
            Sync.timer.cancel();
        }
    }

}

