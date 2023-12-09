package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.DeleteFileAction;
import progettose_gruppo16.action.Action;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

/**
 * Class that manages the GUI and functionalities necessary for
 * deleting a file. It extends the BaseHandlerAction class.
 * @author valentina
 */
public class HandlerDeleteFileAction extends BaseHandlerAction{
    private Button selectFile = new Button();
    private Label labelSelectedFile = new Label();
    private String filePath;
    
    /**
     * Manages GUI components based on the specified action.
     * @param ap  The AnchorPane where GUI components are positioned.
     * @param cb  Combo Box containing the action selected by the user.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){ 
        if(cb.getValue().equals("Delete file")){
            // Clear existing GUI components
            ap.getChildren().clear();
            filePath = "";
            ap.setId("DeleteFilePane");
            
            // Configure the "Select file" button
            selectFile.setText("Select file");
            ap.getChildren().add(selectFile);
            selectFile.setLayoutX(100);
            selectFile.setLayoutY(5);
            selectFile.setPrefWidth(200);
            
            // Configure the label to show the selected file
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(40);
            labelSelectedFile.setPrefWidth(400);
            labelSelectedFile.setAlignment(Pos.CENTER);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            // Associate the "Select" button with an action to choose a file
            selectFile.setOnAction(event ->  filePath = chooseFile(labelSelectedFile));

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
        if(ap.getId().equals("DeleteFilePane")){  
            if(filePath == null || filePath.isEmpty())
                return null;
            else
                return new DeleteFileAction(filePath);          
        }else{
            return super.handleBehaviour(ap);
        }
    }
}
