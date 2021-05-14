package View;

import Controller.Util;
import Model.Card;
import com.google.gson.JsonArray;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;

public class ImportExportMenu {
    public void run(String input) {
        MainMenu.checked = false;
        MainMenu.showCurrentMenu(Util.getCommand(input, "menu show-current"));
        importCard(Util.getCommand(input, "import card (.+)"));
        exportCard(Util.getCommand(input, "export card (.+)"));
        MainMenu.exitMenu(Util.getCommand(input, "menu exit"));
    }

    public static void exportCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            Card card = Card.getCardByName(matcher.group(1));
            if (card == null) {
                System.out.println("no card with this name");
            } else {
                JsonArray cardDetail = new JsonArray();
                cardDetail.add("Card Name :" + card.getCardName());
                cardDetail.add("Card Description :" + card.getDescription());
                try (FileWriter file = new FileWriter(matcher.group(1) + ".json")) {
                    file.write(cardDetail.toString());
                    file.flush();
                } catch (IOException e) {
                    System.out.println("Error");
                }
            }
        }
    }

    public static void importCard(Matcher matcher) {
        if (!MainMenu.checked && matcher.matches()) {
            MainMenu.checked = true;
            try {
                String card = new String(Files.readAllBytes(Paths.get(matcher.group(1) + ".json")));
                System.out.println(card);
            } catch (IOException e) {
                System.out.println("no json file with this name found");
            }
        }
    }

}
