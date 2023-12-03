/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.trigger.FileSizeTrigger;
import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 * Classe handler che gestisce il trigger "FileSizeTrigger"
 * @author raffa
 */
public class HandlerFileSizeTrigger extends BaseHandlerTrigger{

    private Button selectFile = new Button("Select file");
    private TextField fileSize = new TextField();
    private ComboBox<String> fileUnit = new ComboBox<>();
    private Label labelSelectedFile = new Label();
    private File file;
    
    /**
     * Quando l'utente aggiunge la regola vengono presi il riferimento al file selezionato (se presente),
     * il valore della dimensione di riferimento e la sua unità di misura (tramite indice), e passati
     * al costruttore del trigger
     * @param ap
     * @return
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap) {
        long size;
        if(ap.getId().equalsIgnoreCase("FileSizePane")){
            if(file == null || fileSize.getText().isEmpty()){
                return null;
            }
            else{
                size = Long.parseLong(fileSize.getText());
                return new FileSizeTrigger(file, size, fileUnit.getSelectionModel().getSelectedIndex());
                //Al costruttore del trigger viene passato l'indice della relativa unità di misura all'interno della lista (0 = B, 1 = KB, 2 = MB, 3 = GB)

            }
        }
        else return super.handleBehaviour(ap);
    }

    /**
     * Quando si seleziona il trigger "File size" vengono creati dinamicamente gli elementi
     * che permettono di selezionare il file ed impostare la dimensione di soglia
     * @param ap
     * @param s
     * @param btn
     */
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn) {
        if(s.equalsIgnoreCase("FIle size")){
            ap.getChildren().clear();
            ap.setId("FileSizePane");
            
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(19);
            selectFile.setLayoutY(7);
            
            ap.getChildren().add(fileSize);
            fileSize.setLayoutX(132);
            fileSize.setLayoutY(7);
            fileSize.setPrefSize(64.0, 31.0);
            
            fileUnit.getItems().addAll("B", "KB", "MB", "GB");
            ap.getChildren().add(fileUnit);
            fileUnit.setLayoutX(208);
            fileUnit.setLayoutY(7);
            fileUnit.setPrefSize(78.0, 31.0);
            fileUnit.setValue("B");
            
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(-15);
            labelSelectedFile.setLayoutY(48);
            labelSelectedFile.setPrefWidth(344);
            labelSelectedFile.setAlignment(Pos.CENTER);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            selectFile.setOnAction(event -> file = chooseFile());
        }
        else{
            super.handleGUI(ap, s, btn);
        }
    }
    
    // Metodo per selezionare un file da finestra di dialogo e mostrarne il nome tramite label
    private File chooseFile(){
        FileChooser fileChooser = new FileChooser();
        File filePath;
        filePath = fileChooser.showOpenDialog(labelSelectedFile.getScene().getWindow());
        if(filePath!=null){
            labelSelectedFile.textProperty().set("Selected file: " + filePath.getName());
            return filePath;
        }else{
            labelSelectedFile.textProperty().set("File not selected");
            return null;
        }
    }
    
}
