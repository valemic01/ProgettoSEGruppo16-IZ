package progettose_gruppo16;

import progettose_gruppo16.handlerTrigger.HandlerTimeOfDayTrigger;
import progettose_gruppo16.handlerTrigger.HandlerExistingFileTrigger;
import progettose_gruppo16.handlerTrigger.HandlerFileSizeTrigger;
import progettose_gruppo16.handlerTrigger.HandlerDateTrigger;
import progettose_gruppo16.handlerTrigger.HandlerDayOfWeekTrigger;
import progettose_gruppo16.handlerTrigger.HandlerDayOfMonthTrigger;
import progettose_gruppo16.handlerTrigger.HandlerExitStatusTrigger;
import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.handlerAction.HandlerMoveFileAction;
import progettose_gruppo16.handlerAction.HandlerShowMessageAction;
import progettose_gruppo16.handlerAction.HandlerPlayAudioAction;
import progettose_gruppo16.handlerAction.HandlerDeleteFileAction;
import progettose_gruppo16.handlerAction.HandlerCopyFileAction;
import progettose_gruppo16.handlerAction.HandlerExecuteProgramAction;
import progettose_gruppo16.handlerAction.HandlerAppendStringToFileAction;
import progettose_gruppo16.action.Action;
import java.io.IOException;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
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
    private Label notValidSleep;
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
    private HandlerExecuteProgramAction h14= new HandlerExecuteProgramAction();
    
    private HandlerTimeOfDayTrigger h7 = new HandlerTimeOfDayTrigger();
    private HandlerDayOfWeekTrigger h8 = new HandlerDayOfWeekTrigger();
    private HandlerDayOfMonthTrigger h9= new HandlerDayOfMonthTrigger();
    private HandlerDateTrigger h10= new HandlerDateTrigger();
    private HandlerFileSizeTrigger h11 = new HandlerFileSizeTrigger();
    private HandlerExistingFileTrigger h12= new HandlerExistingFileTrigger();
    private HandlerExitStatusTrigger h13= new HandlerExitStatusTrigger();      
    
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
        trigDD1.getItems().add("Day of month");
        trigDD1.getItems().add("Date");
        trigDD1.getItems().add("File size");
        trigDD1.getItems().add("Existing file");
        trigDD1.getItems().add("Exit status");
        
        actionDD1.getItems().add("Show message");
        actionDD1.getItems().add("Play audio");
        actionDD1.getItems().add("Move file");
        actionDD1.getItems().add("Delete file");
        actionDD1.getItems().add("Copy file");
        actionDD1.getItems().add("Add text to file");
        actionDD1.getItems().add("Execute program");
        
        //binding to show the sleeping period settings when the "Repetable" check box is selected
        sleepingPeriodPane.visibleProperty().bind(repetableCB.selectedProperty());
        
        //binding to disable the add rule button if the rule name, the action or the trigger are not selected
        addRuleBtn.disableProperty().bind(Bindings.isEmpty(ruleNameTxtBox.textProperty()).or(Bindings.isNull(trigDD1.valueProperty())).or(Bindings.isNull(actionDD1.valueProperty())).or((Bindings.isEmpty(slepPerDays.textProperty()).or(Bindings.isEmpty(slepPerHours.textProperty())).or(Bindings.isEmpty(slepPerMins.textProperty()))).and(sleepingPeriodPane.visibleProperty())));
        
        //creation of the handlers chains
        //action
        h1.setNext(h2);
        h2.setNext(h3);
        h3.setNext(h4);
        h4.setNext(h5);
        h5.setNext(h6);
        h6.setNext(h14);
        
        //trigger
        h7.setNext(h8);
        h8.setNext(h9);
        h9.setNext(h10);
        h10.setNext(h11);
        h11.setNext(h12);
        h12.setNext(h13);
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
        
        Rule rule;
        Action action;
        Trigger trigger;
        
        String name = ruleNameTxtBox.getText();
        
        LocalTime sleepPeriod;
        int days;
        
        //get che selected trigger from the trigger handlers chain
        trigger = h7.handleBehaviour(triggerPane);
        
        //get che selected action from the action handlers chain
        action = h1.handleBehaviour(actionPane);
        
        if(action == null || trigger == null)
            return;
        
        if(repetableCB.isSelected()){
            if(!slepPerHours.getText().matches("^(0?\\d|1\\d|2[0-3])$") || !slepPerMins.getText().matches("^(0?[0-9]|[1-5][0-9])$") || !slepPerDays.getText().matches("\\d*")){
                notValidSleep.setVisible(true);
                return;
            }else{
                sleepPeriod = LocalTime.of(Integer.parseInt(slepPerHours.getText()), Integer.parseInt(slepPerMins.getText()));
                days = Integer.parseInt(slepPerDays.getText());
                rule = new RepeatableRule(name, trigger, action, days, sleepPeriod);
            } 
        }else
            rule = new OneTimeRule(name, trigger, action);
                
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
        h1.handleGUI(actionPane, actionDD1.getValue(), addRuleBtn);
    }

    @FXML
    private void chooseTrigger(ActionEvent event) {
        h7.handleGUI(triggerPane, trigDD1.getValue(), addRuleBtn);
    }
    
}