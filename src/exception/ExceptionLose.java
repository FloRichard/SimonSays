package exception;

import controller.Controller;

/**
 * Exception thrown if the player clicked on the wrong rectangle
 */
public class ExceptionLose extends Exception{
    public ExceptionLose(Controller controller){
        System.out.println("Vous avez perdu !");
        controller.lose();
    }
}
