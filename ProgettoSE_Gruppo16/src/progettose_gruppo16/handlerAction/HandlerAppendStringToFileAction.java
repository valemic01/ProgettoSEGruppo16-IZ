/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.Action;
import progettose_gruppo16.action.AppendStringToFileAction;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 *HandlerAppendStringToFileAction è una classe che gestisce la GUI e le funzionalità 
 * necessarie per l'aggiunta di testo a un file. 
 * Estende la classe BaseHandlerAction.
 * @author valentina <your.name at your.org>
 */
public class HandlerAppendStringToFileAction extends BaseHandlerAction{
    private TextField textToAppend = new TextField();
    private Label labelTextField = new Label();
    private Button selectFile = new Button();
    private Label labelSelectedFile = new Label();
    private String filePath;
    
    /**
     * Costruttore
     * @param ap AnchorPane dove sono posizionati i componenti GUI.
     * @param s identificatore dell'azione
     * @param btn pulsante associato all'azione
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb, Button btn){ 
        if(cb.getValue().equals("Add text to file")){
            // Cancella i componenti GUI esistenti
            ap.getChildren().clear();
            filePath = "";
            ap.setId("AppendStringPane");
            
            // Configura l'etichetta "Insert text to append"
            labelTextField.setText("Insert text to append");
            ap.getChildren().add(labelTextField);
            labelTextField.setLayoutX(138);
            labelTextField.setLayoutY(38);
            
            // Configura il campo di testo per l'input dell'utente
            textToAppend.setPromptText("Your text...");
            ap.getChildren().add(textToAppend);
            textToAppend.setLayoutX(99);
            textToAppend.setLayoutY(68);
            textToAppend.setPrefWidth(217);
            
            // Configura il pulsante "Select file"
            selectFile.setText("Select file");
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(157);
            selectFile.setLayoutY(127);
            
            // Configura l'etichetta per mostrare il file selezionato
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(171);          
            labelSelectedFile.setPrefWidth(400);
            labelSelectedFile.setAlignment(Pos.CENTER);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            // Associa il pulsante "Select file" a un'azione per scegliere un file
            selectFile.setOnAction(event -> filePath = chooseFile(labelSelectedFile));
        }else{
            super.handleGUI(ap, cb, btn);
        }      
    }
    
    /**
     * Gestisce il comportamento in base allo stato dell'AnchorPane.
     *
     * @param ap L'AnchorPane che rappresenta lo stato corrente.
     * @return Un'istanza di Action basata sullo stato corrente.
     */
    @Override 
    public Action handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("AppendStringPane")){     
            if(filePath!=null  && textToAppend.getText()!=null){
                return new AppendStringToFileAction(filePath, textToAppend.getText());   
            }else
                return null;
        }else{
           return super.handleBehaviour(ap);
        }
    } 
    
    /**
     * Apre una finestra di dialogo per permettere all'utente di selezionare un file di testo.
     * Sovrascrive il metodo della superclasse, specificando l'estensione del file.
     * 
     * @param lbl L'etichetta utilizzata per mostrare informazioni sul file selezionato.
     * @return Il percorso assoluto del file selezionato o null se nessun file è stato selezionato.
     */
    @Override
    public String chooseFile(Label lbl){
        FileChooser fileChooser = new FileChooser();
        File file;
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("TXT files (*.txt)", "*.txt");
        
        fileChooser.getExtensionFilters().add(filter);
        file = fileChooser.showOpenDialog(lbl.getScene().getWindow());
        if(file!=null){
            lbl.textProperty().set("Selected file: " + file.getName());
            return file.getAbsolutePath();
        }else{
            lbl.textProperty().set("File not selected");
            return null;
        }
    }
   
}
