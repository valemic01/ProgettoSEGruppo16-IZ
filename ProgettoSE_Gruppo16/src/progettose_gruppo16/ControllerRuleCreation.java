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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import progettose_gruppo16.handlerTrigger.HandlerComposite;

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
    
    private final HandlerComposite h15 = new HandlerComposite();
    private final HandlerTimeOfDayTrigger h7 = new HandlerTimeOfDayTrigger();
    private final HandlerDayOfWeekTrigger h8 = new HandlerDayOfWeekTrigger();
    private final HandlerDayOfMonthTrigger h9= new HandlerDayOfMonthTrigger();
    private final HandlerDateTrigger h10= new HandlerDateTrigger();
    private final HandlerFileSizeTrigger h11 = new HandlerFileSizeTrigger();
    private final HandlerExistingFileTrigger h12= new HandlerExistingFileTrigger();
    private final HandlerExitStatusTrigger h13= new HandlerExitStatusTrigger(); 
    
    
    @FXML
    private ComboBox<String> trigDD2;
    @FXML
    private ComboBox<String> trigDD3;
    @FXML
    private AnchorPane triggerPane2;
    @FXML
    private AnchorPane triggerPane3;
    @FXML
    private AnchorPane triggerPane4;
    @FXML
    private AnchorPane triggerPane5;
    @FXML
    private AnchorPane triggerPane6;
    @FXML
    private AnchorPane triggerPane7;
    @FXML
    private ComboBox<String> trigDD4;
    @FXML
    private ComboBox<String> trigDD5;
    @FXML
    private ComboBox<String> trigDD6;
    @FXML
    private ComboBox<String> trigDD7;
    @FXML
    private AnchorPane composite1;
    @FXML
    private AnchorPane composite2;
    @FXML
    private AnchorPane composite3;
    @FXML
    private AnchorPane triggerPane1;
    @FXML
    private ComboBox<String> logicalOpsDD1;
    @FXML
    private ComboBox<String> logicalOpsDD2;
    @FXML
    private ComboBox<String> logicalOpsDD3;
    @FXML
    private VBox notVBox;
    @FXML
    private CheckBox not2;
    @FXML
    private CheckBox not3;
    @FXML
    private CheckBox not4;
    @FXML
    private CheckBox not5;
    @FXML
    private CheckBox not6;
    @FXML
    private CheckBox not7;
    @FXML
    private Label errorMessage;

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
        initializeTrigDD(trigDD1);
        initializeTrigDD(trigDD2);
        initializeTrigDD(trigDD3);
        initializeTrigDD(trigDD4);
        initializeTrigDD(trigDD5);
        initializeTrigDD(trigDD6);
        initializeTrigDD(trigDD7);
        
        initializeLogOpsDD(logicalOpsDD1);
        initializeLogOpsDD(logicalOpsDD2);
        initializeLogOpsDD(logicalOpsDD3);
        
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
        
        composite1.visibleProperty().bind(Bindings.createBooleanBinding(() ->"Composite".equals(trigDD1.getSelectionModel().getSelectedItem()),trigDD1.getSelectionModel().selectedItemProperty()));
        triggerPane2.visibleProperty().bind(composite1.visibleProperty());
        triggerPane3.visibleProperty().bind(composite1.visibleProperty());
        not2.visibleProperty().bind(composite1.visibleProperty());
        not3.visibleProperty().bind(composite1.visibleProperty());
        
        composite2.visibleProperty().bind(Bindings.createBooleanBinding(() ->"Composite".equals(trigDD2.getSelectionModel().getSelectedItem()),trigDD2.getSelectionModel().selectedItemProperty()).and(composite1.visibleProperty()));
        triggerPane4.visibleProperty().bind(composite2.visibleProperty());
        triggerPane5.visibleProperty().bind(composite2.visibleProperty());
        not4.visibleProperty().bind(composite2.visibleProperty());
        not5.visibleProperty().bind(composite2.visibleProperty());

        composite3.visibleProperty().bind(Bindings.createBooleanBinding(() ->"Composite".equals(trigDD3.getSelectionModel().getSelectedItem()),trigDD3.getSelectionModel().selectedItemProperty()).and(composite1.visibleProperty()));
        triggerPane6.visibleProperty().bind(composite3.visibleProperty());
        triggerPane7.visibleProperty().bind(composite3.visibleProperty());
        not6.visibleProperty().bind(composite3.visibleProperty());
        not7.visibleProperty().bind(composite3.visibleProperty());
        
        //creation of the handlers chains
        //action
        h1.setNext(h2);
        h2.setNext(h3);
        h3.setNext(h4);
        h4.setNext(h5);
        h5.setNext(h6);
        h6.setNext(h14);
        
        //trigger
        h15.setNext(h7);
        h7.setNext(h8);
        h8.setNext(h9);
        h9.setNext(h10);
        h10.setNext(h11);
        h11.setNext(h12);
        h12.setNext(h13);
    }
    
    private void initializeTrigDD(ComboBox<String> cb){
        cb.getItems().add("Time of day");
        cb.getItems().add("Day of the week");
        cb.getItems().add("Day of month");
        cb.getItems().add("Date");
        cb.getItems().add("File size");
        cb.getItems().add("Existing file");
        cb.getItems().add("Exit status");
        if(Integer.parseInt(cb.getId().substring(6))<4)
            cb.getItems().add("Composite");
    }
    
    private void initializeLogOpsDD(ComboBox<String> cb){
        cb.getItems().add("AND");
        cb.getItems().add("OR");
        cb.setValue("AND");
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
        trigger = h15.handleBehaviour(triggerPane1, h15, 1, notVBox);
        
        //get che selected action from the action handlers chain
        action = h1.handleBehaviour(actionPane);
        
        if(action == null || trigger == null){
            errorMessage.setVisible(true);
            return;
        }
        
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
        h1.handleGUI(actionPane, actionDD1);
    }

    @FXML
    private void chooseTrigger(ActionEvent event) {
        ComboBox<String> cb = (ComboBox<String>) event.getSource();
        String n = cb.getId().substring(6);
        AnchorPane ap;
        switch (n) {
            case "1":
                ap = triggerPane1;
                break;
            case "2":
                ap = triggerPane2;
                break;
            case "3":
                ap = triggerPane3;
                break;
            case "4":
                ap = triggerPane4;
                break;
            case "5":
                ap = triggerPane5;
                break;
            case "6":
                ap = triggerPane6;
                break;
            default:
                ap = triggerPane7;
                break;
        }
        h15.handleGUI(ap, cb);
    }
    
}