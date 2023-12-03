/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.CopyFileAction;
import progettose_gruppo16.action.Action;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *Classe che gestisce la GUI e le funzionalità necessarie ai fini
 * di consentire la copia di un file. Estende la classe BaseHandlerAction.
 * @author valentina <your.name at your.org>
 */
public class HandlerCopyFileAction extends BaseHandlerAction{
    private Label labelSourceFile = new Label();
    private Button selectSourceFile = new Button();
    private Label selectedFile = new Label();
    private Label labelDestinationFile = new Label();
    private Button selectDestinationFile = new Button();
    private Label selectedDestination = new Label();
    private String sourcePath;
    private String destinationPath;
    
    /**
     * Gestisce i componenti GUI in base all'azione specificata.
     *
     * @param ap  L'AnchorPane dove sono posizionati i componenti GUI.
     * @param s   L'identificatore dell'azione.
     * @param btn Il pulsante associato all'azione.
     */
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Copy file")){
            // Cancella i componenti GUI esistenti
            ap.getChildren().clear();
            sourcePath = "";
            destinationPath = "";
            ap.setId("CopyFilePane");
            
            // Configura l'etichetta "File to copy"
            labelSourceFile.setText("File to copy");
            ap.getChildren().add(labelSourceFile);
            labelSourceFile.setLayoutX(158);
            labelSourceFile.setLayoutY(37);
            
            // Configura il pulsante "Select file"
            selectSourceFile.setText("Select file");
            ap.getChildren().add(selectSourceFile);
            selectSourceFile.setLayoutX(156);
            selectSourceFile.setLayoutY(67);
            
            // Configura l'etichetta per mostrare il file selezionato
            selectedFile.setText("");
            ap.getChildren().add(selectedFile);
            selectedFile.setLayoutX(0);
            selectedFile.setLayoutY(106);
            selectedFile.setPrefWidth(400);
            selectedFile.setAlignment(Pos.CENTER);
            selectedFile.setTextFill(Color.web("#009999"));
            
            // Configura l'etichetta "Where you want to copy the file"
            labelDestinationFile.setText("Where you want to copy the file");
            ap.getChildren().add(labelDestinationFile);
            labelDestinationFile.setLayoutX(91);
            labelDestinationFile.setLayoutY(146);
            
            // Configura il pulsante "Select destination"
            selectDestinationFile.setText("Select destination");
            ap.getChildren().add(selectDestinationFile);
            selectDestinationFile.setLayoutX(131);
            selectDestinationFile.setLayoutY(176);
            
            // Configura l'etichetta per mostrare la destinazione selezionata
            selectedDestination.setText("");
            ap.getChildren().add(selectedDestination);
            selectedDestination.setLayoutX(0);
            selectedDestination.setLayoutY(218);
            selectedDestination.setPrefWidth(400);
            selectedDestination.setAlignment(Pos.CENTER);
            selectedDestination.setTextFill(Color.web("#009999"));
            
            // Associa il pulsante "Select file" a un'azione per scegliere un file
            selectSourceFile.setOnAction(event -> sourcePath = chooseFile(selectedFile));
            
            // Associa il pulsante "Select destination" a un'azione per scegliere una directory
            selectDestinationFile.setOnAction(event -> destinationPath = chooseDirectory(selectedDestination));
          
        }else{
            super.handleGUI(ap, s, btn);
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
        if(ap.getId().equals("CopyFilePane")){    
            if(sourcePath==null || destinationPath==null)
                return null;
            else
                return new CopyFileAction(sourcePath, destinationPath);           
        }else{
            return super.handleBehaviour(ap);
        }
    }     
}

