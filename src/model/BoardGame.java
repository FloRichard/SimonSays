package model;

import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

/**
 * An interface representing a board game, all different boardgames implement this
 */
public interface BoardGame {
    void setRectangles();
    int getNbOfRectangles();
    ArrayList<Rectangle> getRectangles();
}
