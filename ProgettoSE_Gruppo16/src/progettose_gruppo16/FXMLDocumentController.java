/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package progettose_gruppo16;

import java.io.File;
import java.net.URL;
import java.time.LocalTime;
import java.util.ResourceBundle;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author raffa
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane ruleListPane;
    @FXML
    private TableView<Rule> allRulesTable;
    @FXML
    private TableColumn<Rule, Integer> allRulesIDClm;
    @FXML
    private TableColumn<Rule, String> allRulesNameClm;
    @FXML
    private TableColumn<Rule, Trigger> allRulesTrigClm;
    @FXML
    private TableColumn<Rule, Action> allRulesActClm;
    private ObservableList<Rule> allRulesList; //observable list of all rules, created to manage the respective TableView
    
    @FXML
    private TableView<Rule> activeRulesTable;
    @FXML
    private TableColumn<Rule, String> activeRulesIDClm;
    @FXML
    private TableColumn<Rule, String> activeRulesNameClm;
    @FXML
    private TableColumn<Rule, String> activeRulesTrigClm;
    @FXML
    private TableColumn<Rule, String> activeRulesActClm;
    private ObservableList<Rule> activeRulesList; //observable list of active rules, created to manage the respective TableView
    
    @FXML
    private TableView<Rule> inactRulesTable;
    @FXML
    private TableColumn<Rule, String> inactRulesIDClm;
    @FXML
    private TableColumn<Rule, String> inactRulesNameClm;
    @FXML
    private TableColumn<Rule, String> inactRulesTrigClm;
    @FXML
    private TableColumn<Rule, String> inactRulesActClm;
    private ObservableList<Rule> inactRulesList; //observable list of inactive rules, created to manage the respective TableView
    @FXML
    private Button delRuleBtn;
    @FXML
    private AnchorPane ruleCreatePane;
    @FXML
    private ComboBox<String> trigDD1;
    @FXML
    private CheckBox notCB1;
    @FXML
    private ComboBox<String> actionDD1;
    @FXML
    private Button addActBtn1;
    @FXML
    private ComboBox<?> actionDD2;
    @FXML
    private Button addActBtn2;
    @FXML
    private ComboBox<?> actionDD3;
    @FXML
    private Button addActBtn3;
    @FXML
    private Button delActBtn2;
    @FXML
    private Button delActBtn1;
    @FXML
    private CheckBox onlyOnceCB;
    @FXML
    private TextField slepPerDays;
    @FXML
    private TextField slepPerHours;
    @FXML
    private TextField slepPerMins;
    @FXML
    private AnchorPane counterPane;
    @FXML
    private TableView<?> counterList;
    @FXML
    private TableColumn<?, ?> counterLIstNameClm;
    @FXML
    private TableColumn<?, ?> counterListValClm;
    @FXML
    private TextField counterNameTxtBox;
    @FXML
    private TextField counterValTxtBox;
    @FXML
    private Button createCounterBtn;
    @FXML
    private AnchorPane trigCompose2;
    @FXML
    private ComboBox<?> trigDD4;
    @FXML
    private CheckBox notCB4;
    @FXML
    private ComboBox<?> logOps2;
    @FXML
    private ComboBox<?> trigDD5;
    @FXML
    private CheckBox notCB5;
    @FXML
    private AnchorPane trigCompose1;
    @FXML
    private ComboBox<?> logOps1;
    @FXML
    private ComboBox<?> trigDD2;
    @FXML
    private CheckBox notCB2;
    @FXML
    private ComboBox<?> trigDD3;
    @FXML
    private CheckBox notCB3;
    @FXML
    private AnchorPane trigCompose3;
    @FXML
    private ComboBox<?> logOps3;
    @FXML
    private ComboBox<?> trigDD6;
    @FXML
    private CheckBox notCB6;
    @FXML
    private ComboBox<?> trigDD7;
    @FXML
    private CheckBox notCB7;
    @FXML
    private ComboBox<?> actionDD4;
    @FXML
    private Button addActBtn4;
    @FXML
    private Button delActBtn3;
    @FXML
    private ComboBox<?> actionDD5;
    @FXML
    private Button addActBtn5;
    @FXML
    private Button delActBtn4;
    @FXML
    private ComboBox<?> actionDD6;
    @FXML
    private Button addActBtn6;
    @FXML
    private Button delActBtn5;
    @FXML
    private ComboBox<String> hoursDD1;
    @FXML
    private ComboBox<String> minsDD1;
    @FXML
    private TextField ruleNameTxtBox;
    @FXML
    private TextField messTxtBox;
    @FXML
    private Button selectAudioBtn;
    @FXML
    private MenuItem delRuleContextMenu1;
    @FXML
    private MenuItem delRuleContextMenu2;
    @FXML
    private MenuItem delRuleContextMenu3;
    @FXML
    private TabPane trigg_actTab;
    @FXML
    private Tab triggerTab;
    @FXML
    private AnchorPane trigTimeOfDay;
    
    private TimeOfDayTrigger timeOfDay;
    int selectHour, selectMinute;
     
    private RulesChecker rulesChecker;
    private Thread threadRulesChecker;
    
    private FileChooser fileChooser;
    
    private Action action;
    private Trigger trigger;
    
    @FXML
    private MenuItem inactiveRuleContextMenu2;
    @FXML
    private MenuItem activeRuleContextMenu3;
    @FXML
    private MenuItem activeRuleContextMenu1;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //listener that creates a new ShowMessage action every time the message is changed
        messTxtBox.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                action = new ShowMessageAction(newValue); //incorrect for sequence of action (TECHNICAL DEBT!)
            }
        });
        
        //setup process of the three TableView, linked with the respective observable list
        inizializeTables();
        
        //binding to disable the delete button and context menus when no rule is selected
        delRuleBtn.disableProperty().bind(allRulesTable.getSelectionModel().selectedItemProperty().isNull().and(activeRulesTable.getSelectionModel().selectedItemProperty().isNull()).and(inactRulesTable.getSelectionModel().selectedItemProperty().isNull()));
        delRuleContextMenu1.disableProperty().bind(allRulesTable.getSelectionModel().selectedItemProperty().isNull().and(activeRulesTable.getSelectionModel().selectedItemProperty().isNull()).and(inactRulesTable.getSelectionModel().selectedItemProperty().isNull()));
        delRuleContextMenu2.disableProperty().bind(allRulesTable.getSelectionModel().selectedItemProperty().isNull().and(activeRulesTable.getSelectionModel().selectedItemProperty().isNull()).and(inactRulesTable.getSelectionModel().selectedItemProperty().isNull()));
        delRuleContextMenu3.disableProperty().bind(allRulesTable.getSelectionModel().selectedItemProperty().isNull().and(activeRulesTable.getSelectionModel().selectedItemProperty().isNull()).and(inactRulesTable.getSelectionModel().selectedItemProperty().isNull()));
        activeRuleContextMenu1.disableProperty().bind(allRulesTable.getSelectionModel().selectedItemProperty().isNull().and(activeRulesTable.getSelectionModel().selectedItemProperty().isNull()).and(inactRulesTable.getSelectionModel().selectedItemProperty().isNull()));
        inactiveRuleContextMenu2.disableProperty().bind(allRulesTable.getSelectionModel().selectedItemProperty().isNull().and(activeRulesTable.getSelectionModel().selectedItemProperty().isNull()).and(inactRulesTable.getSelectionModel().selectedItemProperty().isNull()));
        activeRuleContextMenu3.disableProperty().bind(allRulesTable.getSelectionModel().selectedItemProperty().isNull().and(activeRulesTable.getSelectionModel().selectedItemProperty().isNull()).and(inactRulesTable.getSelectionModel().selectedItemProperty().isNull()));
        
        //inizialization of the combo boxes for trigger and actions(TECHNICAL DEBT!)
        trigDD1.getItems().add("Time of day");
        actionDD1.getItems().add("Show message");
        actionDD1.getItems().add("Play audio");
        
        //bindings to show the trigger/action settings when selected
        trigTimeOfDay.visibleProperty().bind(Bindings.createBooleanBinding(() ->"Time of day".equals(trigDD1.getSelectionModel().getSelectedItem()),trigDD1.getSelectionModel().selectedItemProperty()));
        messTxtBox.visibleProperty().bind(Bindings.createBooleanBinding(() ->"Show message".equals(actionDD1.getSelectionModel().getSelectedItem()),actionDD1.getSelectionModel().selectedItemProperty()));
        selectAudioBtn.visibleProperty().bind(Bindings.createBooleanBinding(() ->"Play audio".equals(actionDD1.getSelectionModel().getSelectedItem()),actionDD1.getSelectionModel().selectedItemProperty()));

        //inizialization of the combo boxes for the time selection (TECHNICAL DEBT!)
        for (int i = 0; i <= 23; i++) {
            if(i<10)
                hoursDD1.getItems().add("0"+String.valueOf(i));
            else
                hoursDD1.getItems().add(String.valueOf(i));
        }
 
        for (int i = 0; i <= 59; i++) {
            if(i<10)
                minsDD1.getItems().add("0"+String.valueOf(i));
            else
                minsDD1.getItems().add(String.valueOf(i));
        }
        
        //Initialization and start of the thread for automatic condition checking
        rulesChecker = new RulesChecker(activeRulesList);
        threadRulesChecker = new Thread(rulesChecker);
        threadRulesChecker.setName("Thread Rules Checker");
        threadRulesChecker.setDaemon(true);
        threadRulesChecker.start();
    }
    
    private void inizializeTables(){
        allRulesList = FXCollections.observableArrayList();
        allRulesIDClm.setCellValueFactory(new PropertyValueFactory("ID"));
        allRulesNameClm.setCellValueFactory(new PropertyValueFactory("name"));
        allRulesTrigClm.setCellValueFactory(new PropertyValueFactory("trigger"));
        allRulesActClm.setCellValueFactory(new PropertyValueFactory("action"));
        allRulesTable.setItems(allRulesList);
        
        activeRulesList = FXCollections.observableArrayList();
        activeRulesIDClm.setCellValueFactory(new PropertyValueFactory("ID"));
        activeRulesNameClm.setCellValueFactory(new PropertyValueFactory("name"));
        activeRulesTrigClm.setCellValueFactory(new PropertyValueFactory("trigger"));
        activeRulesActClm.setCellValueFactory(new PropertyValueFactory("action"));
        activeRulesTable.setItems(activeRulesList);
        
        inactRulesList = FXCollections.observableArrayList();
        inactRulesIDClm.setCellValueFactory(new PropertyValueFactory("ID"));
        inactRulesNameClm.setCellValueFactory(new PropertyValueFactory("name"));
        inactRulesTrigClm.setCellValueFactory(new PropertyValueFactory("trigger"));
        inactRulesActClm.setCellValueFactory(new PropertyValueFactory("action"));
        inactRulesTable.setItems(inactRulesList);
    }

    //make the rule creation panel visible
    @FXML
    private void showCreateRule(ActionEvent event) {
        ruleListPane.setVisible(false);
        ruleCreatePane.setVisible(true);
    }

    //make the counters pane visible
    @FXML
    private void showCounters(ActionEvent event) {
        ruleListPane.setVisible(false);
        counterPane.setVisible(true);
    }

    //close the current pane and goes back to the rule list pane
    @FXML
    private void goBack(ActionEvent event) {
        messTxtBox.clear();
        ruleNameTxtBox.clear();
        trigg_actTab.getSelectionModel().select(triggerTab);
        ruleCreatePane.setVisible(false);
        counterPane.setVisible(false);
        ruleListPane.setVisible(true);
    }

    //open a window to select the audio fil and create the respective action
    @FXML
    private void chooseAudio(ActionEvent event) {
        
        fileChooser = new FileChooser();
        File fileAudio = fileChooser.showSaveDialog(selectAudioBtn.getScene().getWindow());
        
        //action = new PlayAudioAction(fileAudio); //incorrect for sequence of action (TECHNICAL DEBT!)
        
    }

    //add rule to the list with the trigger and action previously set
    @FXML
    private void addRuleAction(ActionEvent event) {
        String name = ruleNameTxtBox.getText();
        LocalTime time = LocalTime.of(selectHour, selectMinute);
        timeOfDay = new TimeOfDayTrigger(time);
        
        if(name == null || action == null || timeOfDay == null){
            return;
        }
        
        Rule rule = new Rule(name, timeOfDay, action, false, null);
        allRulesList.add(rule);
        activeRulesList.add(rule);
        
    }

    /*create a new trigger when the user change its settings
    
    private void timeChangeAction(ActionEvent event) {
        
        ComboBox cb = (ComboBox)event.getSource();
        Parent parent = cb.getParent();
        ComboBox cb1 = (ComboBox)parent.getChildrenUnmodifiable().get(0);
        ComboBox cb2 = (ComboBox)parent.getChildrenUnmodifiable().get(2);
        
        Time time = Time.valueOf(cb1.getValue()+":"+cb2.getValue()+":0");
        //trigger = new TimeOfDayTrigger(time);
        
    }
    */

    @FXML
    private void deleteRuleAction(ActionEvent event) {
        Rule rule;
        if(allRulesTable.getSelectionModel().selectedItemProperty().isNotNull().get()){
            rule= allRulesTable.getSelectionModel().getSelectedItem();
            allRulesList.remove(rule);
            if (rule.getActive())
                activeRulesList.remove(rule);
            else
                inactRulesList.remove(rule);
        }
        else if(activeRulesTable.getSelectionModel().selectedItemProperty().isNotNull().get()){
            rule= activeRulesTable.getSelectionModel().getSelectedItem();
            allRulesList.remove(rule);
            activeRulesList.remove(rule);
            
        }
        else if(inactRulesTable.getSelectionModel().selectedItemProperty().isNotNull().get()){
            rule= inactRulesTable.getSelectionModel().getSelectedItem();
            allRulesList.remove(rule);
            inactRulesList.remove(rule);
        }
    }

    //incorrect for sequence of action (TECHNICAL DEBT!)
    @FXML
    private void selectHour(ActionEvent event) {
        selectHour = Integer.parseInt(hoursDD1.getValue());
    }

    //incorrect for sequence of action (TECHNICAL DEBT!)
    @FXML
    private void selectMinute(ActionEvent event) {
        selectMinute = Integer.parseInt(minsDD1.getValue());
    }
    
    /*
    Si possono attivare o disattivare le regole presenti nella tabella generale.
    Se la regola è attiva, si setta il menu item a "Disable Rule" per disattivarla.
    se la regole è disattivata, si setta il menu item a "Activate Rule" per attivarla.
    */
    @FXML
    private void setActiveInactive(MouseEvent event) {
        Rule rule= allRulesTable.getSelectionModel().getSelectedItem();
        if(inactRulesList.contains(rule)){
            activeRuleContextMenu1.setText("Activate Rule");
        }
        else if(activeRulesList.contains(rule))
            activeRuleContextMenu1.setText("Disable Rule");
    }

    /*
    In questo metodo vengono aggiornate le liste osservabili e le tabelle quando, nella tebella
    contenente tutte le regole, l'utente decide di attivare o disattivare una regola.
    una regola
    */
    @FXML
    private void activeInactivateRuleAction(ActionEvent event) {
        Rule rule= allRulesTable.getSelectionModel().getSelectedItem();

        if(inactRulesList.contains(rule)){
            inactRulesList.remove(rule);
            inactRulesTable.getSelectionModel().clearSelection();
            activeRulesList.add(rule);
            activeRulesTable.setItems(activeRulesList);  
            rule.setActive(true);
        }
        else if(activeRulesList.contains(rule)){
            activeRulesList.remove(rule);
            activeRulesTable.getSelectionModel().clearSelection();
            inactRulesList.add(rule);
            inactRulesTable.setItems(inactRulesList);
            rule.setActive(false);            
        }
    }

    /*
    Il metodo aggiorna tutte le liste osservabili e le tabelle quando, nella tabella contentente le
    regole attive, l'utente decide di disattivare una regola.
    */
    @FXML
    private void InactivateRuleAction(ActionEvent event) {
        Rule rule= activeRulesTable.getSelectionModel().getSelectedItem();

        activeRulesList.remove(rule);
        activeRulesTable.getSelectionModel().clearSelection();
        inactRulesList.add(rule);
        inactRulesTable.setItems(inactRulesList);  
        rule.setActive(false); 
    }

    /*
    Il metodo aggiorna tutte le liste osservabili e le tabelle quando, nella tabella contentente le
    regole disattive, l'utente decide di attivare una regola.
    */
    @FXML
    private void activateRuleAction(ActionEvent event) {
      
        Rule rule= inactRulesTable.getSelectionModel().getSelectedItem();

        inactRulesList.remove(rule);
        inactRulesTable.getSelectionModel().clearSelection();
        activeRulesList.add(rule);
        activeRulesTable.setItems(activeRulesList);
        rule.setActive(true);
    }
}
