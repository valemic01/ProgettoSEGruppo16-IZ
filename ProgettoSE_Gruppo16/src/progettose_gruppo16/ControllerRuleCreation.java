package progettose_gruppo16;

import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *  Implementazione del controller che gestisce la schermata di creazione delle regole
 */
public class ControllerRuleCreation implements Initializable {

    @FXML
    private AnchorPane ruleCreatePane;
    @FXML
    private TabPane trigg_actTab;
    @FXML
    private Tab triggerTab;
    @FXML
    private ComboBox<String> trigDD1;
    @FXML
    private Tab actionTab;
    @FXML
    private ComboBox<String> actionDD1;
    @FXML
    private Button addRuleBtn;
    @FXML
    private CheckBox repetableCB;
    @FXML
    private AnchorPane sleepingPeriodPane;
    @FXML
    private TextField slepPerDays;
    @FXML
    private TextField slepPerHours;
    @FXML
    private TextField slepPerMins;
    @FXML
    private TextField ruleNameTxtBox;
    @FXML
    private Label notValidName;
    @FXML
    private AnchorPane actionPane;
      
    private Time sleepingPeriod;
    private RulesManager ruleManager;   
    private ObservableList<Rule> allRulesList; //observable list of all rules, created to manage the respective TableView  
    private Stage stage; 
    
    private HandlerShowMessageAction h1 = new HandlerShowMessageAction();
    private HandlerPlayAudioAction h2 = new HandlerPlayAudioAction();
    private HandlerCopyFileAction h3 = new HandlerCopyFileAction();
    private HandlerMoveFileAction h4 = new HandlerMoveFileAction();
    private HandlerDeleteFileAction h5 = new HandlerDeleteFileAction();
    private HandlerAppendStringToFileAction h6 = new HandlerAppendStringToFileAction();
    private HandlerTimeOfDayTrigger h7 = new HandlerTimeOfDayTrigger();
    private HandlerDayOfWeekTrigger h8 = new HandlerDayOfWeekTrigger();
    
    @FXML
    private AnchorPane triggerPane;

    /**
     *  Inizializzazione delle componenti dell'interfaccia utente
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //get RulesManager instance (Singleton) and allRulesList
        ruleManager = RulesManager.getInstance();
        
        allRulesList = ruleManager.getAllRulesList();
        
        //inizialization of the combo boxes for trigger and actions
        trigDD1.getItems().add("Time of day");
        trigDD1.getItems().add("Day of the week");
        actionDD1.getItems().add("Show message");
        actionDD1.getItems().add("Play audio");
        actionDD1.getItems().add("Move file");
        actionDD1.getItems().add("Delete file");
        actionDD1.getItems().add("Copy file");
        actionDD1.getItems().add("Add text to file");
        
        //binding to show the sleeping period settings when the "Repetable" check box is selected
        sleepingPeriodPane.visibleProperty().bind(repetableCB.selectedProperty());
        
        //binding to disable the add rule button if the rule name, the action or the trigger are not selected
        addRuleBtn.disableProperty().bind(Bindings.isEmpty(ruleNameTxtBox.textProperty()).or(Bindings.isNull(trigDD1.valueProperty())).or(Bindings.isNull(actionDD1.valueProperty())));
        
        //creation of the handlers chains
        h1.setNext(h2);
        h2.setNext(h3);
        h3.setNext(h4);
        h4.setNext(h5);
        h5.setNext(h6);
        
        h7.setNext(h8);
    }    

    /**
     * Metodo che consente di passare dalla schermata di creazione delle regole alla
     * schermata che mostra tutte le regole definite dall'utente. 
     * @param event 
     */
    @FXML
    private void goBack(ActionEvent event) throws IOException {
        
        stage = (Stage) ruleCreatePane.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLRuleTable.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Metodo che consente di passare dalla schermata dei trigger a quella delle azioni.
     * @param event 
     */
    @FXML
    private void toAction(ActionEvent event) {
        trigg_actTab.getSelectionModel().select(actionTab);
    }
    
    /**
     * Metodo che consente di passare dalla schermata delle azioni a quella dei trigger.
     * @param event 
     */
    @FXML
    private void toTrigger(ActionEvent event) {
        trigg_actTab.getSelectionModel().select(triggerTab);
    }

    /**
     * Metodo che recupera le informazioni necessarie per la creazione della regola e invoca il
     * corrispettivo metodo di RulesManager.
     * Controlla che tutti i campi siano stati riempiti correttamente e che i nomi assegnati alle regole siano identificatori univoci.
     * @param event 
     */
    @FXML
    private void addRuleAction(ActionEvent event) throws IOException {
        Action action;
        Trigger trigger;
        
        String hours;
        String name = ruleNameTxtBox.getText();
              
        if(repetableCB.isSelected()){
            hours = String.valueOf(Integer.parseInt(slepPerDays.getText())*24 + Integer.parseInt(slepPerHours.getText()));
            sleepingPeriod = Time.valueOf(hours + ":" + slepPerMins.getText() + ":00");
        }
        
        //get che selected trigger from the trigger handlers chain
        trigger = h7.handleBehaviour(triggerPane);
        
        //get che selected action from the action handlers chain
        action = h1.handleBehaviour(actionPane);
        
        if(action == null)
            return;
        
        Rule rule = new Rule(name, trigger, action, repetableCB.isSelected(), sleepingPeriod);
        
        //check if the rule name is valid (not already existing)
        for(Rule r: allRulesList){
            if(r.getName().equals(rule.getName())){
                notValidName.setVisible(true);
                return;
            }
        }
  
        ruleManager.addRule(rule);
        goBack(null);
        
    }

    @FXML
    private void chooseAction(ActionEvent event) {
        h1.handleGUI(actionPane, actionDD1.getValue());
    }

    @FXML
    private void chooseTrigger(ActionEvent event) {
        h7.handleGUI(triggerPane, trigDD1.getValue());
    }
    
}