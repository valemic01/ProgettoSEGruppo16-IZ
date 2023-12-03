/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
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
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Copy file")){
            ap.getChildren().clear();
            sourcePath = "";
            destinationPath = "";
            ap.setId("CopyFilePane");
            
            labelSourceFile.setText("File to copy");
            ap.getChildren().add(labelSourceFile);
            labelSourceFile.setLayoutX(158);
            labelSourceFile.setLayoutY(37);
            
            selectSourceFile.setText("Select file");
            ap.getChildren().add(selectSourceFile);
            selectSourceFile.setLayoutX(156);
            selectSourceFile.setLayoutY(67);
            
            selectedFile.setText("");
            ap.getChildren().add(selectedFile);
            selectedFile.setLayoutX(0);
            selectedFile.setLayoutY(106);
            selectedFile.setPrefWidth(400);
            selectedFile.setAlignment(Pos.CENTER);
            selectedFile.setTextFill(Color.web("#009999"));
            
            labelDestinationFile.setText("Where you want to copy the file");
            ap.getChildren().add(labelDestinationFile);
            labelDestinationFile.setLayoutX(91);
            labelDestinationFile.setLayoutY(146);
            
            selectDestinationFile.setText("Select destination");
            ap.getChildren().add(selectDestinationFile);
            selectDestinationFile.setLayoutX(131);
            selectDestinationFile.setLayoutY(176);
            
            selectedDestination.setText("");
            ap.getChildren().add(selectedDestination);
            selectedDestination.setLayoutX(0);
            selectedDestination.setLayoutY(218);
            selectedDestination.setPrefWidth(400);
            selectedDestination.setAlignment(Pos.CENTER);
            selectedDestination.setTextFill(Color.web("#009999"));
            
            selectSourceFile.setOnAction(event -> sourcePath = chooseFile(selectedFile));
            selectDestinationFile.setOnAction(event -> destinationPath = chooseDirectory(selectedDestination));
          
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
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


