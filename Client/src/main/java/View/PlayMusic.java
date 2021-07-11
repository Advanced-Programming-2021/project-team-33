package View;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import java.util.Timer;
import java.util.TimerTask;

public class PlayMusic extends TimerTask {
    Timer timer;
    public void start() {
        timer = new Timer();
        timer.schedule(new PlayMusic(), 0,110000);

    }
    @Override
    public void run() {
        if(MainMenu.menu.equals("menu") || MainMenu.menu.equals("start")) music();
        if(MainMenu.menu.equals("game")) music1();
    }

    static  MediaPlayer mediaPlayer;
    public void music() {
        String path = "src/main/resources/music/Main.mp3";
        Media music = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setVolume(5);
        mediaPlayer.play();

    }

    public void music1() {
        String path = "src/main/resources/music/Game.mp3";
        Media music = new Media(Paths.get(path).toUri().toString());
        mediaPlayer = new MediaPlayer(music);
        mediaPlayer.setVolume(.4);
        mediaPlayer.play();

    }


    public static void stop() {
        if(mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}
