package progettose_gruppo16;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.action.Action;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Class for repeatable rules.
 * After the rule is activated, it can be re-executed after the sleep period specified by the user.
 */
public class RepeatableRule extends Rule{
    
    private final int daysToSleep;
    private final LocalTime sleepPeriod;
    private LocalDate dayLastFired;
    private LocalTime timeLastFired;
    private boolean fireable;
    
    /**
     * Constructor that calls the parent constructor, adding attributes useful
     * for controlling the sleep period.
     * @param name
     * @param trigger
     * @param action
     * @param daysToSleep -> sleep days
     * @param sleepPeriod -> sleep hours and minutes
     */
    public RepeatableRule(String name, Trigger trigger, Action action, int daysToSleep, LocalTime sleepPeriod) {
        super(name, trigger, action);
        this.daysToSleep = daysToSleep;
        this.sleepPeriod = sleepPeriod;
        this.dayLastFired = null;
        this.timeLastFired = null;
        this.fireable = true;
    }
    
    /**
     * The method performs the action selected by the user when the trigger condition is met.
     * If the trigger condition is true, the action is not executed if the sleep period has not passed yet.
     * @return -> returns true if the action is executed; false otherwise
     */
    @Override
    public boolean evaluate(){
        
        RulesManager rm = super.getRm();
        LocalDate today;
        LocalTime now;
        
        if(!fireable){
            today = LocalDate.now();
            now = LocalTime.now();
            if((today.isEqual(dayLastFired.plusDays(daysToSleep)) || today.isAfter(dayLastFired.plusDays(daysToSleep))) && (now.isAfter(timeLastFired.plusHours(sleepPeriod.getHour()).plusMinutes(sleepPeriod.getMinute()))))
                rm.changeFireable(this);
            
        }
        
        if(super.getTrigger().checkCondition()){
            
            if(fireable){
                super.getAction().executeAction();
                dayLastFired = LocalDate.now();
                timeLastFired = LocalTime.now();
                rm.changeFireable(this);
                return true;
            }
            
        }
        return false;       
        
    }

    public int getDaysToSleep() {
        return daysToSleep;
    }

    public LocalTime getSleepPeriod() {
        return sleepPeriod;
    }

    public LocalDate getDayLastFired() {
        return dayLastFired;
    }

    public LocalTime getTimeLastFired() {
        return timeLastFired;
    }
    
    public boolean isFireable() {
        return fireable;
    }

    public void setFireable(boolean fireable) {
        this.fireable = fireable;
    }

    @Override
    public String toString() {
        return super.toString() + "-" + daysToSleep + "-" + sleepPeriod + "-" + dayLastFired + "-" + timeLastFired + "-" + fireable;
    }
    
}