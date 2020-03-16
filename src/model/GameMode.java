package model;

/**
 * Represent the different game mode possible
 */
public class GameMode {
    private int difficulty;
    private String mode;
    public GameMode(int difficulty,String mode){
        this.difficulty = difficulty;
        this.mode = mode;
    }
    public GameMode(){

    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}
