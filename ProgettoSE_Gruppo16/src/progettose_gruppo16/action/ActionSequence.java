package progettose_gruppo16.action;

import java.util.LinkedList;
import java.util.List;

/**
 * Class for managing a sequence of actions.
 * Each user-created action is added to the list so that invoking the executeAction method
 * on this object will execute all the actions in the sequence.
 */
public class ActionSequence implements Action{

    private List<Action> sequence;

    /**
     *  Constructor with the instantiation of the list.
     */
    public ActionSequence() {
        sequence = new LinkedList<>();
    }
    
    /**
     * Method for adding an action to the list.
     * @param a -> the action to be added.
     * @return -> true if the action is added successfully, false otherwise.
     */
    public boolean addAction(Action a){
        return sequence.add(a);
    }
    
    /**
     * Method for removing an action from the list.
     * @param a -> the action to be removed.
     * @return -> true if the action is removed successfully, false otherwise.
     */
    public boolean removeAction(Action a){
        return sequence.remove(a);
    }
    
    /**
     * Method inherited from the Action interface.
     * Calls executeAction() on all actions in the sequence.
     */
    @Override
    public void executeAction() {
        for(Action a: sequence)
            a.executeAction();
    }

    /**
     * Get method for the sequence of actions.
     * @return -> the sequence of actions
     */
    public List<Action> getSequence() {
        return sequence;
    }

    /**
     * Display all actions in the list.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Action a: sequence)
            sb.append(a.toString());
        return sb.toString();
    }
    
}