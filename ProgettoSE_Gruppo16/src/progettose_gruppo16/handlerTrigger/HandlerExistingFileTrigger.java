/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import java.io.File;
import javafx.beans.binding.Binding;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import progettose_gruppo16.trigger.ExistingFileTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 * Classe che estende la classe BaseHandlerTrigger. 
 */
public class HandlerExistingFileTrigger extends BaseHandlerTrigger{
    
    private Label labelFile = new Label();
    private TextField textFile = new TextField();
    private Button selectFolderBtn = new Button();
    private Label labelFolder = new Label();
    private Label labelSelectedFolder = new Label();
    private String folder;
    
    /**
     * Permette all'utente di scrivere il nome del file da controllare 
     * e di selezionare una cartella quando decide di utilizzare il trigger Existing file.
     * @param ap
     * @param s
     * @param btn
     */
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Existing file")){
            ap.getChildren().clear();
            ap.setId("ExistingFilePane");
            
            labelFile.setText("Write the file to check");
            ap.getChildren().add(labelFile);
            labelFile.setLayoutX(0);
            labelFile.setLayoutY(4);
            labelFile.setPrefWidth(344);
            labelFile.setAlignment(Pos.CENTER);
            
            textFile.setPromptText("Your file...");
            ap.getChildren().add(textFile);
            textFile.setLayoutX(79);
            textFile.setLayoutY(32);
            
            labelFolder.setText("Select the folder");
            ap.getChildren().add(labelFolder);
            labelFolder.setLayoutX(0);
            labelFolder.setLayoutY(75);
            labelFolder.setPrefWidth(344);
            labelFolder.setAlignment(Pos.CENTER);
            

            selectFolderBtn.setText("Select folder");
            ap.getChildren().add(selectFolderBtn);
            selectFolderBtn.setLayoutX(125);
            selectFolderBtn.setLayoutY(104);
            
            labelSelectedFolder.setText("");
            ap.getChildren().add(labelSelectedFolder);
            labelSelectedFolder.setLayoutX(0);
            labelSelectedFolder.setLayoutY(138);
            labelSelectedFolder.setPrefWidth(344);
            labelSelectedFolder.setAlignment(Pos.CENTER);
            labelSelectedFolder.setTextFill(Color.web("#009999"));
         
            selectFolderBtn.setOnAction(event -> folder = chooseFolder());
            
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
    /**
     * Se l'utente ha scritto il nome di un file e ha selezionato una cartella, allora viene creato 
     * un oggetto di tipo ExistingFileTrigger
     * @param ap
     * @return
     */
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
        File directory;
        
        directory = directoryChooser.showDialog(labelSelectedFolder.getScene().getWindow());
        if(directory!=null){
            labelSelectedFolder.textProperty().set("Destination: " + directory.getName());
            return directory.getAbsolutePath();
        }else{
            labelSelectedFolder.textProperty().set("Destination not selected");
            return null;
        }       
    }  
}
