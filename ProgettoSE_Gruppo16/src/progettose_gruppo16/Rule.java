package progettose_gruppo16;

import java.io.Serializable;

/**
 *  Classe astratta Rule
 */
public abstract class Rule implements Serializable{
    
    private final String name;
    private Trigger trigger;
    private Action action;
    private boolean active; 
    
    public Rule(String name, Trigger trigger, Action action) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = true;
    }
    
    /**
     * Metodo astratto per la valutazione delle regole.
     * @return -> true se l'azione è stata eseguita; false altrimenti
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
    
    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return name + "-" + trigger + "-" + action + "-" + active;
    }
       
}