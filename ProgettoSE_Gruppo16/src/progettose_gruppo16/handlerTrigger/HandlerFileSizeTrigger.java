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
 * Handler class for the "FileSizeTrigger" trigger.
 * Handles the behavior and GUI for the FileSizeTrigger.
 */
public class HandlerFileSizeTrigger extends BaseHandlerTrigger{

    /**
     * When the "File size" trigger is selected, dynamically creates elements
     * to select the file and set the threshold size.
     * @param ap AnchorPane to modify
     * @param cb ComboBox representing the trigger type
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {        
        Button selectFile;
        TextField fileSize;
        ComboBox<String> fileUnit;
        Label labelSelectedFile;
        Label filePath;
        Label labelErrorSize;
   
        if(cb.getValue().equalsIgnoreCase("FIle size")){
            ap.getChildren().clear();
            ap.setId("FileSizePane");
            
            selectFile = new Button("Select file");
            fileSize = new TextField();
            fileUnit = new ComboBox<>();
            labelSelectedFile = new Label();
            filePath = new Label();
            labelErrorSize = new Label();
            
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
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(37);
            labelSelectedFile.setPrefWidth(344);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            // invisible label to pass the file path string to the handleBehaviour method
            filePath.setText("");
            filePath.setVisible(false);
            ap.getChildren().add(filePath);
            
            labelErrorSize.setText("");
            ap.getChildren().add(labelErrorSize);
            labelErrorSize.setLayoutX(220);
            labelErrorSize.setLayoutY(37);
            labelErrorSize.setPrefWidth(344);
            labelErrorSize.setTextFill(Color.RED);
            
            selectFile.setOnAction(event -> chooseFile(labelSelectedFile, filePath));
        }
        else{
            super.handleGUI(ap, cb);
        }
    }
    
    /**
     * When the user adds the rule, the reference to the selected file (if present),
     * the reference size value, and its unit of measure (by index) are taken and passed
     * to the trigger constructor.
     * @param ap AnchorPane containing the GUI elements
     * @param ht HandlerTrigger instance
     * @param x Integer parameter for trigger behavior
     * @param notVBox VBox containing "not" CheckBox elements
     * @return Trigger object representing the file size trigger
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox) {
        long size;
        File file;
        TextField fileSize;
        ComboBox<String> fileUnit;
        boolean not;
        Label labelErrorSize;
        
        if(ap.getId().equalsIgnoreCase("FileSizePane")){
            file = new File(((Label) ap.getChildren().get(4)).getText());
            fileSize = (((TextField) ap.getChildren().get(1)));
            fileUnit = (((ComboBox<String>) ap.getChildren().get(2)));
            
            labelErrorSize = (Label)ap.getChildren().get(5);
            labelErrorSize.setText("");
             if(!fileSize.getText().matches("\\d*")){
                    labelErrorSize.setText("You must write a number!");
                    return null;
                }
             
            if(fileSize.getText().isEmpty() || file.getName().isEmpty()){
                return null;
            }
            else{
                size = Long.parseLong(fileSize.getText());
                not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
                return new FileSizeTrigger(file, size, fileUnit.getSelectionModel().getSelectedIndex(), not);
                //To the trigger constructor is given the index representing the measurament unit(0 = B, 1 = KB, 2 = MB, 3 = GB)

            }
        }
        else return super.handleBehaviour(ap, ht, x, notVBox);
    }

    /**
     * Method to choose a file from a dialog window and display its name through a label.
     * @param labelSelectedFile Label displaying the selected file
     * @param filePath The label to pass the file path.
     */
    private void chooseFile(Label labelSelectedFile, Label filePath){
        FileChooser fileChooser = new FileChooser();
        File file;
        
        file = fileChooser.showOpenDialog(labelSelectedFile.getScene().getWindow());
        if(file!=null && !file.getAbsolutePath().isEmpty()){
            filePath.setText(file.getAbsolutePath());
            labelSelectedFile.textProperty().set("Selected file: " + file.getName());
        }else{
            labelSelectedFile.textProperty().set("File not selected");
        }
    }   
}
