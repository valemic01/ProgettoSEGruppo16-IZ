/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class HandlerAppendStringToFileAction extends BaseHandlerAction{
    private TextField textToAppend = new TextField();
    private Label labelTextField = new Label();
    private Button selectFile = new Button();
    private Label labelSelectedFile = new Label();
    private String file;
    
    @Override
    public void handleGUI(AnchorPane ap, String s){ 
        if(s.equals("Add text to file")){
            ap.getChildren().clear();
            ap.setId("AppendStringPane");
            
            labelTextField.setText("Insert text to append");
            ap.getChildren().add(labelTextField);
            labelTextField.setLayoutX(118);
            labelTextField.setLayoutY(14);
            
            textToAppend.setPromptText("Your text...");
            ap.getChildren().add(textToAppend);
            textToAppend.setLayoutX(103);
            textToAppend.setLayoutY(41);
            
            selectFile.setText("Select file");
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(118);
            selectFile.setLayoutY(103);
            
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(103);
            labelSelectedFile.setLayoutY(130);
            
            selectFile.setOnAction(event -> file = chooseFile(labelSelectedFile));
        }else{
            super.handleGUI(ap, s);
        }      
    }
    
    @Override 
    public Action handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("AppendStringPane")){                
            if(file.isEmpty())
                return null;
            else
                return new AppendStringToFileAction(file, textToAppend.getText());           
        }else{
            return super.handleBehaviour(ap);
        }
    }
    
    public String chooseFile(Label label){
        FileChooser fileChooser = new FileChooser();
        String filename;
        String file;
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("TXT files (*.txt)", "*.txt");
        
        fileChooser.getExtensionFilters().add(filter);
        file = fileChooser.showOpenDialog(selectFile.getScene().getWindow()).getAbsolutePath();
        if(!file.isEmpty()){
            filename = file.substring(file.lastIndexOf('\\')+1);
            label.textProperty().set("Selected file: " + filename);
        }
        
        return file;
    }
}
