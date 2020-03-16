package model;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * Classical simon says game
 */
public class ClassicalBoardGame implements BoardGame {
    private int nbOfRectangles;
    private ArrayList<Rectangle> rectangles =  new ArrayList<>();
    private int screenWidth,screenHeight;
    public ClassicalBoardGame(int nbOfRectangles, int screenWidth, int screenHeight){
        this.nbOfRectangles = nbOfRectangles;
        this.screenHeight = screenHeight;
        this.screenWidth = screenWidth;
        this.setRectangles();

    }

    /**
     * Set the rectangles in two rows
     */
    public void setRectangles(){
        for(int i = 0;i < this.getNbOfRectangles();i++){
            this.getRectangles().add(new Rectangle());
            if(i>this.getNbOfRectangles()/2 -1) {
                this.getRectangles().get(i).setX((i-nbOfRectangles/2)*screenHeight/(nbOfRectangles/2));
                this.getRectangles().get(i).setY(screenHeight/(nbOfRectangles/2));
            }else{
                this.getRectangles().get(i).setX(i*screenHeight/(nbOfRectangles/2));
                this.getRectangles().get(i).setY(0);
            }
            this.getRectangles().get(i).setWidth(screenHeight/(nbOfRectangles/2));
            this.getRectangles().get(i).setHeight(screenHeight/(nbOfRectangles/2));
            this.getRectangles().get(i).setStroke(Paint.valueOf("white"));
        }
    }

    public int getNbOfRectangles() {
        return nbOfRectangles;
    }

    public ArrayList<Rectangle> getRectangles() {
        return rectangles;
    }
}
