package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.Trigger;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.Handler;

/**
 * The HandlerTrigger interface represents a handler for triggering behaviors
 * It extends the Handler interface and provides methods to set the next handler and handle specific behaviors.
 * 
 */
public interface HandlerTrigger extends Handler{
    
    /**
     * Sets the next handler in the chain.
     *
     * @param h The next HandlerTrigger in the chain.
     */
    public void setNext(HandlerTrigger h);
    
    /**
     * Handles the specified behavior triggered by the given parameters.
     *
     * @param ap    The AnchorPane on which the behavior is applied.
     * @param ht    An HandlerTrigger reference to the start of the chain
     * @param x     An integer parameter for behavior handling.
     * @param vbox  VBox containing "not" CheckBox elements
     * @return      The Trigger object representing the result of the behavior handling.
     */
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox vbox);
    
}
