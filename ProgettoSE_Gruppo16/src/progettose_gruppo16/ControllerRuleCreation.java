package progettose_gruppo16;

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
import javafx.stage.FileChooser;
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
    private AnchorPane trigTimeOfDay;
    @FXML
    private ComboBox<String> hoursDD1;
    @FXML
    private ComboBox<String> minsDD1;
    @FXML
    private Tab actionTab;
    @FXML
    private ComboBox<String> actionDD1;
    @FXML
    private TextField messTxtBox;
    @FXML
    private Button selectAudioBtn;
    @FXML
    private Label labelAudioSelected;
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
    
    private TimeOfDayTrigger timeOfDay;
    private int selectHour, selectMinute;
    private FileChooser fileChooser;   
    private Trigger trigger;
    private Time sleepingPeriod;
    private String fileAudio;
    private String messageToShow;
    private Action action;
    
    private RulesManager ruleManager;
    
    private ObservableList<Rule> allRulesList; //observable list of all rules, created to manage the respective TableView
    
    private Stage stage;

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
        
        //inizialization of the combo boxes for trigger and actions(TECHNICAL DEBT!)
        trigDD1.getItems().add("Time of day");
        actionDD1.getItems().add("Show message");
        actionDD1.getItems().add("Play audio");
        actionDD1.getItems().add("Move file");
        actionDD1.getItems().add("Delete file");
        actionDD1.getItems().add("Copy file");
        actionDD1.getItems().add("Add text to file");
        
        //bindings to show the trigger/action settings when selected
        trigTimeOfDay.visibleProperty().bind(Bindings.createBooleanBinding(() ->"Time of day".equals(trigDD1.getSelectionModel().getSelectedItem()),trigDD1.getSelectionModel().selectedItemProperty()));
        messTxtBox.visibleProperty().bind(Bindings.createBooleanBinding(() ->"Show message".equals(actionDD1.getSelectionModel().getSelectedItem()),actionDD1.getSelectionModel().selectedItemProperty()));
        selectAudioBtn.visibleProperty().bind(Bindings.createBooleanBinding(() ->"Play audio".equals(actionDD1.getSelectionModel().getSelectedItem()),actionDD1.getSelectionModel().selectedItemProperty()));

        //binding to show the sleeping period settings when the "Repetable" check box is selected
        sleepingPeriodPane.visibleProperty().bind(repetableCB.selectedProperty());
        
        //binding to disable the add rule button if the rule name, the action or the trigger are not selected
        addRuleBtn.disableProperty().bind(Bindings.isEmpty(ruleNameTxtBox.textProperty()).or(Bindings.isNull(trigDD1.valueProperty())).or(Bindings.isNull(actionDD1.valueProperty())).or(Bindings.createBooleanBinding(() ->"Show message".equals(actionDD1.getSelectionModel().getSelectedItem()), actionDD1.getSelectionModel().selectedItemProperty()).and(Bindings.isEmpty(messTxtBox.textProperty()))).or(Bindings.createBooleanBinding(() ->"Play audio".equals(actionDD1.getSelectionModel().getSelectedItem()), actionDD1.getSelectionModel().selectedItemProperty()).and(labelAudioSelected.visibleProperty().not())));
        labelAudioSelected.visibleProperty().bind(actionDD1.getSelectionModel().selectedItemProperty().isEqualTo("Play audio"));         
        
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
     * Metodo che consente all'utente di selezionare il file da riprodurre come azione tramite filechooser.
     * @param event 
     */
    @FXML
    private void chooseAudio(ActionEvent event) {
        
        fileChooser = new FileChooser();
        String filename;
        
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("File Audio WAV (*.wav)", "*.wav");
        fileChooser.getExtensionFilters().add(filter);
        fileAudio = fileChooser.showOpenDialog(selectAudioBtn.getScene().getWindow()).getAbsolutePath();
        if(!fileAudio.isEmpty()){
            filename = fileAudio.substring(fileAudio.lastIndexOf('\\')+1);
            labelAudioSelected.textProperty().set("Selected audio: " + filename);
        }
    }

    /**
     * Metodo che recupera le informazioni necessarie per la creazione della regola e invoca il
     * corrispettivo metodo di RulesManager.
     * Controlla che tutti i campi siano stati riempiti correttamente e che i nomi assegnati alle regole siano identificatori univoci.
     * @param event 
     */
    @FXML
    private void addRuleAction(ActionEvent event) throws IOException {
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
  
        ruleManager.addRule(rule);
        goBack(null);
        
    }
    
}