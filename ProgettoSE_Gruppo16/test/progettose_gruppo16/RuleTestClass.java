package progettose_gruppo16;

import java.io.Serializable;
import progettose_gruppo16.action.Action;
import progettose_gruppo16.trigger.Trigger;

/**
 *  Classe astratta per i test sulle regole
 */
public abstract class RuleTestClass implements Serializable{
    
    private final String name;
    private Trigger trigger;
    private Action action;
    private boolean active; 
    
    public RuleTestClass(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = true;
    }
    
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
    
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return name + "-" + trigger + "-" + action + "-" + active;
    }
       
}