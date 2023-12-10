package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.PlayAudioAction;
import progettose_gruppo16.action.Action;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 * Class that manages the GUI and functionalities necessary for playing an audio file.
 * Extends the BaseHandlerAction class.
 * @author valentina
 */
public class HandlerPlayAudioAction extends HandlerFileSelection{
    private Button selectAudioBtn = new Button();
    private Label labelAudioSelected = new Label();
    private String filePath;
    
    /**
     * Manages GUI components based on the specified action.
     * @param ap The AnchorPane where GUI components are positioned.
     * @param cb Combo Box containing the action selected by the user.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){ 
        if(cb.getValue().equals("Play audio")){
            // Clear existing GUI components
            ap.getChildren().clear();
            filePath = "";
            ap.setId("PlayAudioPane");
            
            // Configure the "Select audio" button
            selectAudioBtn.setText("Select audio");
            ap.getChildren().add(selectAudioBtn);
            selectAudioBtn.setLayoutX(100);
            selectAudioBtn.setLayoutY(5);
            selectAudioBtn.setPrefWidth(200);
            
            // Configure the label to show the selected audio
            labelAudioSelected.setText("");
            ap.getChildren().add(labelAudioSelected);
            labelAudioSelected.setLayoutX(0);
            labelAudioSelected.setLayoutY(40);
            labelAudioSelected.setPrefWidth(400);
            labelAudioSelected.setAlignment(Pos.CENTER);
            labelAudioSelected.setTextFill(Color.web("#009999"));
            
            // Associate the "Select audio" button with an action to choose an audio file
            selectAudioBtn.setOnAction(event -> filePath = chooseFile(labelAudioSelected));
        }else{
            super.handleGUI(ap, cb);
        }      
    }
    
    /**
     * Manages behavior based on the state of the AnchorPane.
     * @param ap The AnchorPane representing the current state.
     * @return An instance of Action based on the current state.
     */
    @Override 
    public Action handleBehaviour(AnchorPane ap){   
        if(ap.getId().equals("PlayAudioPane")){
            if(filePath!=null && !filePath.isEmpty())
                return new PlayAudioAction(filePath);     
            else
                return null;
        }else
            return super.handleBehaviour(ap);
    }
    
    /**
     * Allows the user to select an audio file through a file dialog.
     * @param lbl The label to display the result of the selection.
     * @return The absolute path of the selected audio file or null if no file is selected.
     */
    @Override
    public String chooseFile(Label lbl){
        FileChooser fileChooser = new FileChooser();
        File fileAudio;
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("File Audio WAV (*.wav), (*.mp3)", "*.wav", "*.mp3");
        
        fileChooser.getExtensionFilters().add(filter);
        fileAudio = fileChooser.showOpenDialog(selectAudioBtn.getScene().getWindow());
        
        if(fileAudio!=null && !fileAudio.getName().isEmpty()){
            lbl.textProperty().set("Selected audio: " + fileAudio.getName());
            return fileAudio.getAbsolutePath();
        }else{
            lbl.textProperty().set("Audio not selected");
            return null;
        }
    }
}
