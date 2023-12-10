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
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
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
import progettose_gruppo16.action.ActionSequence;
import progettose_gruppo16.handlerAction.BaseHandlerAction;
import progettose_gruppo16.handlerTrigger.BaseHandlerTrigger;
import progettose_gruppo16.handlerTrigger.HandlerComposite;

/**
 * Implementation of the controller that manages the rule creation screen
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
    private Button addRuleBtn;
    @FXML
    private AnchorPane sleepingPeriodPane;
    @FXML
    private CheckBox repeatableCB;
    @FXML
    private TextField sleepPerDays;
    @FXML
    private TextField sleepPerHours;
    @FXML
    private TextField sleepPerMins;
    @FXML
    private TextField ruleNameTxtBox;
    @FXML
    private Label notValidName;
    @FXML
    private Label notValidSleep;
    @FXML
    private AnchorPane actionPane1;
    @FXML
    private AnchorPane actionPane2;
    @FXML
    private AnchorPane actionPane3;
    @FXML
    private AnchorPane actionPane4;
    @FXML
    private Button addActionBtn1;
    @FXML
    private Button addActionBtn2;
    @FXML
    private Button addActionBtn3;
    @FXML
    private ComboBox<String> actionDD1;
    @FXML
    private ComboBox<String> actionDD2;
    @FXML
    private ComboBox<String> actionDD3;
    @FXML
    private ComboBox<String> actionDD4;
    @FXML
    private Button deleteActionBtn1;
    @FXML
    private Button deleteActionBtn2;   
    @FXML
    private Button deleteActionBtn3;     
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
       
    private RulesManager ruleManager;   
    private ObservableList<Rule> allRulesList; //observable list of all rules, created to manage the respective TableView  
    private Stage stage;   
    private List<BaseHandlerAction> handlerAction1 = new LinkedList<>();
    private List<BaseHandlerAction> handlerAction2 = new LinkedList<>();;
    private List<BaseHandlerAction> handlerAction3 = new LinkedList<>();;
    private List<BaseHandlerAction> handlerAction4 = new LinkedList<>();;
    private List<BaseHandlerAction> startHandlersAction = new LinkedList<>();
    private List<AnchorPane> anchorPanesAction = new LinkedList<>();  
    private List<BaseHandlerTrigger> handlerTrigger = new LinkedList<>();
    private List<AnchorPane> anchorPanesTrigger = new LinkedList<>();
    private int numAction = 1;
    
    /**
     * Initialization of the user interface components
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //get RulesManager instance (Singleton) and allRulesList
        ruleManager = RulesManager.getInstance();
        
        allRulesList = ruleManager.getAllRulesList();
        
        //inizialization of the combo boxes for trigger and actions
        initializeHandlerTrigger(handlerTrigger);
        initializeComboBoxTrigger();      
        initializeLogOpsDD();
        
        initializeComboBoxAction();
        initializeHandlerAction(handlerAction1);
        anchorPanesAction.add(actionPane1);
        
        //binding to show the sleeping period settings when the "Repetable" check box is selected
        sleepingPeriodPane.visibleProperty().bind(repeatableCB.selectedProperty());
        
        //binding to disable the add rule button if the rule name, the action or the trigger are not selected
        addRuleBtn.disableProperty().bind(Bindings.isEmpty(ruleNameTxtBox.textProperty()).or(Bindings.isNull(trigDD1.valueProperty())).or(Bindings.isNull(actionDD1.valueProperty())).or((Bindings.isEmpty(sleepPerDays.textProperty()).or(Bindings.isEmpty(sleepPerHours.textProperty())).or(Bindings.isEmpty(sleepPerMins.textProperty()))).and(sleepingPeriodPane.visibleProperty())));
        
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
        
    }
 
    private void initializeLogOpsDD(){
        logicalOpsDD1.getItems().addAll("AND", "OR");
        logicalOpsDD1.setValue("AND");
        logicalOpsDD2.getItems().addAll("AND", "OR");
        logicalOpsDD2.setValue("AND");
        logicalOpsDD3.getItems().addAll("AND", "OR");
        logicalOpsDD3.setValue("AND");
    }

    /**
     * Method to transition from the rule creation screen to the one showing all rules defined by the user.
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
     * Method to transition from the trigger screen to the action screen.
     * @param event 
     */
    @FXML
    private void toAction(ActionEvent event) {
        trigg_actTab.getSelectionModel().select(actionTab);
    }
    
    /**
     * Method to transition from the action screen to the trigger screen.
     * @param event 
     */
    @FXML
    private void toTrigger(ActionEvent event) {
        trigg_actTab.getSelectionModel().select(triggerTab);
    }

    /**
     * Method to retrieve the necessary information for rule creation and invoke the corresponding method of RulesManager.
     * Checks that all fields are filled in correctly and that the names assigned to the rules are unique identifiers.
     * @param event 
     */
    @FXML
    private void addRuleAction(ActionEvent event) throws IOException {
        
        Rule rule;
        ActionSequence action = new ActionSequence();
        Trigger trigger;
        String name = ruleNameTxtBox.getText();        
        LocalTime sleepPeriod;
        int days;
        
        //get che selected trigger from the trigger handlers chain
        trigger = handlerTrigger.get(0).handleBehaviour(triggerPane1, handlerTrigger.get(0), 1, notVBox);
        
        //get che selected action from the action handlers chain
        for(int i = 0; i < numAction; i++){
            Action a = startHandlersAction.get(i).handleBehaviour(anchorPanesAction.get(i));
            if(a==null){
                errorMessage.setVisible(true);
                return;
            }else
                action.addAction(a);
            
        }
            
        if(action.getSequence().isEmpty() || trigger == null){
            errorMessage.setVisible(true);
            return;
        }
        
        if(repeatableCB.isSelected()){
            if(!sleepPerHours.getText().matches("^(0?\\d|1\\d|2[0-3])$") || !sleepPerMins.getText().matches("^(0?[0-9]|[1-5][0-9])$") || !sleepPerDays.getText().matches("\\d*")){
                notValidSleep.setVisible(true);
                return;
            }else{
                sleepPeriod = LocalTime.of(Integer.parseInt(sleepPerHours.getText()), Integer.parseInt(sleepPerMins.getText()));
                days = Integer.parseInt(sleepPerDays.getText());
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

    /**
     * Method to handle the user's choice of trigger.
     * @param event 
     */
    @FXML
    private void chooseTrigger(ActionEvent event) {
        ComboBox<String> cb = (ComboBox<String>) event.getSource();
        Integer i = Integer.parseInt(cb.getId().substring(6));
        AnchorPane ap;
        
        ap = anchorPanesTrigger.get(i-1);
        handlerTrigger.get(0).handleGUI(ap, cb);
    }

    /**
     * Method to handle the user's choice of the first action.
     * @param event 
     */
    @FXML
    private void chooseAction1(ActionEvent event) {
        if(actionDD1.getValue() != null)
            handlerAction1.get(0).handleGUI(actionPane1, actionDD1);
    }
    
    /**
     * Method to handle the user's choice of the second action.
     * @param event 
     */
    @FXML
    private void chooseAction2(ActionEvent event) {
        if(actionDD2.getValue() != null)
            handlerAction2.get(0).handleGUI(actionPane2, actionDD2);
    }
    
    /**
     * Method to handle the user's choice of the third action.
     * @param event 
     */
    @FXML
    private void chooseAction3(ActionEvent event) {
        if(actionDD3.getValue() != null)
            handlerAction3.get(0).handleGUI(actionPane3, actionDD3);
    }
    
    /**
    * Method to handle the user's choice of the fourth action.
    * @param event 
    */
    @FXML
    private void chooseAction4(ActionEvent event) {
        if(actionDD4.getValue() != null)
            handlerAction4.get(0).handleGUI(actionPane4, actionDD4);
    }
    
    /**
    * Method to handle the first button to add a new action.
    * @param event 
    */
    @FXML
    private void onAddAction1(ActionEvent event) {
        initializeHandlerAction(handlerAction2);
        anchorPanesAction.add(actionPane2);
        actionDD2.setVisible(true);
        addActionBtn2.setVisible(true);
        addActionBtn1.setVisible(false);
        deleteActionBtn1.setVisible(true);
        numAction++;
    }

    /**
    * Method to handle the second button to add a new action.
    * @param event 
    */
    @FXML
    private void onAddAction2(ActionEvent event) {
        initializeHandlerAction(handlerAction3);
        anchorPanesAction.add(actionPane3);
        actionDD3.setVisible(true);
        addActionBtn3.setVisible(true);
        addActionBtn2.setVisible(false);
        deleteActionBtn1.setVisible(false);
        deleteActionBtn2.setVisible(true);
        numAction++;
    }
    
    /**
    * Method to handle the third button to add a new action.
    * @param event 
    */
    @FXML
    private void onAddAction3(ActionEvent event) {   
        initializeHandlerAction(handlerAction4);
        anchorPanesAction.add(actionPane4);
        actionDD4.setVisible(true);
        deleteActionBtn3.setVisible(true);
        addActionBtn3.setVisible(false);
        deleteActionBtn2.setVisible(false);
        numAction++;
    }
    
    /**
    * Method to handle the first button to delete an action.
    * @param event 
    */
    @FXML
    private void onDeleteAction1(ActionEvent event) {
        actionPane2.getChildren().clear();
        actionDD2.setVisible(false);
        actionDD2.getSelectionModel().clearSelection();
        deleteActionBtn1.setVisible(false);
        addActionBtn2.setVisible(false);
        addActionBtn1.setVisible(true);
        numAction--;
    }
  
    /**
    * Method to handle the second button to delete an action.
    * @param event 
    */
    @FXML
    private void onDeleteAction2(ActionEvent event) {
        actionPane3.getChildren().clear();
        actionDD3.setVisible(false);
        actionDD3.getSelectionModel().clearSelection();
        deleteActionBtn2.setVisible(false);
        addActionBtn3.setVisible(false);
        addActionBtn2.setVisible(true);
        deleteActionBtn1.setVisible(true);
        numAction--;
    }

    /**
    * Method to handle the third button to delete an action.
    * @param event 
    */
    @FXML
    private void onDeleteAction3(ActionEvent event) {
        actionPane4.getChildren().clear();
        actionDD4.getSelectionModel().clearSelection();
        actionDD4.setVisible(false);
        deleteActionBtn3.setVisible(false);
        deleteActionBtn2.setVisible(true);
        addActionBtn3.setVisible(true);
        numAction--;
    }
    
    /**
    * Method to initialize the ComboBoxes for actions.
    */
    private void initializeComboBoxAction(){
        actionDD1.getItems().addAll("Show message", "Play audio", "Move file", "Delete file", "Copy file", "Add text to file", "Execute program");
        actionDD2.getItems().addAll("Show message", "Play audio", "Move file", "Delete file", "Copy file", "Add text to file", "Execute program");
        actionDD3.getItems().addAll("Show message", "Play audio", "Move file", "Delete file", "Copy file", "Add text to file", "Execute program");
        actionDD4.getItems().addAll("Show message", "Play audio", "Move file", "Delete file", "Copy file", "Add text to file", "Execute program");
    }
    
    /**
    * Method to initialize the lists containing action handlers.
    * @param list -> the list of handlers to initialize
    */
    private void initializeHandlerAction(List<BaseHandlerAction> list){
        list.add(new HandlerShowMessageAction());
        list.add(new HandlerPlayAudioAction());
        list.add(new HandlerCopyFileAction());
        list.add(new HandlerMoveFileAction());
        list.add(new HandlerDeleteFileAction());
        list.add(new HandlerAppendStringToFileAction());
        list.add(new HandlerExecuteProgramAction());
        
        setNextHandlerAction(list);
        startHandlersAction.add(list.get(0));
    }
    
    /**
    * Method to set the chain of actions.
    * @param list 
    */
    private void setNextHandlerAction(List<BaseHandlerAction> list){
        for(int i=0; i<list.size()-1; i++){
            list.get(i).setNext(list.get(i+1));
        }
    }
    
    /**
    * Method to initialize the lists containing trigger handlers.
    * @param list -> the list of handlers to initialize
    */
    private void initializeHandlerTrigger(List<BaseHandlerTrigger> list){
        list.add(new HandlerComposite());
        list.add(new HandlerTimeOfDayTrigger());
        list.add(new HandlerDayOfWeekTrigger());
        list.add(new HandlerDayOfMonthTrigger());
        list.add(new HandlerDateTrigger());
        list.add(new HandlerFileSizeTrigger());
        list.add(new HandlerExistingFileTrigger());
        list.add(new HandlerExitStatusTrigger());     
        
        for(int i=0; i<list.size()-1; i++){
            list.get(i).setNext(list.get(i+1));
        }
    }
    
    /**
    * Method to initialize the ComboBoxes for triggers.
    */
    private void initializeComboBoxTrigger(){
        trigDD1.getItems().addAll("Time of day", "Day of the week", "Day of month", "Date", "File size", "Existing file", "Exit status", "Composite");
        trigDD2.getItems().addAll("Time of day", "Day of the week", "Day of month", "Date", "File size", "Existing file", "Exit status", "Composite");
        trigDD3.getItems().addAll("Time of day", "Day of the week", "Day of month", "Date", "File size", "Existing file", "Exit status", "Composite");
        trigDD4.getItems().addAll("Time of day", "Day of the week", "Day of month", "Date", "File size", "Existing file", "Exit status");
        trigDD5.getItems().addAll("Time of day", "Day of the week", "Day of month", "Date", "File size", "Existing file", "Exit status");
        trigDD6.getItems().addAll("Time of day", "Day of the week", "Day of month", "Date", "File size", "Existing file", "Exit status");
        trigDD7.getItems().addAll("Time of day", "Day of the week", "Day of month", "Date", "File size", "Existing file", "Exit status");
        
        listingAnchorPanesTrigger();
    }
    
    /**
    * Method to initialize the list of AnchorPanes.
    */
    private void listingAnchorPanesTrigger(){
        anchorPanesTrigger.add(triggerPane1);
        anchorPanesTrigger.add(triggerPane2);
        anchorPanesTrigger.add(triggerPane3);
        anchorPanesTrigger.add(triggerPane4);
        anchorPanesTrigger.add(triggerPane5);
        anchorPanesTrigger.add(triggerPane6);
        anchorPanesTrigger.add(triggerPane7);
    }
}
