/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class HandlerDeleteFileAction extends BaseHandlerAction{
    private Button selectFile = new Button();
    private Label labelFileSelected = new Label();
    private String file;
    
    @Override
    public void handleGUI(AnchorPane ap, String s){ 
        if(s.equals("Delete file")){
            ap.getChildren().clear();
            ap.setId("DeleteFilePane");
            
            selectFile.setText("Select file");
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(103);
            selectFile.setLayoutY(41);
            
            labelFileSelected.setText("");
            ap.getChildren().add(labelFileSelected);
            labelFileSelected.setLayoutX(118);
            labelFileSelected.setLayoutY(86);
            
            selectFile.setOnAction(event ->  file = chooseFile(labelFileSelected));

        }else{
            super.handleGUI(ap, s);
        }      
    }
    
    @Override 
    public Action handleBehaviour(AnchorPane ap){
        if(ap.getId().equals("DeleteFilePane")){  
            if(file.isEmpty())
                return null;
            else
                return new DeleteFileAction(file);          
        }
        else{
            return super.handleBehaviour(ap);
        }
    }
    
    public String chooseFile(Label label){
        FileChooser fileChooser = new FileChooser();
        String filename;
        String file;
        
        file = fileChooser.showOpenDialog(selectFile.getScene().getWindow()).getAbsolutePath();
        if(!file.isEmpty()){
            filename = file.substring(file.lastIndexOf('\\')+1);
            label.textProperty().set("Selected file: " + filename);
        }
        
        return file;
    }
}

