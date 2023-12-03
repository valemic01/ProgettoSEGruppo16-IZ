package progettose_gruppo16;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.action.Action;

/**
 *  Classe per le regole da eseguire una sola volta. 
 *  Dopo che l'azione viene eseguita, la regola viene disattivata.
 */
public class OneTimeRule extends Rule{
    
    private transient RulesManager rm;
    
    public OneTimeRule(String name, Trigger trigger, Action action) {
        super(name, trigger, action);
    }
    
    /**
    * Il metodo esegue l'azione selezionata dall'utente quando la condizione del trigger è verificata.
    * @return  -> restituisce true se l'azione viene eseguita; false altrimenti
    */
    /***********************************VALUTARE ISTANZA RULES MANAGER***************************************/
    @Override
    public boolean evaluate(){
        
        rm = RulesManager.getInstance();
        
       if(super.getTrigger().checkCondition()){
           super.getAction().executeAction();
            rm.activeInactivateRule(this);
           return true;
       }else
           return false;
       
    }
    
}