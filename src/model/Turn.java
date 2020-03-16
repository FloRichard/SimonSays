package model;

import controller.Controller;
import exception.ExceptionLose;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Random;

/**
 * Represent a turn of the game
 */
public class Turn {
    BoardGame boardGame;
    ArrayList<Rectangle> rectanglesToClick =  new ArrayList<>();
    public Turn(BoardGame boardGame){
        this.boardGame = boardGame;
    }

    /**
     * Select randomly a new rectangle in the sequence
     * @return
     */
    public Rectangle getNextRectangleToClick(){
        Random randRect =  new Random();
        int rectangleIndex = randRect.nextInt(boardGame.getNbOfRectangles());
        rectanglesToClick.add(boardGame.getRectangles().get(rectangleIndex));
        return boardGame.getRectangles().get(rectangleIndex);

    }

    /**
     * Check if the clicked rectangle is the good one in the sequence
     * @param clickedRectangles
     * @param nbClicks
     * @param controller
     * @return
     * @throws ExceptionLose
     */
    public boolean checkForGoodClick(ArrayList<Rectangle> clickedRectangles, int nbClicks,Controller controller) throws ExceptionLose {
        if(!clickedRectangles.get(nbClicks).equals(rectanglesToClick.get(nbClicks)))
                    throw new ExceptionLose(controller);
        return true;
    }

    public ArrayList<Rectangle> getRectanglesToClick() {
        return rectanglesToClick;
    }
}
