package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.CopyFileAction;
import progettose_gruppo16.action.Action;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * Class that manages the GUI and functionalities necessary for
 * allowing the copying of a file. It extends the BaseHandlerAction class.
 * @author valentina
 */
public class HandlerCopyFileAction extends BaseHandlerAction{
    private Button selectSourceFile = new Button();
    private Label selectedFile = new Label();
    private Button selectDestinationFile = new Button();
    private Label selectedDestination = new Label();
    private String sourcePath;
    private String destinationPath;
    
    /**
     * Manages GUI components based on the specified action.
     * @param ap  The AnchorPane where GUI components are positioned.
     * @param cb   The combo box containing the action selected by the user.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){ 
        if(cb.getValue().equals("Copy file")){
            // Clear existing GUI components
            ap.getChildren().clear();
            sourcePath = "";
            destinationPath = "";
            ap.setId("CopyFilePane");
            
            // Configure the "Select file" button
            selectSourceFile.setText("File to copy");
            ap.getChildren().add(selectSourceFile);
            selectSourceFile.setLayoutX(0);
            selectSourceFile.setLayoutY(5);
            
            // Configure the label to show the selected file
            selectedFile.setText("");
            ap.getChildren().add(selectedFile);
            selectedFile.setLayoutX(0);
            selectedFile.setLayoutY(40);
            selectedFile.setPrefWidth(150);
            selectedFile.setAlignment(Pos.CENTER);
            selectedFile.setTextFill(Color.web("#009999"));
            
            // Configure the "Select destination" button
            selectDestinationFile.setText("Select destination");
            ap.getChildren().add(selectDestinationFile);
            selectDestinationFile.setLayoutX(200);
            selectDestinationFile.setLayoutY(5);
            
            // Configure the label to show the selected destination
            selectedDestination.setText("");
            ap.getChildren().add(selectedDestination);
            selectedDestination.setLayoutX(200);
            selectedDestination.setLayoutY(40);
            selectedDestination.setPrefWidth(150);
            selectedDestination.setAlignment(Pos.CENTER);
            selectedDestination.setTextFill(Color.web("#009999"));
            
            // Associate the "Select file" button with an action to choose a file
            selectSourceFile.setOnAction(event -> sourcePath = chooseFile(selectedFile));
            
            // Associate the "Select destination" button with an action to choose a directory
            selectDestinationFile.setOnAction(event -> destinationPath = chooseDirectory(selectedDestination));
          
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
        if(ap.getId().equals("CopyFilePane")){    
            if(sourcePath==null || destinationPath==null || sourcePath.isEmpty() || destinationPath.isEmpty())
                return null;
            else
                return new CopyFileAction(sourcePath, destinationPath);           
        }else{
            return super.handleBehaviour(ap);
        }
    }     
}
