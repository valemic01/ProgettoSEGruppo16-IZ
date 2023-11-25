package progettose_gruppo16;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;

/**
 *  RulesChecker rappresenta il thread eseguito in background per il controllo automatico delle regole ogni 5 secondi.
 */
public class RulesChecker implements Runnable{
    
    private ObservableList<Rule> rules;
    private final int waitTime = 5;

    /**
     *  Costruttore
     * @param rules -> Lista delle regole da verificare
     */
    public RulesChecker(ObservableList<Rule> rules) {
        this.rules = rules;
    }

    /**
     * Metodo run implementato dall'interfaccia Runnable
     * Acquisisce il mutex sulla tabella delle regole, in modo che non possa essere modificata durante il controllo,
     * e poi la scorre per verificare tutte le condizioni e, eventualmente, eseguire le azioni.
     * Poi, si mette in attesa per il tempo specificato
     */
    @Override
    public void run() {
        
        while(!Thread.currentThread().isInterrupted()){
                synchronized(rules){
                    for(Rule r : rules)
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