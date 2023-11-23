/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.sql.Time;

/**
 *
 * @author raffa
 */
public class Rule {
    
    private final int ID;
    private final String name;
    private Trigger trigger;
    private Action action;
    private Boolean active;
    private Boolean repeatable; //false if the rule can be fired only once
    private Time sleepPeriod; // if repeatible is true the rule will be periodically checked again after this time
    
    private static int counter = 1;

    public Rule(String name, Trigger trigger, Action action, Boolean repeatable, Time sleepPeriod) {
        this.ID = counter;
        this.name = name;
        this.trigger = trigger;
        this.action = action;
        this.active = true;
        this.repeatable = repeatable;
        this.sleepPeriod = sleepPeriod;
        counter++;
    }
    
    public boolean evaluate(){
        
        if(trigger.checkCondition()){
            action.executeAction();
            return true;
        }
        else{
            return false;
        }
    }
    
}
