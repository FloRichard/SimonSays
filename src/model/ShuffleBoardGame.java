package model;

import javafx.scene.shape.Rectangle;
import java.util.ArrayList;
import java.util.Random;

/**
 * An other rule of the game, all the rectangle are positionned randomly
 */
public class ShuffleBoardGame implements BoardGame {
    private int nbOfRectangles;
    private ArrayList<Rectangle> rectangles =  new ArrayList<>();
    private int screenWidth,screenHeight;
    public ShuffleBoardGame(int nbOfRectangles, int screenWidth, int screenHeight){
        this.nbOfRectangles = nbOfRectangles;
        this.screenWidth =screenWidth;
        this.screenHeight = screenHeight;
        this.setRectangles();
    }

    @Override
    /**
     * Set all the rectangles randomly
     */
    public void setRectangles() {
        ArrayList<Integer> visitedX = new ArrayList<>();
        ArrayList<Integer> visitedY = new ArrayList<>();

        for(int i = 0 ; i < getNbOfRectangles() ; i++){
            this.getRectangles().add(new Rectangle());
            Rectangle rectangle = this.getRectangles().get(i);
            Random random = new Random();
            int x = random.nextInt(screenWidth);
            int y = random.nextInt(screenHeight);
            while (canBePutOnTheBoard(x,visitedX)){
                 x = random.nextInt(screenWidth);
            }
            if(x > screenWidth - screenHeight/nbOfRectangles){
                x -= screenHeight/nbOfRectangles;
            }
            if(y > screenHeight - screenHeight/nbOfRectangles){
                y -= screenWidth/nbOfRectangles;
            }
            visitedX.add(x);
            visitedY.add(y);
            rectangle.setX(x);
            rectangle.setY(y);
            rectangle.setWidth(screenHeight/nbOfRectangles);
            rectangle.setHeight(screenHeight/nbOfRectangles);

        }
    }

    /**
     * Verify if the rectangle can be put on the actual x location
     * @param x the actual location
     * @param visitedX list of all actual x positioned
     * @return true if the rectangles can be positioned
     */
   public boolean canBePutOnTheBoard(int x,ArrayList<Integer> visitedX){
        boolean flagX = false;
        boolean flagY = false;
        for(Integer valX : visitedX){
            int size =  screenHeight/nbOfRectangles;
            if(x > valX+size && x < valX-size) {
                return true;
            }
        }
        return false;
    }
    @Override
    public int getNbOfRectangles() {
        return nbOfRectangles;
    }

    @Override
    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }
}
