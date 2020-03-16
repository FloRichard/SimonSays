package view;

import javafx.application.Application;
import javafx.stage.Stage;
import model.GameMode;

/**
 * Application programmed by Florian RICHARD
 */
public class SimonSays extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        GameMode mode = new GameMode();
        System.out.println(mode.getDifficulty()+mode.getMode());
        mode = MenuInterface.runMenu();
        primaryStage = Interface.getGame(800,600,mode);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
