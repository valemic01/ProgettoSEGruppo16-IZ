package progettose_gruppo16.handlerTrigger;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.trigger.Trigger;

/**
 * This class represents a basic implementation of the HandlerTrigger interface.
 * It provides a default implementation for setting the next handler in the chain
 * and handling GUI elements and behavior triggers.
 *
 */
public class BaseHandlerTrigger implements HandlerTrigger{

    private HandlerTrigger next;

    /**
     * Sets the next handler in the chain.
     *
     * @param h The next handler to be set.
     */
    @Override
    public void setNext(HandlerTrigger h) {
        next = h;
    }

    /**
     * Handles the GUI elements based on the implementation in subclasses.
     * If a next handler is set, delegates the handling to the next handler.
     *
     * @param ap The AnchorPane to handle.
     * @param cb The ComboBox of the selected trigger.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        if(next != null) next.handleGUI(ap, cb);
    }

    /**
     * Handles the behavior triggers based on the implementation in subclasses.
     * If a next handler is set, delegates the handling to the next handler.
     *
     * @param ap    The AnchorPane associated with the trigger.
     * @param ht    The HandlerTrigger instance.
     * @param x     An integer parameter for trigger behavior.
     * @param vbox  The VBox associated with the trigger with all the "Not" check boxes.
     * @return      The Trigger instance resulting from the behavior handling.
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox vbox) {
        if(next != null) return next.handleBehaviour(ap, ht, x, vbox);
        return null;
    }
}
