package progettose_gruppo16;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.action.Action;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *  Classe per le regole ripetibili.
 *  Dopo che la regola viene attivata, può essere ri-eseguita dopo lo sleep period specificato dall'utente.
 */
public class RepeatableRule extends Rule{
    
    private final int daysToSleep;
    private final LocalTime sleepPeriod;
    private LocalDate dayLastFired;
    private LocalTime timeLastFired;
    private boolean fireable;
    private transient RulesManager rm;
    
    /**
     *  Costruttore che richiama il costruttore del padre, aggiungendo gli attributi utili
     *  al controllo del periodo di sleep.
     * @param name
     * @param trigger
     * @param action
     * @param daysToSleep -> giorni di sleep
     * @param sleepPeriod -> ore e minuti di sleep
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
     * Il metodo esegue l'azione selezionata dall'utente quando la condizione del trigger è verificata.
     * Se la condizione del trigger è vera, l'azione non viene eseguita se non è ancora passato il periodo di sleep.
     * @return -> restituisce true se l'azione viene eseguita; false altrimenti
     */
    /***********************************VALUTARE ISTANZA RULES MANAGER***************************************/
    @Override
    public boolean evaluate(){
        
        rm = RulesManager.getInstance();
        
        if(!fireable){
            LocalDate today = LocalDate.now();
            LocalTime now = LocalTime.now();
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