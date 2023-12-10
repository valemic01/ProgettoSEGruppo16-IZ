package progettose_gruppo16;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.action.Action;
import java.io.Serializable;

/**
 *  Abstract class Rule
 */
public abstract class Rule implements Serializable{
    
    private final String name;
    private final Trigger trigger;
    private final Action action;
    private boolean active; 
    private transient RulesManager rm;
    
    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = true;
    }
    
    /**
     * Abstract method for rule evaluation.
     * @return -> true if the action is performed; false otherwise
     */
    public abstract boolean evaluate();

    public String getName() {
        return name;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public Action getAction() {
        return action;
    }

    public boolean isActive() {
        return active;
    }

    public RulesManager getRm() {
        return RulesManager.getInstance();
    }
    
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return name + "-" + trigger + "-" + action + "-" + active;
    }
       
}