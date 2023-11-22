/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package it.unisa.diem.se;

import java.io.File;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author raffa
 */
public class FXML_ProgettoSEController implements Initializable {

    @FXML
    private AnchorPane ruleListPane;
    @FXML
    private TableView<Rule> allRulesTable;
    @FXML
    private TableColumn<Rule, String> allRulesIDClm;
    @FXML
    private TableColumn<Rule, String> allRulesNameClm;
    @FXML
    private TableColumn<Rule, String> allRulesTrigClm;
    @FXML
    private TableColumn<Rule, String> allRulesActClm;
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
    private Button createRuleBtn;
    @FXML
    private Button delRuleBtn;
    @FXML
    private Button counterBtn;
    @FXML
    private AnchorPane ruleCreatePane;
    @FXML
    private ComboBox<String> trigDD1;
    @FXML
    private CheckBox notCB1;
    @FXML
    private Button closeBtn1;
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
    private Button addRuleBtn;
    @FXML
    private CheckBox onlyOnceCB;
    @FXML
    private Button closeBtn2;
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
    private Button closeBtn3;
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
    private AnchorPane trigTimeOfDay1;
    @FXML
    private ComboBox<String> hoursDD1;
    @FXML
    private ComboBox<String> minsDD1;
    @FXML
    private AnchorPane trigDayOfWeek1;
    @FXML
    private ComboBox<?> dayOfWeekDD1;
    @FXML
    private AnchorPane trigFileSize1;
    @FXML
    private Button chooseFileBtn1;
    @FXML
    private TextField fileSizeTxtBox1;
    @FXML
    private AnchorPane trigFileExists1;
    @FXML
    private Button choosePathBtn1;
    @FXML
    private TextField fileNameTxtBox1;
    @FXML
    private AnchorPane trigDate1;
    @FXML
    private DatePicker datePicker1;
    @FXML
    private AnchorPane trigDayOfMonth1;
    @FXML
    private TextField dayOfMonthTxtBox1;
    @FXML
    private AnchorPane trigExitStatus1;
    @FXML
    private TextField progNameTxtBox1;
    @FXML
    private TextField argsListTxtBox1;
    @FXML
    private TextField expValueTxtBox1;
    @FXML
    private AnchorPane trigCounterValue1;
    @FXML
    private ComboBox<?> countValDD1;
    @FXML
    private TextField countValTxtBox1;
    @FXML
    private AnchorPane trigCounters1;
    @FXML
    private ComboBox<?> countsDD11;
    @FXML
    private ComboBox<?> countsDD12;
    @FXML
    private TextField ruleNameTxtBox;
    @FXML
    private TextField messTxtBox;
    @FXML
    private Button selectAudioBtn;
    
    private FileChooser fileChooser;
    
    private Action action;
    private Trigger trigger;
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //setup process of the three TableView, linked with the respective observable list
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
        
        //binding to disable the delete button when no rule is selected
        delRuleBtn.disableProperty().bind(allRulesTable.getSelectionModel().selectedItemProperty().isNull().and(activeRulesTable.getSelectionModel().selectedItemProperty().isNull()).and(inactRulesTable.getSelectionModel().selectedItemProperty().isNull()));
        
        //inizialization of the combo boxes for trigger and actions(TECHNICAL DEBT!)
        trigDD1.getItems().add("Time of day");
        actionDD1.getItems().add("Show message");
        actionDD1.getItems().add("Play audio");
        
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
    }    

    @FXML
    private void onChangeTrigger(ActionEvent event) {
        //TECHNICAL DEBT!
        trigTimeOfDay1.setVisible(true);
        
    }

    @FXML
    private void showCreateRule(ActionEvent event) {
        
        ruleListPane.setVisible(false);
        ruleCreatePane.setVisible(true);
        
    }

    @FXML
    private void showCounters(ActionEvent event) {
        
        ruleListPane.setVisible(false);
        counterPane.setVisible(true);
        
    }

    @FXML
    private void goBack(ActionEvent event) {
        
        ruleCreatePane.setVisible(false);
        counterPane.setVisible(false);
        ruleListPane.setVisible(true);
        
    }

    @FXML
    private void onChangeAction(ActionEvent event) {
        
        //TECHNICAL DEBT!
        ComboBox cb = (ComboBox)event.getSource();
        if(cb.getValue().toString() == "Show message"){
            selectAudioBtn.setVisible(false);
            messTxtBox.setVisible(true);
        }
        else{
            messTxtBox.setText(null);
            messTxtBox.setVisible(false);
            selectAudioBtn.setVisible(true);
        }
        
    }

    @FXML
    private void chooseAudio(ActionEvent event) {
        
        fileChooser = new FileChooser();
        File fileAudio = fileChooser.showSaveDialog(selectAudioBtn.getScene().getWindow());
        
        action = new PlayAudioAction(fileAudio); //incorrect for sequence of action (TECHNICAL DEBT!)
        
    }

    @FXML
    private void addRuleAction(ActionEvent event) {
        
        String name = ruleNameTxtBox.getText();
        
        if(name == null){
            return;
        }
        
        Rule rule = new Rule(name, trigger, action, false, null);
        allRulesList.add(rule);
        
    }

    @FXML
    private void messChangeAction(ActionEvent event) {
        
        TextField tb = (TextField)event.getSource();
        String message = tb.getText();
        
        action = new ShowMessageAction(message); //incorrect for sequence of action (TECHNICAL DEBT!)
        
    }

    @FXML
    private void timeChangeAction(ActionEvent event) {
        
        ComboBox cb = (ComboBox)event.getSource();
        Parent parent = cb.getParent();
        ComboBox cb1 = (ComboBox)parent.getChildrenUnmodifiable().get(0);
        ComboBox cb2 = (ComboBox)parent.getChildrenUnmodifiable().get(2);
        
        Time time = Time.valueOf(cb1.getValue()+":"+cb2.getValue()+":0");
        trigger = new TimeOfDayTrigger(time);
        
    }
    
}
