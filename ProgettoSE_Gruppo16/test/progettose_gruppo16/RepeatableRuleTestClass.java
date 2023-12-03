package progettose_gruppo16;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.action.Action;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *  Classe test per le regole ripetibili.
 *  Per il testing vengono eliminate le dipendenze dal RulesManager e vengono aggiunti i metodi per 
 *  settare lo sleepingPeriod.
 */
public class RepeatableRuleTestClass extends RuleTestClass{
    
    private int daysToSleep;
    private LocalTime sleepPeriod;
    private LocalDate dayLastFired;
    private LocalTime timeLastFired;
    private boolean fireable;
    
    public RepeatableRuleTestClass(String name, Trigger trigger, Action action, int daysToSleep, LocalTime sleepPeriod) {
        super(name, trigger, action);
        this.daysToSleep = daysToSleep;
        this.sleepPeriod = sleepPeriod;
        this.dayLastFired = null;
        this.timeLastFired = null;
        this.fireable = true;
    }
    
    @Override
    public boolean evaluate(){
                
        if(!fireable){
            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();
            if((today.isEqual(dayLastFired.plusDays(daysToSleep)) || today.isAfter(dayLastFired.plusDays(daysToSleep))) && (now.isAfter(timeLastFired.plusHours(sleepPeriod.getHour()).plusMinutes(sleepPeriod.getMinute()))))
                setFireable(true);
            
        }
        
        if(super.getTrigger().checkCondition()){
            
            if(fireable){
                super.getAction().executeAction();
                dayLastFired = LocalDate.now();
                timeLastFired = LocalTime.now();
                setFireable(false);
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

    public void setDaysToSleep(int daysToSleep) {
        this.daysToSleep = daysToSleep;
    }

    public void setSleepPeriod(LocalTime sleepPeriod) {
        this.sleepPeriod = sleepPeriod;
    }

    public void setDayLastFired(LocalDate dayLastFired) {
        this.dayLastFired = dayLastFired;
    }

    public void setTimeLastFired(LocalTime timeLastFired) {
        this.timeLastFired = timeLastFired;
    }

    @Override
    public String toString() {
        return super.toString() + "-" + daysToSleep + "-" + sleepPeriod + "-" + dayLastFired + "-" + timeLastFired + "-" + fireable;
    }
    
}