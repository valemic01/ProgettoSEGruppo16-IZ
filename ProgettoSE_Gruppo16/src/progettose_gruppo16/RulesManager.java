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
 * Singleton class for rule management.
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
     * Method to manage the singleton instance of RulesManager.
     * If the instance already exists, it is returned; otherwise, it is created.
     * @return instance -> the only instance of RulesManager
     */
    public static RulesManager getInstance(){
        if (instance == null)
            instance = new RulesManager();
        return instance;
    }
    
    /**
     * Method that imports rules from the backup file when the application starts.
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
     * Method that creates the service to save rules to the backup file
     * every time there is a modification on the rule table.
     */
    private void saveRules(){
        BackupFileService backupService = new BackupFileService(allRulesList, backupFile);
        backupService.start();
    }
    
    /**
     * Method that deletes a selected rule from the rule list and updates related tables.
     * Invokes the saveRules() method to update the list of rules on the backup file.
     * @param r -> the rule the user wants to delete
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
     * Method that allows updating all tables 
     * when the user chooses to activate or deactivate a rule.
     * Invokes the corresponding method based on the action to be performed.
     * @param r -> the rule the user wants to activate/deactivate
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
     * Method that allows updating all tables when the user chooses
     * to deactivate a rule from the active rule table.
     * @param r -> the rule the user wants to deactivate
     */
    public void inactivateRule(Rule r) {

        activeRulesList.remove(r);
        inactRulesList.add(r);
        r.setActive(false);
        
        saveRules();
    }
    
    /**
     * Method that allows updating all tables when the user chooses
     * to activate a rule from the inactive rule table.
     * @param r -> the rule the user wants to activate
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
     * Method that allows adding a rule to the rule list
     * and saves the new content
     * @param r -> the rule the user wants to add
     */
    public void addRule(Rule r) {
  
        allRulesList.add(r);
        activeRulesList.add(r);
        saveRules(); 
        
    }
    
    /**
     * Method to change the value of the "fireable" parameter for repeatable rules.
     * When a repeatable rule is activated, "fireable" is set to false.
     * When the sleeping period ends, "fireable" is set to true.
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