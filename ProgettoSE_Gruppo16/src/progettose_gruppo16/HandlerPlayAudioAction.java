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
public class HandlerPlayAudioAction extends BaseHandlerAction{
    private Button selectAudioBtn = new Button();
    private Label labelAudioSelected = new Label();
    private String filePath;
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Play audio")){
            ap.getChildren().clear();
            filePath = "";
            ap.setId("PlayAudioPane");
            
            selectAudioBtn.setText("Select audio");
            ap.getChildren().add(selectAudioBtn);
            selectAudioBtn.setLayoutX(148);
            selectAudioBtn.setLayoutY(45);
            
            labelAudioSelected.setText("");
            ap.getChildren().add(labelAudioSelected);
            labelAudioSelected.setLayoutX(0);
            labelAudioSelected.setLayoutY(85);
            labelAudioSelected.setPrefWidth(400);
            labelAudioSelected.setAlignment(Pos.CENTER);
            
            selectAudioBtn.setOnAction(event -> filePath = chooseFile(labelAudioSelected));
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
    @Override 
    public Action handleBehaviour(AnchorPane ap){   
        if(ap.getId().equals("PlayAudioPane")){
            if(filePath!=null)
                return new PlayAudioAction(filePath);     
            else
                return null;
        }else
            return super.handleBehaviour(ap);
    }
    
    @Override
    public String chooseFile(Label lbl){
        FileChooser fileChooser = new FileChooser();
        File fileAudio;
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("File Audio WAV (*.wav)", "*.wav");
        
        fileChooser.getExtensionFilters().add(filter);
        fileAudio = fileChooser.showOpenDialog(selectAudioBtn.getScene().getWindow());
        
        if(fileAudio!=null){
            lbl.textProperty().set("Selected audio: " + fileAudio.getName());
            return fileAudio.getAbsolutePath();
        }else{
            lbl.textProperty().set("Audio not selected");
            return null;
        }
    }
}
    
