package progettose_gruppo16;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *  RulesChecker rappresenta il thread eseguito in background per il controllo automatico delle regole ogni 5 secondi.
 */
public class RulesChecker implements Runnable{
    
    private ObservableList<Rule> rules;
    private final int waitTime = 5;
    private List<Rule> rulesList;

    /**
     *  Costruttore
     * @param rules -> Lista delle regole da verificare
     */
    public RulesChecker(ObservableList<Rule> rules) {
        this.rules = rules;
        rulesList = new ArrayList<>();
    }

    /**
     * Metodo run implementato dall'interfaccia Runnable
     * Si salva l'istanza attuale della tabella delle regole attive e le controlla.
     * Poi, si mette in attesa per il tempo specificato
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