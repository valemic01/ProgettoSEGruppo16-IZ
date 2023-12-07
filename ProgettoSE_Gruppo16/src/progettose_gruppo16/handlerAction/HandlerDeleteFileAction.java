/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.DeleteFileAction;
import progettose_gruppo16.action.Action;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 *Classe che gestisce la GUI e le funzionalità necessarie ai fini
 * di poter eliminare un file. Estende la classe BaseHandlerAction.
 * @author valentina <your.name at your.org>
 */
public class HandlerDeleteFileAction extends BaseHandlerAction{
    private Button selectFile = new Button();
    private Label labelSelectedFile = new Label();
    private String filePath;
    
    /**
     * Gestisce i componenti GUI in base all'azione specificata.
     *
     * @param ap  L'AnchorPane dove sono posizionati i componenti GUI.
     * @param s   L'identificatore dell'azione.
     * @param btn Il pulsante associato all'azione.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb, Button btn){ 
        if(cb.getValue().equals("Delete file")){
            // Cancella i componenti GUI esistenti
            ap.getChildren().clear();
            filePath = "";
            ap.setId("DeleteFilePane");
            
            // Configura il pulsante "Select file"
            selectFile.setText("Select file");
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(148);
            selectFile.setLayoutY(45);
            
            // Configura l'etichetta per mostrare il file selezionato
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(85);
            labelSelectedFile.setPrefWidth(400);
            labelSelectedFile.setAlignment(Pos.CENTER);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            // Associa il pulsante "Select" a un'azione per scegliere un file
            selectFile.setOnAction(event ->  filePath = chooseFile(labelSelectedFile));

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
        if(ap.getId().equals("DeleteFilePane")){  
            if(filePath == null)
                return null;
            else
                return new DeleteFileAction(filePath);          
        }else{
            return super.handleBehaviour(ap);
        }
    }
}

