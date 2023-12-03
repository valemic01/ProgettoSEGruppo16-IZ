package progettose_gruppo16;

import progettose_gruppo16.action.Action;
import progettose_gruppo16.trigger.Trigger;

/**
 *  Classe test per le regole da eseguire una sola volta. 
 *  Per il testing vengono eliminate le dipendenze dal RulesManager
 */
public class OneTimeRuleTestClass extends RuleTestClass{
        
    public OneTimeRuleTestClass(String name, Trigger trigger, Action action) {
        super(name, trigger, action);
    }
    
    @Override
    public boolean evaluate(){
                
       if(super.getTrigger().checkCondition()){
           super.getAction().executeAction();
           super.setActive(false);
           return true;
       }else
           return false;
       
    }
    
}
