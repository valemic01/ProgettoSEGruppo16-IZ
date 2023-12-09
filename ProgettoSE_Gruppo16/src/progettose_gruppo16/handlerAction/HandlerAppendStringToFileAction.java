package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.Action;
import progettose_gruppo16.action.AppendStringToFileAction;
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
 * HandlerAppendStringToFileAction is a class that manages the GUI and functionalities 
 * required for adding text to a file. 
 * It extends the BaseHandlerAction class.
 * @author valentina
 */
public class HandlerAppendStringToFileAction extends BaseHandlerAction{
    private TextField textToAppend = new TextField();
    private Label labelTextField = new Label();
    private Button selectFile = new Button();
    private Label labelSelectedFile = new Label();
    private String filePath;
    
    /**
     * Constructor
     * @param ap AnchorPane where GUI components are positioned.
     * @param cb Combo Box containing the action selected by the user.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){ 
        if(cb.getValue().equals("Add text to file")){
            // Clear existing GUI components
            ap.getChildren().clear();
            filePath = "";
            ap.setId("AppendStringPane");
            
            // Configure the text field for user input
            textToAppend.setPromptText("Your text...");
            ap.getChildren().add(textToAppend);
            textToAppend.setLayoutX(0);
            textToAppend.setLayoutY(5);
            textToAppend.setPrefWidth(200);
            
            // Configure the "Select file" button
            selectFile.setText("Select file");
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(250);
            selectFile.setLayoutY(5);
            selectFile.setPrefWidth(100);
            
            // Configure the label to show the selected file
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(40);          
            labelSelectedFile.setPrefWidth(400);
            labelSelectedFile.setAlignment(Pos.CENTER);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            // Associate the "Select file" button with an action to choose a file
            selectFile.setOnAction(event -> filePath = chooseFile(labelSelectedFile));
        }else{
            super.handleGUI(ap, cb);
        }      
    }
    
    /**
     * Handles behavior based on the state of the AnchorPane.
     * @param ap The AnchorPane representing the current state.
     * @return An instance of Action based on the current state.
     */
    @Override 
    public Action handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("AppendStringPane")){     
            if(filePath!=null  && textToAppend.getText()!=null && !textToAppend.getText().isEmpty()){
                return new AppendStringToFileAction(filePath, textToAppend.getText());   
            }else
                return null;
        }else{
           return super.handleBehaviour(ap);
        }
    } 
    
    /**
     * Opens a dialog window to allow the user to select a text file.
     * Overrides the superclass method, specifying the file extension.
     * @param lbl The label used to display information about the selected file.
     * @return The absolute path of the selected file or null if no file is selected.
     */
    @Override
    public String chooseFile(Label lbl){
        FileChooser fileChooser = new FileChooser();
        File file;
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("TXT files (*.txt)", "*.txt");
        
        fileChooser.getExtensionFilters().add(filter);
        file = fileChooser.showOpenDialog(lbl.getScene().getWindow());
        if(file!=null){
            lbl.textProperty().set("Selected file: " + file.getName());
            return file.getAbsolutePath();
        }else{
            lbl.textProperty().set("File not selected");
            return null;
        }
    }
   
}
