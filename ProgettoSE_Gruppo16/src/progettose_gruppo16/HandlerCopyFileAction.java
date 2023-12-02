/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class HandlerCopyFileAction extends BaseHandlerAction{
    private Label labelSourceFile = new Label();
    private Button selectSourceFile = new Button();
    private Label labelDestinationFile = new Label();
    private Button selectDestinationFile = new Button();
    private String sourcePath;
    private String destinationPath;
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Copy file")){
            ap.getChildren().clear();
            ap.setId("CopyFilePane");
            
            labelSourceFile.setText("File to copy");
            ap.getChildren().add(labelSourceFile);
            labelSourceFile.setLayoutX(118);
            labelSourceFile.setLayoutY(14);
            
            selectSourceFile.setText("Select file");
            ap.getChildren().add(selectSourceFile);
            selectSourceFile.setLayoutX(103);
            selectSourceFile.setLayoutY(41);

            labelDestinationFile.setText("Where you want to copy the file");
            ap.getChildren().add(labelDestinationFile);
            labelDestinationFile.setLayoutX(118);
            labelDestinationFile.setLayoutY(103);
            
            selectDestinationFile.setText("Select file");
            ap.getChildren().add(selectDestinationFile);
            selectDestinationFile.setLayoutX(103);
            selectDestinationFile.setLayoutY(130);
            
            selectSourceFile.setOnAction(event -> sourcePath = chooseFile(labelSourceFile));
            selectDestinationFile.setOnAction(event -> destinationPath = chooseDirectory(labelDestinationFile));
          
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
    @Override 
    public Action handleBehaviour(AnchorPane ap){
        if(ap.getId().equals("CopyFilePane")){    
            if(sourcePath.isEmpty() || destinationPath.isEmpty())
                return null;
            else
                return new CopyFileAction(sourcePath, destinationPath);           
        }
        else{
            return super.handleBehaviour(ap);
        }
    }
    
    public String chooseFile(Label label){
        FileChooser fileChooser = new FileChooser();
        String filename;
        String file;
        
        file = fileChooser.showOpenDialog(selectSourceFile.getScene().getWindow()).getAbsolutePath();
        if(!file.isEmpty()){
            filename = file.substring(file.lastIndexOf('\\')+1);
            label.textProperty().set("Selected file: " + filename);
        }
        
        return file;
    }
    
    public String chooseDirectory(Label label){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        String destinationPath;
   
        destinationPath = directoryChooser.showDialog(selectSourceFile.getScene().getWindow()).getAbsolutePath();
        if(!destinationPath.isEmpty()){
                label.textProperty().set("Destination: " + destinationPath);
            }       
        
        return destinationPath;
    }
    
    
}


