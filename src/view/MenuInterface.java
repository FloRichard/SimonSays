package view;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.GameMode;

import javax.swing.*;
import java.util.ArrayList;

/**
 * The menu interface to select game mode and difficulty
 *
 *
 */
public class MenuInterface{
    static int  difficulty;
    static int screenwidth=250;
    static int screenHeight = 250;

    /**
     * Static method who run the interface
     * @return the game mode selected in the the radio buttons
     */
    public static GameMode runMenu() {
        GameMode mode =  new GameMode();
        Stage menuStage = new Stage();
        HBox box =  setRadioButtons(mode);
        VBox vBox = setButtons(menuStage);
        Pane group =  new Pane();
        group.getChildren().addAll(box,vBox);
        Scene menuScene = new Scene(group,screenwidth+10,screenHeight+10);
        menuStage.setTitle("Select game mode ");
        menuStage.setScene(menuScene);
        menuStage.showAndWait();
        mode.setDifficulty(difficulty);
        return mode;
    }

    /**
     * Set all the radio buttons
     * @param mode the game mode selected by the radio buttons
     * @return return the container of the buttons
     */
    public static HBox setRadioButtons(GameMode mode){
        ToggleGroup radioGroup =  new ToggleGroup();
        RadioButton classical =  new RadioButton("classical");
        classical.setToggleGroup(radioGroup);
        classical.setSelected(true);
        mode.setMode("classical");
        RadioButton shuffle = new RadioButton("shuffle");
        shuffle.setToggleGroup(radioGroup);
        RadioButton nightmare =  new RadioButton("nightmare");
        nightmare.setToggleGroup(radioGroup);
        radioGroup.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
            @Override
            public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
                RadioButton radioButton = (RadioButton)radioGroup.getSelectedToggle();
                mode.setMode(radioButton.getText());
            }
        });
        HBox box = new HBox(new Label("Mode : "),classical,shuffle,nightmare);
        return box;
    }

    /**
     * Set the buttons for difficulty selection
     * @param menuStage the actual stage
     * @return the container of the buttons
     */
    public static VBox setButtons(Stage menuStage){
        VBox vBox = new VBox();
        vBox.setPrefWidth(screenwidth);
        vBox.setPrefHeight(screenHeight);
        vBox.setSpacing(10);
        Button easyDifficulty = new Button("Easy");
        easyDifficulty.setMaxSize(screenwidth,screenwidth);
        easyDifficulty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                difficulty = 4;
                menuStage.close();
            }
        });
        Button normalDifficulty = new Button("Normal");
        normalDifficulty.setMaxSize(screenwidth,screenwidth);
        normalDifficulty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                difficulty = 8;
                menuStage.close();
            }
        });
        Button hardDifficulty = new Button("Hard");
        hardDifficulty.setMaxSize(screenwidth,screenHeight);
        hardDifficulty.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                difficulty = 16;
                menuStage.close();
            }
        });
        vBox.getChildren().addAll(easyDifficulty, normalDifficulty, hardDifficulty);
        vBox.setAlignment(Pos.CENTER);
        return vBox;
    }
}
