package progettose_gruppo16;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.action.Action;

/**
 * Class for rules to be executed only once.
 * After the action is performed, the rule is deactivated.
 */
public class OneTimeRule extends Rule{
        
    public OneTimeRule(String name, Trigger trigger, Action action) {
        super(name, trigger, action);
    }
    
    /**
    * The method performs the action selected by the user when the trigger condition is met.
    * @return  -> returns true if the action is executed; false otherwise
    */
    @Override
    public boolean evaluate(){
        
        RulesManager rm = super.getRm();
        
       if(super.getTrigger().checkCondition()){
           super.getAction().executeAction();
            rm.activeInactivateRule(this);
           return true;
       }else
           return false;
       
    }
    
}