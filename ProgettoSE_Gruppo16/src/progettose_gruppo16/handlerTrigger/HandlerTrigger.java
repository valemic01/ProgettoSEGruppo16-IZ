package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.Trigger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.Handler;

/**
 * The HandlerTrigger interface extends the Handler interface and defines methods
 * for handling triggers in a chain of responsibility.
 * 
 * Implementing classes should provide the logic for handling triggers and
 * may specify the next handler in the chain.
 * 
 */
public interface HandlerTrigger extends Handler {
    
    /**
     * Sets the next handler in the chain.
     * 
     * @param h The next handler to be set.
     */
    public void setNext(HandlerTrigger h);
    
    /**
     * Handles the behavior related to triggers.
     * 
     * @param ap The AnchorPane representing the application's UI.
     * @param ht The next HandlerTrigger in the chain.
     * @param x An integer parameter for behavior customization.
     * @param vbox The VBox representing the application's UI.
     * @return The Trigger that has been handled, or null if no trigger is handled.
     */
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox vbox);
}