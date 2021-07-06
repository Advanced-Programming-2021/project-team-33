package View;

import Controller.EffectController;
import Controller.ProgramController;
import Model.*;
import Model.Effects.AttackDirectToOpponent;
import Model.Effects.ChangeAllEquipAttack;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CardCreatorMenu {
    public TextField cardName;
    public TextField description;
    public ChoiceBox attribute;
    public ChoiceBox cardType;
    public ChoiceBox cardCategory;
    public TextField limit;
    public TextField level;
    public TextField attack;
    public TextField defence;
    public ChoiceBox effects;
    public Label effectDescription;

    private static Effect selectedEffect = null;

    public void start() throws IOException {
        Stage primaryStage = ProgramController.getStage();
        Parent root = FXMLLoader.load(getClass().getResource("cardCreatorMenu.fxml"));
        primaryStage.setTitle("Yu-Gi-Oh");
        primaryStage.setScene(new Scene(root, 1280, 720));
        primaryStage.show();
        selectedEffect = null;
    }

    @FXML
    public void initialize() {
        attribute.setItems(FXCollections.observableArrayList(Attribute.values()));
        cardType.setItems(FXCollections.observableArrayList(CardType.values()));
        cardCategory.setItems(FXCollections.observableArrayList(CardCategory.values()));
        ArrayList<String> effectsList = new ArrayList<>();
        for (Effect effect : EffectController.effects) {
            effectsList.add(effect.getEffectName());
        }
        effects.setItems(FXCollections.observableArrayList(effectsList.toArray()));
        effects.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observableValue, Number number, Number number2) {
                System.out.println(effects.getItems().get((Integer) number2));
                selectedEffect = Effect.getEffectByName(effects.getItems().get((Integer) number2).toString());
                effectDescription.setText(selectedEffect.getEffectDescription());
            }
        });

        limit.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}")) {
                limit.setText(oldValue);
            }
        });
        level.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}")) {
                level.setText(oldValue);
            }
        });
        attack.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}")) {
                attack.setText(oldValue);
            }
        });
        defence.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d{0,10}")) {
                defence.setText(oldValue);
            }
        });

    }


    public void back(MouseEvent mouseEvent) throws Exception {
        MainMenu.playSound("src/main/resources/music/click.mp3");
        new MainMenu().start();
    }

    public void createCard(MouseEvent mouseEvent) throws Exception {
        try {
            Card newCard = new Card(cardName.getText(),
                    description.getText(), 2000,
                    new ArrayList<>(List.of(CardType.valueOf(cardType.getValue().toString()))),
                    CardCategory.valueOf(cardCategory.getValue().toString()), Integer.parseInt(limit.getText()),
                    Integer.parseInt(level.getText()), new ArrayList<>(List.of(selectedEffect)), Integer.parseInt(attack.getText()),
                    Integer.parseInt(defence.getText()), Attribute.valueOf(attribute.getValue().toString()));

            System.out.println(newCard.getEffects().get(0).getEffectName());
            MainMenu.playSound("src/main/resources/music/click.mp3");
            new MainMenu().start();
        } catch (Exception e) {

        }
    }
}
