package progettose_gruppo16.trigger;

import java.io.Serializable;

/**
 * The Trigger interface represents a condition that can be checked to determine if a specific event should occur.
 * Implementing classes provide the logic for evaluating the condition.
 * 
 * This interface extends the Serializable interface to support serialization.
 * Serializable is used to convert the object into a byte stream for various purposes like storage or transmission.
 */
public interface Trigger extends Serializable{
    
    /**
     * Checks whether the condition specified by the trigger is satisfied.
     * 
     * @return True if the condition is satisfied, false otherwise.
     */
    public abstract boolean checkCondition();
}
