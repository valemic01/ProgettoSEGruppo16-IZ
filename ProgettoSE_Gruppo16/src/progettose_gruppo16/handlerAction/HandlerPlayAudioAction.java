/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 *Classe che gestisce la GUI e le funzionalità necessarie al fine di 
 * riprodurre un file audio. Estende la classe BaseHandlerAction.
 * @author valentina <your.name at your.org>
 */
public class HandlerPlayAudioAction extends BaseHandlerAction{
    private Button selectAudioBtn = new Button();
    private Label labelAudioSelected = new Label();
    private String filePath;
    
    /**
     * Gestisce i componenti GUI in base all'azione specificata.
     *
     * @param ap  L'AnchorPane dove sono posizionati i componenti GUI.
     * @param cb
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){ 
        if(cb.getValue().equals("Play audio")){
            // Cancella i componenti GUI esistenti
            ap.getChildren().clear();
            filePath = "";
            ap.setId("PlayAudioPane");
            
            // Configura il pulsante "Select audio"
            selectAudioBtn.setText("Select audio");
            ap.getChildren().add(selectAudioBtn);
            selectAudioBtn.setLayoutX(148);
            selectAudioBtn.setLayoutY(45);
            
            // Configura l'etichetta per mostrare l'audio selezionato
            labelAudioSelected.setText("");
            ap.getChildren().add(labelAudioSelected);
            labelAudioSelected.setLayoutX(0);
            labelAudioSelected.setLayoutY(85);
            labelAudioSelected.setPrefWidth(400);
            labelAudioSelected.setAlignment(Pos.CENTER);
            labelAudioSelected.setTextFill(Color.web("#009999"));
            
            // Associa il pulsante "Select audio" a un'azione per scegliere un file audio
            selectAudioBtn.setOnAction(event -> filePath = chooseFile(labelAudioSelected));
        }else{
            super.handleGUI(ap, cb);
        }      
    }
    
    /**
     * Gestisce il comportamento in base allo stato dell'AnchorPane.
     *
     * @param ap L'AnchorPane che rappresenta lo stato corrente.
     * @return Un'istanza di Action basata sullo stato corrente.
     */
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
    
    /**
     * Consente all'utente di selezionare un file audio attraverso una finestra di dialogo.
     *
     * @param lbl L'etichetta per mostrare il risultato della selezione.
     * @return Il percorso assoluto del file audio selezionato o null se nessun file è stato selezionato.
     */
    @Override
    public String chooseFile(Label lbl){
        FileChooser fileChooser = new FileChooser();
        File fileAudio;
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("File Audio WAV (*.wav), (*.mp3)", "*.wav", "*.mp3");
        
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
    
