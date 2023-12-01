package progettose_gruppo16;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *  Classe singleton per la gestione delle regole.
 */
public class RulesManager {
    
    private static RulesManager instance; 
    
    private ObservableList<Rule> allRulesList; //observable list of all rules, created to manage the respective TableView
    private ObservableList<Rule> activeRulesList; //observable list of active rules, created to manage the respective TableView
    private ObservableList<Rule> inactRulesList; //observable list of inactive rules, created to manage the respective TableView
    private RulesChecker rulesChecker;
    private Thread threadRulesChecker;  
    private final String backupFile = "RulesBackup.dat";

    private RulesManager() {
        
        //Creation of the lists
        this.allRulesList = FXCollections.observableArrayList();
        this.activeRulesList = FXCollections.observableArrayList();
        this.inactRulesList = FXCollections.observableArrayList();
        
        //Initialization and start of the thread for automatic condition checking
        rulesChecker = new RulesChecker(activeRulesList);
        threadRulesChecker = new Thread(rulesChecker);
        threadRulesChecker.setName("Thread Rules Checker");
        threadRulesChecker.setDaemon(true);
        threadRulesChecker.start();
        
        //Import from backup file
        if(new File(backupFile).exists()){
            try {
                importRulesFromFile();
            } catch (IOException ex) {
                Logger.getLogger(ControllerRuleTable.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /**
     *  Metodo per gestire l'unica istanza del RulesManager.
     *  Se l'istanza esiste già viene restituita, altrimenti viene creata.
     * @return instance -> l'unica istanza del RulesManager
     */
    public static RulesManager getInstance(){
        if (instance == null)
            instance = new RulesManager();
        return instance;
    }
    
    /**
     *  Metodo che importa le regole dal file di backup all'avvio dell'applicazione.
     */
    private void importRulesFromFile() throws IOException{
        
        ObjectInputStream ois = null;
        ArrayList importedRules = new ArrayList();

        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupFile)));
            importedRules = (ArrayList) ois.readObject();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            Logger.getLogger(ControllerRuleTable.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ois.close();
        }
 
        allRulesList.addAll(importedRules);
        for(Rule r : allRulesList){
            if(r.isActive())
                activeRulesList.add(r);
            else
                inactRulesList.add(r);
        }
        
    }
    
    /**
     * Metodo che crea il service che salva le regole sul file di backup
     * ad ogni modifica sulla tabella delle regole.
     */
    private void saveRules(){
        BackupFileService backupService = new BackupFileService(allRulesList, backupFile);
        backupService.start();
    }
    
    /**
     * Metodo che cancella una regola selezionata dalla lista delle regole e aggiorna le tabelle correlate.
     * Invoca il metodo saveRules() per aggiornare la lista delle regole presente sul file di backup.
     * @param r -> la regola che l'utente vuole eliminare
     */
    public void deleteRule(Rule r) {
        
        if(r == null)
            return;
        
        if(r.isActive())
            activeRulesList.remove(r);
        else
            inactRulesList.remove(r);
        
        allRulesList.remove(r);
        saveRules();
    }
    
    /**
     * Metodo che consente di aggiornare tutte le tabelle 
     * quando l'utente sceglie di attivare o disattivare una regola.
     * Invoca il metodo corrispondente in base all'azione da compiere.
     * @param r -> la regola che l'utente vuole attivare/disattivare
     */
    public void activeInactivateRule(Rule r) {
        
        if(r == null)
            return;

        if(r.isActive())
            this.inactivateRule(r);
        else
            this.activateRule(r);
        
    }
    
    /**
     * Metodo che consente di aggiornare tutte le tabelle quando l'utente sceglie
     * di disattivare una regola dalla tabella delle regole attive.
     * @param r -> la regola che l'utente vuole disattivare
     */
    public void inactivateRule(Rule r) {

        activeRulesList.remove(r);
        inactRulesList.add(r);
        r.setActive(false);
        
        saveRules();
    }
    
    /**
     * Metodo che consente di aggiornare tutte le tabelle quando l'utente sceglie
     * di attivare una regola dalla tabella delle regole disattivate.
     * @param r -> la regola che l'utente vuole attivare
     */
    public void activateRule(Rule r) {
      
        inactRulesList.remove(r);
        activeRulesList.add(r);
        r.setActive(true);
        if(r instanceof RepeatableRule)
            ((RepeatableRule) r).setFireable(true);
        
        saveRules();
    }
    
    /**
     * Metodo che consente di aggiungere una regola all'interno della lista delle regole
     * e salva il nuovo contenuto
     * @param r -> la regola che l'utente vuole aggiungere
     */
    public void addRule(Rule r) {
  
        allRulesList.add(r);
        activeRulesList.add(r);
        saveRules(); 
        
    }
    
    /**
     * Metodo per cambiare il valore del parametro fireable delle regole ripetibili.
     * Quando una regola ripetibile viene attivata, fireable viene settato a false.
     * Quando lo sleeping period termina, fireable viene settato a true.
     * @param r 
     */
    public void changeFireable(RepeatableRule r){
        
        if(r.isFireable())
            r.setFireable(false);
        else
            r.setFireable(true);
        
        saveRules();
        
    }

    public ObservableList<Rule> getAllRulesList() {
        return allRulesList;
    }

    public ObservableList<Rule> getActiveRulesList() {
        return activeRulesList;
    }

    public ObservableList<Rule> getInactRulesList() {
        return inactRulesList;
    }
    
}