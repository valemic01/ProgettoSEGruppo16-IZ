/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.File;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;

/**
 *
 * @author amost
 */
public class HandlerExistingFileTrigger extends BaseHandlerTrigger{
    
    private Label labelFile = new Label();
    private TextField textFile = new TextField();
    private Button selectFolderBtn = new Button();
    private Label labelFolder = new Label();
    private Label labelSelectedFolder = new Label();
    private String folder;
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Existing file")){
            ap.getChildren().clear();
            ap.setId("ExistingFilePane");
            
            labelFile.setText("Write the file to check");
            ap.getChildren().add(labelFile);
            labelFile.setLayoutX(75);
            labelFile.setLayoutY(5);
            
            textFile.setPromptText("Your file...");
            ap.getChildren().add(textFile);
            textFile.setLayoutX(75);
            textFile.setLayoutY(41);
            
            labelFolder.setText("Select the folder");
            ap.getChildren().add(labelFolder);
            labelFolder.setLayoutX(75);
            labelFolder.setLayoutY(91);

            selectFolderBtn.setText("Select folder");
            ap.getChildren().add(selectFolderBtn);
            selectFolderBtn.setLayoutX(100);
            selectFolderBtn.setLayoutY(125);
            
            labelSelectedFolder.setText("");
            ap.getChildren().add(labelSelectedFolder);
            labelSelectedFolder.setLayoutX(75);
            labelSelectedFolder.setLayoutY(170);
         
            selectFolderBtn.setOnAction(event -> folder = chooseFolder());
            
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
    @Override 
    public Trigger handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("ExistingFilePane")){
            if(folder!=null)
                return new ExistingFileTrigger(folder, textFile.getText()); 
            return null;
        }else{
            return super.handleBehaviour(ap);
        }
    }
    
    private String chooseFolder() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Select a folder");
        
        // Ottieni il percorso selezionato
        File selectedDirectory = directoryChooser.showDialog(selectFolderBtn.getScene().getWindow());
        
        // Controlla se l'utente ha selezionato una cartella
        if (selectedDirectory != null) {
            String[] pathElements = selectedDirectory.getAbsolutePath().split("\\\\");
 
            // Controlla se ci sono almeno due elementi nel percorso
            if (pathElements.length >= 2) {
                String lastFolder = pathElements[pathElements.length - 1];
                String secondToLastFolder = pathElements[pathElements.length - 2];
                String folders = secondToLastFolder + "\\" + lastFolder;
                
                labelSelectedFolder.textProperty().set("Selected folders: " + folders);
            } else {
                // Il percorso ha solo 1 elemento
                labelSelectedFolder.textProperty().set("Selected folder: " + pathElements[0]);
            }
            
            // Salva il percorso completo
            return selectedDirectory.getAbsolutePath();
            
        }
        labelSelectedFolder.textProperty().set("");
        return null;
    }       
}
