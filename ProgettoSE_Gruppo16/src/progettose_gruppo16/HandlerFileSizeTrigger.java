/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

/**
 *
 * @author raffa
 */
public class HandlerFileSizeTrigger extends BaseHandlerTrigger{

    private Button selectFile = new Button("Select file");
    private TextField fileSize = new TextField();
    private ComboBox<String> fileUnit = new ComboBox<>();
    private Label labelSelectedFile = new Label();
    private File file;
    
    @Override
    public Trigger handleBehaviour(AnchorPane ap) {
        long size;
        size = Long.parseLong(fileSize.getText());
        
        if(ap.getId().equalsIgnoreCase("FileSizePane")){
            if(!file.exists()){
                return null;
            }
            else return new FileSizeTrigger(file, size, fileUnit.getSelectionModel().getSelectedIndex());
        }
        else return super.handleBehaviour(ap);
    }

    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn) {
        if(s.equalsIgnoreCase("FIle size")){
            ap.getChildren().clear();
            ap.setId("FileSizePane");
            
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(14);
            selectFile.setLayoutY(7);
            
            ap.getChildren().add(fileSize);
            fileSize.setLayoutX(127);
            fileSize.setLayoutY(7);
            fileSize.setPrefSize(64.0, 31.0);
            
            fileUnit.getItems().addAll("B", "KB", "MB", "GB");
            ap.getChildren().add(fileUnit);
            fileUnit.setLayoutX(203);
            fileUnit.setLayoutY(7);
            fileUnit.setPrefSize(78.0, 31.0);
            fileUnit.setValue("B");
            
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(29);
            labelSelectedFile.setLayoutY(48);
            
            selectFile.setOnAction(event -> file = chooseFile());
        }
        else{
            super.handleGUI(ap, s, btn);
        }
    }
    
    private File chooseFile(){
        FileChooser fileChooser = new FileChooser();
        File filePath;
        
        filePath = fileChooser.showOpenDialog(selectFile.getScene().getWindow());
        if(filePath.exists()){
            labelSelectedFile.setText("Selected file: " + filePath.getName());
        }
        
        return filePath;
    }
    
}
