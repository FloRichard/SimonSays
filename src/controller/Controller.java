package controller;

import exception.ExceptionLose;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;
import model.BoardGame;
import model.GameMode;
import model.Turn;
import view.Interface;
import view.MenuInterface;

import java.util.ArrayList;
import java.util.Optional;

/**
 * Controller of the game, it accepts every kind of boardgames
 */
public class Controller {
    static int nbClicks=0;
    BoardGame boardGame;
    Turn turn;
    ArrayList<Rectangle> clickedRectangles = new ArrayList<>();
    public Controller(BoardGame boardGame, Turn turn){
        this.boardGame = boardGame;
        this.turn = turn;
    }

    /**
     * Play a turn by adding a new rectangle to click
     */
    public void playATurn(){
        this.getTurn().getNextRectangleToClick();
        Timeline timeline = new Timeline();
        if(!clickedRectangles.isEmpty()) {
            Rectangle lastClickedRectangle = this.clickedRectangles.get(this.clickedRectangles.size()-1);
            timeline.getKeyFrames().addAll(
                    new KeyFrame(Duration.millis(0),
                            new KeyValue(lastClickedRectangle.fillProperty(), Color.BLACK)),
                    new KeyFrame(Duration.millis(300),
                            new KeyValue(lastClickedRectangle.fillProperty(), Color.GREEN)),
                    new KeyFrame(Duration.millis(600),
                            new KeyValue(lastClickedRectangle.fillProperty(), Color.GREEN)),
                    new KeyFrame(Duration.millis(900),
                            new KeyValue(lastClickedRectangle.fillProperty(), Color.BLACK))
            );
        }
        for(int i = 0 ; i<this.getTurn().getRectanglesToClick().size();i++){
            Rectangle rectangleToClick  = this.getTurn().getRectanglesToClick().get(i);
            timeline.getKeyFrames().addAll(
                        new KeyFrame(Duration.millis(1000 * (i+1)),
                                new KeyValue(rectangleToClick.fillProperty(), Color.BLACK)),
                        new KeyFrame(Duration.millis((i+1) * 1000 + 300),
                                new KeyValue(rectangleToClick.fillProperty(), Color.YELLOW)),
                        new KeyFrame(Duration.millis(1000 * (i+1) + 600),
                                new KeyValue(rectangleToClick.fillProperty(), Color.YELLOW)),
                        new KeyFrame(Duration.millis(1000 * (i+1) + 900),
                                new KeyValue(rectangleToClick.fillProperty(), Color.BLACK))
            );
        }
        timeline.play();
        nbClicks=0;
    }

    /**
     * Set the rectangle handler
     */
    public void setSquareHandlers(){
        for(Rectangle rectangle : boardGame.getRectangles()){
            rectangle.setOnMouseClicked(new EventHandler<MouseEvent>() {
                public void handle(MouseEvent event) {
                    clickedRectangles.add(rectangle);
                    Timeline timeline =  new Timeline();
                    timeline.getKeyFrames().addAll(
                            new KeyFrame(Duration.ZERO,
                                    new KeyValue(rectangle.fillProperty(),Color.BLACK)),
                            new KeyFrame(Duration.millis(150),
                                    new KeyValue(rectangle.fillProperty(),Color.GREEN)),
                            new KeyFrame(Duration.millis(300),
                                    new KeyValue(rectangle.fillProperty(),Color.GREEN)),
                            new KeyFrame(Duration.millis(600),
                                    new KeyValue(rectangle.fillProperty(),Color.BLACK))

                    );

                    timeline.play();

                    try {
                        getTurn().checkForGoodClick(clickedRectangles,nbClicks,getController());
                        nbClicks++;
                        if(nbClicks == getTurn().getRectanglesToClick().size()){
                            updateScore();
                            playATurn();
                            clickedRectangles.clear();
                        }
                    } catch (ExceptionLose exceptionLose) {

                    }
                }
            });
        }
    }


    private Turn getTurn() {
        return turn;
    }

    public Controller getController(){
        return this;
    }

    /**
     *  Method called when the player clicked on the wrong rectangle
     */
    public void lose() {
        Alert alert =  new Alert(Alert.AlertType.CONFIRMATION);
        alert.setHeaderText("Vous avez perdu ! ");
        alert.setContentText("Voulez vous rejouer une partie ?");
        Optional<ButtonType> option =alert.showAndWait();
        if(option.get() == ButtonType.OK){
            this.getTurn().getRectanglesToClick().clear();
            clickedRectangles.clear();
            Interface.getScore().setText("0");
        }else{
            Interface.close();
            GameMode mode = new GameMode();
            mode = MenuInterface.runMenu();
            Stage newGame = Interface.getGame(800,600,mode);
            newGame.show();
        }

    }

    /**
     * Update the score
     */
    public void updateScore(){
        int actualScore = Integer.parseInt(Interface.getScore().getText());
        Interface.getScore().setText(Integer.toString(actualScore+10));
    }
}
