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
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/**
 * Classe handler che gestisce il trigger "FileSizeTrigger"
 * @author raffa
 */
public class HandlerFileSizeTrigger extends BaseHandlerTrigger{

    
    /**
     * Quando l'utente aggiunge la regola vengono presi il riferimento al file selezionato (se presente),
     * il valore della dimensione di riferimento e la sua unità di misura (tramite indice), e passati
     * al costruttore del trigger
     * @param ap
     * @param ht
     * @param x
     * @param notVBox
     * @return
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox) {
        long size;
        if(ap.getId().equalsIgnoreCase("FileSizePane")){
            File file = new File(((Label) ap.getChildren().get(4)).getText());
            TextField fileSize = (((TextField) ap.getChildren().get(1)));
            ComboBox<String> fileUnit = (((ComboBox<String>) ap.getChildren().get(2)));
            if(file == null || fileSize.getText().isEmpty()){
                return null;
            }
            else{
                size = Long.parseLong(fileSize.getText());
                boolean not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
                return new FileSizeTrigger(file, size, fileUnit.getSelectionModel().getSelectedIndex(), not);
                //Al costruttore del trigger viene passato l'indice della relativa unità di misura all'interno della lista (0 = B, 1 = KB, 2 = MB, 3 = GB)

            }
        }
        else return super.handleBehaviour(ap, ht, x, notVBox);
    }

    /**
     * Quando si seleziona il trigger "File size" vengono creati dinamicamente gli elementi
     * che permettono di selezionare il file ed impostare la dimensione di soglia
     * @param ap
     * @param cb
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        if(cb.getValue().equalsIgnoreCase("FIle size")){
            ap.getChildren().clear();
            ap.setId("FileSizePane");
            
            Button selectFile = new Button("Select file");
            TextField fileSize = new TextField();
            ComboBox<String> fileUnit = new ComboBox<>();
            Label labelSelectedFile = new Label();
            Label filePath = new Label();
            
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
            labelSelectedFile.setLayoutY(37);
            labelSelectedFile.setPrefWidth(344);
            labelSelectedFile.setAlignment(Pos.CENTER);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            filePath.setText("");
            filePath.setVisible(false);
            ap.getChildren().add(filePath);
            
            
            selectFile.setOnAction(event -> chooseFile(labelSelectedFile, filePath));
        }
        else{
            super.handleGUI(ap, cb);
        }
    }
    
    // Metodo per selezionare un file da finestra di dialogo e mostrarne il nome tramite label
    private void chooseFile(Label labelSelectedFile, Label filePath){
        FileChooser fileChooser = new FileChooser();
        File file;
        file = fileChooser.showOpenDialog(labelSelectedFile.getScene().getWindow());
        if(file!=null){
            filePath.setText(file.getAbsolutePath());
            labelSelectedFile.textProperty().set("Selected file: " + file.getName());
        }else{
            labelSelectedFile.textProperty().set("File not selected");
        }
    }   
}
