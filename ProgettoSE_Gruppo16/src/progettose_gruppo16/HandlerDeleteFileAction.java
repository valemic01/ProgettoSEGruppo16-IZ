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
import javafx.stage.FileChooser;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class HandlerDeleteFileAction extends BaseHandlerAction{
    private Button selectFile = new Button();
    private Label labelFileSelected = new Label();
    private String filePath;
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Delete file")){
            ap.getChildren().clear();
            filePath = "";
            ap.setId("DeleteFilePane");
            
            selectFile.setText("Select file");
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(148);
            selectFile.setLayoutY(45);
            
            labelFileSelected.setText("");
            ap.getChildren().add(labelFileSelected);
            labelFileSelected.setLayoutX(0);
            labelFileSelected.setLayoutY(85);
            labelFileSelected.setPrefWidth(400);
            labelFileSelected.setAlignment(Pos.CENTER);
            
            selectFile.setOnAction(event ->  filePath = chooseFile(labelFileSelected));

        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
    @Override 
    public Action handleBehaviour(AnchorPane ap){
        if(ap.getId().equals("DeleteFilePane")){  
            if(filePath == null)
                return null;
            else
                return new DeleteFileAction(filePath);          
        }else{
            return super.handleBehaviour(ap);
        }
    }
}

