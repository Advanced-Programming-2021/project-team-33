package View;

import Controller.EffectController;
import Controller.Util;
import Model.Card;
import Model.CardCategory;
import Model.CardType;
import Model.Effect;
import com.google.gson.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
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
                JsonObject jsonObject = new JsonObject();
                jsonObject.addProperty("id", card.getCardId());
                jsonObject.addProperty("name", card.getCardName());
                jsonObject.addProperty("description", card.getDescription());
                jsonObject.addProperty("price", card.getPrice());
                JsonArray jsonArray1 = new JsonArray();
                for (Effect effect : card.getEffects()) {
                    jsonArray1.add(effect.getEffectName());
                }
                jsonObject.add("effects", jsonArray1);
                JsonArray jsonArray2 = new JsonArray();
                for (CardType cardType : card.getCardTypes()) {
                    jsonArray2.add(cardType.toString());
                }
                jsonObject.add("types", jsonArray2);
                jsonObject.addProperty("category", card.getCardCategory().toString());
                jsonObject.addProperty("limit", card.getLimit());
                jsonObject.addProperty("level", card.getLevel());
                jsonObject.addProperty("attack", card.getAttack());
                jsonObject.addProperty("deffence", card.getDefence());
                try (FileWriter file = new FileWriter(matcher.group(1) + ".json")) {
                    file.write(jsonObject.toString());
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
                JsonParser jsonParser = new JsonParser();
                String stringCard = new String(Files.readAllBytes(Paths.get(matcher.group(1) + ".json")));
                JsonObject card = (JsonObject) jsonParser.parse(stringCard);

                ArrayList<Effect> effects = new ArrayList<>();
                JsonArray jsonArray = card.get("effects").getAsJsonArray();
                for (JsonElement jsonElement : jsonArray) {
                    Effect effect = Effect.getEffectByName(jsonElement.getAsString());
                    if (effect != null) {
                        effects.add(effect);
                    }
                }
                ArrayList<CardType> cardTypes = new ArrayList<>();
                JsonArray jsonArray2 = card.get("types").getAsJsonArray();
                for (JsonElement jsonElement : jsonArray2) {
                    CardType cardType = CardType.valueOf(jsonElement.getAsString());
                    cardTypes.add(cardType);
                }
                String id = card.get("id").getAsString();
                String name = card.get("name").getAsString();
                String description = card.get("description").getAsString();
                String price = card.get("price").getAsString();
                CardCategory category = CardCategory.valueOf(card.get("category").getAsString());
                String limit = card.get("limit").getAsString();
                String level = card.get("level").getAsString();
                String attack = card.get("attack").getAsString();
                String deffence = card.get("deffence").getAsString();

                Card newCard = new Card(name, description, Integer.parseInt(price), cardTypes, category, Integer.parseInt(limit),
                        Integer.parseInt(level), effects, Integer.parseInt(attack), Integer.parseInt(deffence));
            } catch (IOException e) {
                System.out.println("no json file with this name found");
            }
        }
    }

}
