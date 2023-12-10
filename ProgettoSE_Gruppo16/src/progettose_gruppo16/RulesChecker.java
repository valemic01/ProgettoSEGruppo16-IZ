package progettose_gruppo16;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 * RulesChecker represents the background-executed thread for automatic rule checking every 5 seconds.
 */
public class RulesChecker implements Runnable{
    
    private ObservableList<Rule> rules;
    private final int waitTime = 5;
    private List<Rule> rulesList;

    /**
     * Constructor
     * @param rules -> List of rules to be checked
     */
    public RulesChecker(ObservableList<Rule> rules) {
        this.rules = rules;
        rulesList = new ArrayList<>();
    }

    /**
     * Run method implemented from the Runnable interface
     * Saves the current instance of the active rules table and checks them.
     * Then, it waits for the specified time.
     */
    @Override
    public void run() {
        
        while(!Thread.currentThread().isInterrupted()){
            rulesList.clear();
            rulesList.addAll(rules);
                synchronized(rulesList){
                    for(Rule r : rulesList)
                        r.evaluate();
                }
                try{
                    Thread.sleep(waitTime*1000);
                } catch (InterruptedException ex) {
                Logger.getLogger(RulesChecker.class.getName()).log(Level.SEVERE, null, ex);
                }
                
        }
        
    }
    
}