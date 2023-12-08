/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import progettose_gruppo16.trigger.ExistingFileTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 * Classe che estende la classe BaseHandlerTrigger. 
 */
public class HandlerExistingFileTrigger extends BaseHandlerTrigger{
    
    
    /**
     * Permette all'utente di scrivere il nome del file da controllare 
     * e di selezionare una cartella quando decide di utilizzare il trigger Existing file.
     * @param ap
     * @param cb
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){ 
        if(cb.getValue().equals("Existing file")){
            ap.getChildren().clear();
            ap.setId("ExistingFilePane");
            
            TextField textFile = new TextField();
            Button selectFolderBtn = new Button();
            Label labelSelectedFolder = new Label();
            Label filePath= new Label();
            
            
            textFile.setPromptText("Your file...");
            ap.getChildren().add(textFile);
            textFile.setLayoutX(0);
            textFile.setLayoutY(7);
            

            selectFolderBtn.setText("Select folder");
            ap.getChildren().add(selectFolderBtn);
            selectFolderBtn.setLayoutX(220);
            selectFolderBtn.setLayoutY(7);
            
            labelSelectedFolder.setText("");
            ap.getChildren().add(labelSelectedFolder);
            labelSelectedFolder.setLayoutX(0);
            labelSelectedFolder.setLayoutY(37);
            labelSelectedFolder.setPrefWidth(344);
            labelSelectedFolder.setAlignment(Pos.CENTER);
            labelSelectedFolder.setTextFill(Color.web("#009999"));
         
            filePath.setText("");
            filePath.setVisible(false);
            ap.getChildren().add(filePath);
            
            selectFolderBtn.setOnAction(event -> chooseFolder(labelSelectedFolder, filePath));
            
        }else{
            super.handleGUI(ap, cb);
        }      
    }
    
    /**
     * Se l'utente ha scritto il nome di un file e ha selezionato una cartella, allora viene creato 
     * un oggetto di tipo ExistingFileTrigger
     * @param ap
     * @param ht
     * @param x
     * @param notVBox
     * @return
     */
    @Override 
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox){     
        if(ap.getId().equals("ExistingFilePane")){
            String folder= ((Label) ap.getChildren().get(3)).getText();
            if(folder!=null){
                boolean not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
                return new ExistingFileTrigger(folder, ((TextField) ap.getChildren().get(0)).getText(), not); 
            }
            else return null;
        }else{
            return super.handleBehaviour(ap, ht, x, notVBox);
        }
    }
    
    private void chooseFolder(Label labelSelectedFolder, Label filePath) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory;
        
        directory = directoryChooser.showDialog(labelSelectedFolder.getScene().getWindow());
        if(directory!=null){
            filePath.setText(directory.getAbsolutePath());
            labelSelectedFolder.textProperty().set("Destination: " + directory.getName());
        }else{
            labelSelectedFolder.textProperty().set("Destination not selected");
        }       
    }  
}
