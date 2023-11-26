/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package progettose_gruppo16;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.URL;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
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
 *Implementazione del controller che gestisce le interazioni con la GUI.
 * @author raffa
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane ruleListPane;
    @FXML
    private TableView<Rule> allRulesTable;
    @FXML
    private TableColumn<Rule, String> allRulesNameClm;
    @FXML
    private TableColumn<Rule, Trigger> allRulesTrigClm;
    @FXML
    private TableColumn<Rule, Action> allRulesActClm;
    @FXML
    private TableView<Rule> activeRulesTable;
    @FXML
    private TableColumn<Rule, String> activeRulesNameClm;
    @FXML
    private TableColumn<Rule, String> activeRulesTrigClm;
    @FXML
    private TableColumn<Rule, String> activeRulesActClm;  
    @FXML
    private TableView<Rule> inactRulesTable;
    @FXML
    private TableColumn<Rule, String> inactRulesNameClm;
    @FXML
    private TableColumn<Rule, String> inactRulesTrigClm;
    @FXML
    private TableColumn<Rule, String> inactRulesActClm;
    @FXML
    private Button delRuleBtn;
    @FXML
    private AnchorPane ruleCreatePane;
    @FXML
    private ComboBox<String> trigDD1;
    @FXML
    private ComboBox<String> actionDD1;
    @FXML
    private TextField slepPerDays;
    @FXML
    private TextField slepPerHours;
    @FXML
    private TextField slepPerMins;
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
    @FXML
    private MenuItem inactiveRuleContextMenu2;
    @FXML
    private MenuItem activeRuleContextMenu3;
    @FXML
    private MenuItem activeRuleContextMenu1;
    @FXML
    private AnchorPane sleepingPeriodPane;
    @FXML
    private CheckBox repetableCB;
    @FXML
    private Button addRuleBtn;
    @FXML
    private Tab actionTab;
    @FXML
    private Label labelAudioSelected;
    @FXML
    private Label notValidName;
    
    private ObservableList<Rule> allRulesList; //observable list of all rules, created to manage the respective TableView
    private ObservableList<Rule> activeRulesList; //observable list of active rules, created to manage the respective TableView
    private ObservableList<Rule> inactRulesList; //observable list of inactive rules, created to manage the respective TableView
    private TimeOfDayTrigger timeOfDay;
    private int selectHour, selectMinute;
    private RulesChecker rulesChecker;
    private Thread threadRulesChecker;  
    private FileChooser fileChooser;   
    private Trigger trigger;
    private Time sleepingPeriod;
    private String fileAudio;
    private String messageToShow;
    private Action action;
    private final String backupFile = "RulesBackup.dat";
    
    /**
     *Inizializzazione delle componenti dell'interfaccia utente
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        allRulesList = FXCollections.observableArrayList();
        activeRulesList = FXCollections.observableArrayList();
        inactRulesList = FXCollections.observableArrayList();
        
        if(new File(backupFile).exists()){
            try {
                importRulesFromFile();
            } catch (IOException ex) {
                Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
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

        //binding to show the sleeping period settings when the "Repetable" check box is selected
        sleepingPeriodPane.visibleProperty().bind(repetableCB.selectedProperty());
        
        //binding to disable the add rule button if the rule name, the action or the trigger are not selected
        addRuleBtn.disableProperty().bind(Bindings.isEmpty(ruleNameTxtBox.textProperty()).or(Bindings.isNull(trigDD1.valueProperty())).or(Bindings.isNull(actionDD1.valueProperty())).or(Bindings.createBooleanBinding(() ->"Show message".equals(actionDD1.getSelectionModel().getSelectedItem()), actionDD1.getSelectionModel().selectedItemProperty()).and(Bindings.isEmpty(messTxtBox.textProperty()))).or(Bindings.createBooleanBinding(() ->"Play audio".equals(actionDD1.getSelectionModel().getSelectedItem()), actionDD1.getSelectionModel().selectedItemProperty()).and(labelAudioSelected.visibleProperty().not())));
                
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
    
    /**
     *Metodo che importa le regole dal file di backup all'avvio dell'applicazione.
     * 
     */
    private void importRulesFromFile() throws IOException{
        ObjectInputStream ois = null;
        ArrayList importedRules = new ArrayList();

        try {
            ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(backupFile)));
            importedRules = (ArrayList) ois.readObject();
        } catch (FileNotFoundException | ClassNotFoundException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }finally{
            ois.close();
        }
 
        allRulesList.addAll(importedRules);
        for(Rule r : allRulesList){
            if(r.getActive())
                activeRulesList.add(r);
            else
                inactRulesList.add(r);
        }
    }
    
    /**
    * Metodo che inizializza le tabelle delle regole, in modo da renderle visibili nell'applicazione. 
    */
    private void inizializeTables(){
        allRulesNameClm.setCellValueFactory(new PropertyValueFactory("name"));
        allRulesTrigClm.setCellValueFactory(new PropertyValueFactory("trigger"));
        allRulesActClm.setCellValueFactory(new PropertyValueFactory("action"));
        allRulesTable.setItems(allRulesList);
        
        activeRulesNameClm.setCellValueFactory(new PropertyValueFactory("name"));
        activeRulesTrigClm.setCellValueFactory(new PropertyValueFactory("trigger"));
        activeRulesActClm.setCellValueFactory(new PropertyValueFactory("action"));
        activeRulesTable.setItems(activeRulesList);
        
        inactRulesNameClm.setCellValueFactory(new PropertyValueFactory("name"));
        inactRulesTrigClm.setCellValueFactory(new PropertyValueFactory("trigger"));
        inactRulesActClm.setCellValueFactory(new PropertyValueFactory("action"));
        inactRulesTable.setItems(inactRulesList);
        
    }
    
    /**
     * Metodo che mostra la schermata di creazione delle regole.
     * @param event 
     */
    @FXML
    private void showCreateRule(ActionEvent event) {
        ruleListPane.setVisible(false);
        ruleCreatePane.setVisible(true);
    }

    /**
     * Metodo che consente di passare dalla schermata di creazione delle regole alla
     * schermata che mostra tutte le regole definite dall'utente. 
     * Resetta tutti i parametri relativi alla creazione di una regola.
     * @param event 
     */
    @FXML
    private void goBack(ActionEvent event) {
        messTxtBox.clear();
        ruleNameTxtBox.clear();
        slepPerDays.clear();
        slepPerHours.clear();
        slepPerMins.clear();
        repetableCB.setSelected(false);
        hoursDD1.getSelectionModel().clearSelection();
        minsDD1.getSelectionModel().clearSelection();
        trigDD1.getSelectionModel().clearSelection();
        actionDD1.getSelectionModel().clearSelection();
        
        trigg_actTab.getSelectionModel().select(triggerTab);
        ruleCreatePane.setVisible(false);
        ruleListPane.setVisible(true);
        selectHour= 0;
        selectMinute=0;
        fileAudio= "";
        labelAudioSelected.setVisible(false);
        notValidName.setVisible(false);

    }

    /**
     * Metodo che consente all'utente di selezionare il file da riprodurre come azione tramite filechooser.
     * @param event 
     */
    @FXML
    private void chooseAudio(ActionEvent event) {
        
        fileChooser = new FileChooser();
        PlayAudioAction play = null;
        
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("File Audio WAV (*.wav)", "*.wav");
        fileChooser.getExtensionFilters().add(filter);
        fileAudio = fileChooser.showOpenDialog(selectAudioBtn.getScene().getWindow()).getAbsolutePath();
        if(!fileAudio.isEmpty()){
            labelAudioSelected.visibleProperty().set(true);
        }
    }

    /**
     * Metodo che consente di aggiungere una regola all'interno della lista delle regole,
     * memorizzando il trigger e l'azione definita dall'utente.
     * Controlla che tutti i campi siano stati riempiti correttamente.
     * @param event 
     */
    @FXML
    private void addRuleAction(ActionEvent event) {
        String hours;
        String name = ruleNameTxtBox.getText();
        LocalTime time = LocalTime.of(selectHour, selectMinute);
        timeOfDay = new TimeOfDayTrigger(time);
        trigger = timeOfDay;
        
        if("Play audio".equals(actionDD1.getSelectionModel().getSelectedItem())){
            action = new PlayAudioAction(fileAudio);
        }
            
        if("Show message".equals(actionDD1.getSelectionModel().getSelectedItem())){
            messageToShow = messTxtBox.getText();        
            action = new ShowMessageAction(messageToShow);
        }
              
        if(repetableCB.isSelected()){
            hours = String.valueOf(Integer.parseInt(slepPerDays.getText())*24 + Integer.parseInt(slepPerHours.getText()));
            sleepingPeriod = Time.valueOf(hours + ":" + slepPerMins.getText() + ":00");
        }
        
        Rule rule = new Rule(name, trigger, action, repetableCB.isSelected(), sleepingPeriod);
        for(Rule r: allRulesList){
            if(r.getName().equals(rule.getName())){
                notValidName.setVisible(true);
                return;
            }
        }
  
        allRulesList.add(rule);
        activeRulesList.add(rule);
        goBack(null);
        saveRules();   
    }
    
    /**
     * Metodo che crea il service che salva le regole sul file di backup.
     */
    private void saveRules(){
        BackupFileService backupService = new BackupFileService(allRulesList, backupFile);
        backupService.start();
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
    
    /**
     * Metodo che cancella una regola selezionata dalla lista delle regole e aggiorna le tabelle correlate.
     * Invoca anche il metodo saveRules() per aggiornare la lista delle regole presente sul file di backup.
     * @param event 
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
        
        saveRules();
    }

    //incorrect for sequence of action (TECHNICAL DEBT!)
    /**
     * Metodo che preleva l'ora selezionata dall'utente quando crea un TimeOfDayTrigger.
     * @param event 
     */
    @FXML
    private void selectHour(ActionEvent event) {
        if(!hoursDD1.getSelectionModel().isEmpty()){
            selectHour = Integer.parseInt(hoursDD1.getValue());
        }
    }

    //incorrect for sequence of action (TECHNICAL DEBT!)
     /**
     * Metodo che preleva i minuti selezionati dall'utente quando crea un TimeOfDayTrigger.
     * @param event 
     */
    @FXML
    private void selectMinute(ActionEvent event) {
        if(!minsDD1.getSelectionModel().isEmpty()){
            selectMinute = Integer.parseInt(minsDD1.getValue());
        }
    }
    
    /**Metodo che consente di attivare o disattivare le regole presenti nella tabella di tutte le regole.
     * Se la regola è attiva, si setta il menu item su "Disable Rule" per disattivarla.
     * Se la regole è disattivata, si setta il menu item su "Activate Rule" per attivarla.
     * @param event 
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

    /**
     * Metodo che consente di aggiornare tutte le liste delle regole e le tabelle 
     * quando l'utente sceglie di attivare o disattivare una regola dalla tabella generale delle regole.
     * @param event 
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
        
        saveRules();
    }

    /**
     * Metodo che consente di aggiornare le liste di tutte le regole e le tabelle
     * quando l'utente sceglie di disattivare una regola dalla tabella delle regole attive.
     * @param event 
     */
    @FXML
    private void InactivateRuleAction(ActionEvent event) {
        Rule rule= activeRulesTable.getSelectionModel().getSelectedItem();

        activeRulesList.remove(rule);
        activeRulesTable.getSelectionModel().clearSelection();
        inactRulesList.add(rule);
        inactRulesTable.setItems(inactRulesList);  
        rule.setActive(false); 
        
        saveRules();
    }

     /**
     * Metodo che consente di aggiornare le liste di tutte le regole e le tabelle
     * quando l'utente sceglie di riattivare una regola dalla tabella delle regole disattivate.
     * @param event 
     */
    @FXML
    private void activateRuleAction(ActionEvent event) {
      
        Rule rule= inactRulesTable.getSelectionModel().getSelectedItem();

        inactRulesList.remove(rule);
        inactRulesTable.getSelectionModel().clearSelection();
        activeRulesList.add(rule);
        activeRulesTable.setItems(activeRulesList);
        rule.setActive(true);
        
        saveRules();
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
     * Metodo che consente di deselezionare una regola quando si clicca al di fuori delle tabelle.
     * @param event 
     */
    @FXML
    private void deselect(MouseEvent event) {
        allRulesTable.getSelectionModel().clearSelection();
        activeRulesTable.getSelectionModel().clearSelection();
        inactRulesTable.getSelectionModel().clearSelection();
    }
}
