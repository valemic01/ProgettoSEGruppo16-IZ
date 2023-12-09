package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.MoveFileAction;
import progettose_gruppo16.action.Action;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * Class that manages the GUI and functionalities necessary for file movement.
 * Extends the BaseHandlerAction class.
 * @author valentina 
 */
public class HandlerMoveFileAction extends BaseHandlerAction{
    private Button selectSourceFile = new Button();
    private Label selectedFile = new Label();
    private Button selectDestinationFile = new Button();
    private Label selectedDestination = new Label();
    private String sourcePath;
    private String destinationPath;
    
    /**
     * Manages GUI components based on the specified action.
     * @param ap The AnchorPane where GUI components are positioned.
     * @param cb Combo Box containing the action selected by the user.
     * @param btn The button associated with the action.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb, Button btn){ 
        if(cb.getValue().equals("Move file")){
            // Clear existing GUI components
            ap.getChildren().clear();
            sourcePath = "";
            destinationPath = "";
            ap.setId("MoveFilePane");
          
            // Configure the "Select file" button
            selectSourceFile.setText("File to move");
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
            super.handleGUI(ap, cb, btn);
        }      
    }
    
    /**
     * Manages behavior based on the state of the AnchorPane.
     * @param ap The AnchorPane representing the current state.
     * @return An instance of Action based on the current state.
     */
    @Override 
    public Action handleBehaviour(AnchorPane ap){
        if(ap.getId().equals("MoveFilePane")){         
            if(sourcePath==null || destinationPath==null || sourcePath.isEmpty() || destinationPath.isEmpty())
                return null;
            else
                return new MoveFileAction(sourcePath, destinationPath);           
        }else{
            return super.handleBehaviour(ap);
        }
    }   
}