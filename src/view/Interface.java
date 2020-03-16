package view;

import controller.Controller;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.*;

/**
 * this is the interface when you play, instancied after the menu
 */
public class Interface {
    private static  Controller controller ;
    private static  Text score = new Text();
    private static Stage gameInterface =  new Stage();

    /**
     * Method that return the set stage
     * @param screenWidth the screen  width
     * @param screenHeight the screen height
     * @param mode the selected game mode
     * @return the stage of the game
     */
    public static Stage getGame(int screenWidth, int screenHeight, GameMode mode) {
        BoardGame boardGame = null;
        if(mode.getMode().equals("classical")) {
            boardGame = new ClassicalBoardGame(mode.getDifficulty(), screenWidth, screenHeight);
        }else {
            boardGame = new ShuffleBoardGame(mode.getDifficulty(), screenWidth, screenHeight);
        }
        Turn turn = new Turn(boardGame);
        Pane paneSquareBoard = new Pane();
        Group groupSquareBoard = new Group();
        groupSquareBoard.getChildren().addAll(boardGame.getRectangles());
        paneSquareBoard.getChildren().add(groupSquareBoard);
        paneSquareBoard.setPrefSize(screenWidth,screenHeight);
        paneSquareBoard.setBorder(new Border(new BorderStroke(Color.RED,BorderStrokeStyle.DASHED,CornerRadii.EMPTY, new BorderWidths(1))));
        controller = new Controller(boardGame, turn);
        controller.setSquareHandlers();

        Button start = new Button();
        start.setText("start");
        start.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                controller.playATurn();
            }
        });
        HBox scoreContainer =  new HBox();

        score.setText("0");
        HBox.setHgrow(score, Priority.ALWAYS);
        scoreContainer.getChildren().addAll(new Label("Score : "),score);
        VBox container = new VBox();
        container.getChildren().addAll(scoreContainer,paneSquareBoard,start);
        Scene scene = new Scene(container, screenWidth, screenHeight+50);
        if(mode.getMode().equals("nightmare")){
            BackgroundFill backgroundFill =  new BackgroundFill(Color.BLACK,CornerRadii.EMPTY, Insets.EMPTY);
            paneSquareBoard.setBackground(new Background(backgroundFill));
        }
        gameInterface.setTitle("SimonSays");
        gameInterface.setScene(scene);
        return gameInterface;
    }

    public static Controller getController() {
        return controller;
    }

    public static void setController(Controller controller) {
        Interface.controller = controller;
    }

    public static Text getScore() {
        return score;
    }
    public static void close(){
        gameInterface.close();
    }

    public static void setScore(Text score) {
        Interface.score = score;
    }
}
