/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.Action;
import progettose_gruppo16.action.ExecuteProgramAction;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author amost
 */
public class HandlerExecuteProgramAction extends BaseHandlerAction{
    
    private Label labelFile = new Label();
    private Button selectFileBtn = new Button();
    private Label labelSelectedFile = new Label();
    private Label labelArguments = new Label();
    private TextField textArguments = new TextField();
    private String file;
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Execute program")){
            ap.getChildren().clear();
            ap.setId("ExecuteProgramPane");
            
            labelFile.setText("Select a file");
            ap.getChildren().add(labelFile);
            labelFile.setLayoutX(75);
            labelFile.setLayoutY(5);
            
            selectFileBtn.setText("Select file");
            ap.getChildren().add(selectFileBtn);
            selectFileBtn.setLayoutX(100);
            selectFileBtn.setLayoutY(40);
            
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(75);
            labelSelectedFile.setLayoutY(75);
            
            labelArguments.setText("Command Line Arguments ");
            ap.getChildren().add(labelArguments);
            labelArguments.setLayoutX(75);
            labelArguments.setLayoutY(100);
            
            textArguments.setPromptText("Arguments...");
            ap.getChildren().add(textArguments);
            textArguments.setLayoutX(75);
            textArguments.setLayoutY(130);
            
            
            selectFileBtn.setOnAction(event -> file = chooseFile());
            
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
    @Override 
    public Action handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("ExecuteProgramPane")){
            if(file!=null){
                if(textArguments.getText().isEmpty())
                    textArguments.setText("");
 
                return new ExecuteProgramAction(file, textArguments.getText());
            }else
                return null;
        }else{
            return super.handleBehaviour(ap);
        }
    } 
    
    
    private String chooseFile(){
        FileChooser fileChooser = new FileChooser();
        String path;
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("Accepted extensions (*.exe), (*.jar)", "*.exe", "*.jar");
        fileChooser.getExtensionFilters().add(filter);
        File filePath = fileChooser.showOpenDialog(selectFileBtn.getScene().getWindow());
        if(filePath!=null){           
            labelSelectedFile.setText("Selected file: " + filePath.getName());
            return filePath.getAbsolutePath();
        }
        labelSelectedFile.setText("");
        return null;
    }
}
