package View;

import Controller.Util;
import Model.Card;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;

public class ImportExportMenu {
    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        importCard(Util.getCommand(input,"import card (\\S+)"));
        exportCard(Util.getCommand(input,"export card (\\S+)"));
    }

    public static void importCard(Matcher matcher){
        if(!MainMenu.checked&&matcher.matches()){
            ArrayList<Card> cards = new ArrayList<Card>();
            Card card = Card.getCardByName(matcher.group(1));
            if(card == null){
                System.out.println("no card with this name");
            }
            else {
                cards.add(card);
                try {
                    FileWriter fileWriter = new FileWriter(matcher.group(1)+".json");
                    fileWriter.write(new Gson().toJson(cards));
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void exportCard(Matcher matcher){
        if(!MainMenu.checked&&matcher.matches()){
            ArrayList<Card> cards;
            try {
                String json = new String(Files.readAllBytes(Paths.get(matcher.group(1)+".json")));
                cards = new Gson().fromJson(json,new TypeToken<List<Card>>(){}.getType());
                System.out.println(cards);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
