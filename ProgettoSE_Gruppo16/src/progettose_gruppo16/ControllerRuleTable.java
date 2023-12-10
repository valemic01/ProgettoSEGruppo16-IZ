package progettose_gruppo16;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 *  Controller che gestisce la schermata delle tabelle
 */
public class ControllerRuleTable implements Initializable {

    @FXML
    private AnchorPane ruleListPane;
    @FXML
    private TableView<Rule> allRulesTable;
    @FXML
    private MenuItem delRuleContextMenu1;
    @FXML
    private MenuItem activeRuleContextMenu1;
    @FXML
    private TableColumn<Rule, String> allRulesNameClm;
    @FXML
    private TableColumn<Rule, String> allRulesTrigClm;
    @FXML
    private TableColumn<Rule, String> allRulesActClm;
    @FXML
    private TableView<Rule> activeRulesTable;
    @FXML
    private MenuItem delRuleContextMenu2;
    @FXML
    private MenuItem inactiveRuleContextMenu2;
    @FXML
    private TableColumn<Rule, String> activeRulesNameClm;
    @FXML
    private TableColumn<Rule, String> activeRulesTrigClm;
    @FXML
    private TableColumn<Rule, String> activeRulesActClm;
    @FXML
    private TableView<Rule> inactRulesTable;
    @FXML
    private MenuItem delRuleContextMenu3;
    @FXML
    private MenuItem activeRuleContextMenu3;
    @FXML
    private TableColumn<Rule, String> inactRulesNameClm;
    @FXML
    private TableColumn<Rule, String> inactRulesTrigClm;
    @FXML
    private TableColumn<Rule, String> inactRulesActClm;
    @FXML
    private Button delRuleBtn;
    
    private ObservableList<Rule> allRulesList; //observable list of all rules, created to manage the respective TableView
    private ObservableList<Rule> activeRulesList; //observable list of active rules, created to manage the respective TableView
    private ObservableList<Rule> inactRulesList; //observable list of inactive rules, created to manage the respective TableView    
    private RulesManager ruleManager; 
    private Stage stage;
    
    /**
     *  Inizializzazione delle componenti dell'interfaccia utente
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //get RulesManager instance (Singleton) and all the tables
        ruleManager = RulesManager.getInstance();
        
        allRulesList = ruleManager.getAllRulesList();
        activeRulesList = ruleManager.getActiveRulesList();
        inactRulesList = ruleManager.getInactRulesList();
        
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
     * Metodo che consente di recuperare la regola che l'utente vuole eliminare.
     * Viene poi invocato il rispettivo metodo di RulesManager per l'effettiva eliminazione.
     * @param event 
     */
    @FXML
    private void deleteRuleAction(ActionEvent event) {
        Rule rule = null;
        
        if(allRulesTable.getSelectionModel().selectedItemProperty().isNotNull().get())
            rule = allRulesTable.getSelectionModel().getSelectedItem();
        else if(activeRulesTable.getSelectionModel().selectedItemProperty().isNotNull().get())
            rule = activeRulesTable.getSelectionModel().getSelectedItem();
        else if(inactRulesTable.getSelectionModel().selectedItemProperty().isNotNull().get())
            rule = inactRulesTable.getSelectionModel().getSelectedItem();
            
        ruleManager.deleteRule(rule);
        
    }

    /**
     * Metodo che consente di recuperare la regola che l'utente vuole attivare/disattivare.
     * Viene poi invocato il rispettivo metodo di RulesManager per l'effettiva attivazione/disattivazione.
     * @param event 
     */
    @FXML
    private void activeInactivateRuleAction(ActionEvent event) {
        
        Rule rule = null;
        
        if(allRulesTable.getSelectionModel().selectedItemProperty().isNotNull().get())
            rule = allRulesTable.getSelectionModel().getSelectedItem();
        else if(activeRulesTable.getSelectionModel().selectedItemProperty().isNotNull().get())
            rule = activeRulesTable.getSelectionModel().getSelectedItem();
        else if(inactRulesTable.getSelectionModel().selectedItemProperty().isNotNull().get())
            rule = inactRulesTable.getSelectionModel().getSelectedItem();
                    
        ruleManager.activeInactivateRule(rule);
    }

    /**Metodo che consente di cambiare dinamicamente il nome del comando nel Context Menu.
     * Se la regola è attiva, si setta il menu item su "Disable Rule" per disattivarla.
     * Se la regole è disattivata, si setta il menu item su "Activate Rule" per attivarla.
     * @param event 
     */
    @FXML
    private void setActiveInactive(MouseEvent event) {
        
        Rule rule = allRulesTable.getSelectionModel().getSelectedItem();
        
        if(rule != null && rule.isActive())
            activeRuleContextMenu1.setText("Disable Rule");
        else
            activeRuleContextMenu1.setText("Activate Rule");
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

    /**
     * Metodo che mostra la schermata di creazione delle regole.
     * @param event 
     */
    @FXML
    private void showCreateRule(ActionEvent event) throws IOException {
        
        stage = (Stage) ruleListPane.getScene().getWindow();
        
        Parent root = FXMLLoader.load(getClass().getResource("FXMLRuleCreation.fxml"));
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        
    }

    
}