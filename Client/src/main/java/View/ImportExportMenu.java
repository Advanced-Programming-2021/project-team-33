package View;

import Controller.EffectController;
import Controller.ProgramController;
import Controller.Util;
import Model.*;
import com.google.gson.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;

public class ImportExportMenu {

    public TextField cardName;

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("importView.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
    }

    public static void exportCard(String cardName) {
        Card card = Card.getCardByName(cardName);
        if (card == null) {
            System.out.println("no card with this name");
        } else {
            JsonObject jsonObject = new JsonObject();
            jsonObject.addProperty("id", card.getCardId());
            jsonObject.addProperty("name", card.getCardName());
            jsonObject.addProperty("description", card.getDescription());
            jsonObject.addProperty("price", card.getPrice());
            JsonArray jsonArray1 = new JsonArray();
            if (card.getEffects() != null)
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
            try (FileWriter file = new FileWriter(cardName + ".json")) {
                file.write(jsonObject.toString());
                file.flush();
            } catch (IOException e) {
                System.out.println("Error");
            }
        }
    }

    public static void importCard(String cardName) {
        try {
            JsonParser jsonParser = new JsonParser();
            String stringCard = new String(Files.readAllBytes(Paths.get(cardName + ".json")));
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
                    Integer.parseInt(level), effects, Integer.parseInt(attack), Integer.parseInt(deffence), Attribute.FIRE);
            System.out.println(newCard.getCardName());
        } catch (IOException e) {
            System.out.println("no json file with this name found");
        }
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        MainMenu.playSound(Util.CLICK_MUSIC);
        new DeckMenu().start();
    }

    public void importCard(MouseEvent mouseEvent) {
        MainMenu.playSound(Util.CLICK_MUSIC);
        importCard(cardName.getText());
    }
}
