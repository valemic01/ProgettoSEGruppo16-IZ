/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.File;
import javafx.geometry.Pos;
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
    private String filePath;
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Add text to file")){
            ap.getChildren().clear();
            filePath = "";
            ap.setId("AppendStringPane");
            
            labelTextField.setText("Insert text to append");
            ap.getChildren().add(labelTextField);
            labelTextField.setLayoutX(138);
            labelTextField.setLayoutY(38);
            
            textToAppend.setPromptText("Your text...");
            ap.getChildren().add(textToAppend);
            textToAppend.setLayoutX(99);
            textToAppend.setLayoutY(68);
            textToAppend.setPrefWidth(217);
            
            selectFile.setText("Select file");
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(157);
            selectFile.setLayoutY(127);
            
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(171);          
            labelSelectedFile.setPrefWidth(400);
            labelSelectedFile.setAlignment(Pos.CENTER);
            
            selectFile.setOnAction(event -> filePath = chooseFile(labelSelectedFile));
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
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
