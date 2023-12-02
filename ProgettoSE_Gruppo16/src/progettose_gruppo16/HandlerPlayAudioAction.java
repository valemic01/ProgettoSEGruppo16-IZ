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
public class HandlerPlayAudioAction extends BaseHandlerAction{
    private Button selectAudioBtn = new Button();
    private Label labelAudioSelected = new Label();
    private String fileAudio;
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Play audio")){
            ap.getChildren().clear();
            ap.setId("PlayAudioPane");
            
            selectAudioBtn.setText("Select audio");
            ap.getChildren().add(selectAudioBtn);
            selectAudioBtn.setLayoutX(103);
            selectAudioBtn.setLayoutY(41);
            
            labelAudioSelected.setText("");
            ap.getChildren().add(labelAudioSelected);
            labelAudioSelected.setLayoutX(70);
            labelAudioSelected.setLayoutY(86);
            
            selectAudioBtn.setOnAction(event -> chooseFile());
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
    @Override 
    public Action handleBehaviour(AnchorPane ap){   
        if(ap.getId().equals("PlayAudioPane")){
            if(fileAudio.isEmpty())
                return null;
            else
                return new PlayAudioAction(fileAudio);            
        }
        else{
            return super.handleBehaviour(ap);
        }
    }
    
    public void chooseFile(){
        FileChooser fileChooser = new FileChooser();
        String filename;
        
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("File Audio WAV (*.wav)", "*.wav");
            fileChooser.getExtensionFilters().add(filter);
            fileAudio = fileChooser.showOpenDialog(selectAudioBtn.getScene().getWindow()).getAbsolutePath();
            if(!fileAudio.isEmpty()){
                filename = fileAudio.substring(fileAudio.lastIndexOf('\\')+1);
                labelAudioSelected.textProperty().set("Selected audio: " + filename);
            }
    }
    
}
