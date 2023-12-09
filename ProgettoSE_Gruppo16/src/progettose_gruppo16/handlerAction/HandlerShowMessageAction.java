package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.ShowMessageAction;
import progettose_gruppo16.action.Action;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * Class that manages the GUI and functionalities necessary for displaying a message.
 * Extends the BaseHandlerAction class.
 * @author valentina
 */
public class HandlerShowMessageAction extends BaseHandlerAction{
    private TextField messTxtBox = new TextField();
          
    /**
     * Manages GUI components based on the specified action.
     * @param ap  The AnchorPane where GUI components are positioned.
     * @param cb Combo Box containing the action selected by the user.
     * @param btn The button associated with the action.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb, Button btn){ 
        if(cb.getValue().equals("Show message")){
            // Clear existing GUI components
            ap.getChildren().clear();
            ap.setId("ShowMessagePane");
           
            // Configure the text box for the message
            messTxtBox.setPromptText("Your text...");
            ap.getChildren().add(messTxtBox);
            messTxtBox.setLayoutX(100);
            messTxtBox.setLayoutY(5);
            messTxtBox.setPrefWidth(200);
            messTxtBox.setPrefHeight(30);
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
        if(ap.getId().equals("ShowMessagePane")){ 
            if(messTxtBox.getText().isEmpty())
                return null;
            else
                return new ShowMessageAction(messTxtBox.getText());
        }
        else{
            return super.handleBehaviour(ap);
        }
    }
}