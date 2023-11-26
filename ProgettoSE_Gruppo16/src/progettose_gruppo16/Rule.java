/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.Serializable;
import java.sql.Time;

/**
 *
 * @author raffa
 */
public class Rule implements Serializable{
    
    private final String name;
    private Trigger trigger;
    private Action action;
    private Boolean active;
    private Boolean repeatable; //false if the rule can be fired only once
    private Time sleepPeriod; // if repeatible is true the rule will be periodically checked again after this time
    private boolean done;   
    
    public Rule(String name, Trigger trigger, Action action, Boolean repeatable, Time sleepPeriod) {
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = true;
        this.repeatable = repeatable;
        this.sleepPeriod = sleepPeriod;
        done=false;
    }
    
    /**
     * Il metodo esegue l'azione selezionata dall'utente quando la condizione del trigger è verificata.
     * Si utilizza un booleano per eseguire una sola volta l'azione.
     * @return  --> restituisce true se l'azione viene eseguita; false altrimenti
     */
    public boolean evaluate(){
        
        if(trigger.checkCondition()){
            
            if(!done){
                action.executeAction();
                done=true;
                return true;
            }
        }
        else{
            done=false;
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public Trigger getTrigger() {
        return trigger;
    }

    public Action getAction() {
        return action;
    }

    public Boolean getActive() {
        return active;
    }

    public Boolean getRepeatable() {
        return repeatable;
    }

    public Time getSleepPeriod() {
        return sleepPeriod;
    }

    public boolean isDone() {
        return done;
    }
    
    public void setActive(Boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return name + "-" + trigger + "-" + action + "-" + active + "-" + repeatable + "-" + sleepPeriod + "-" + done;
    }
    
    
}
