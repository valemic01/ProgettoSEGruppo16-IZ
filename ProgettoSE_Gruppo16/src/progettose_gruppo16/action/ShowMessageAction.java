package progettose_gruppo16.action;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class that implements the Action interface to display a message
 * written by the user on the screen when the trigger is activated.
 * The message must be explicitly closed by the user.
 */
public class ShowMessageAction implements Action{
    
    private final String message;

    /**
     * Constructor of the class that implements the action of displaying a 
     * message specified by the user on the screen.
     * @param message -> Message written by the user through the GUI.
     */
    public ShowMessageAction(String message) {
        this.message = message;
    }

    /**
     * Method implemented by the Action interface. Creates an Alert containing the user's message
     * and a button to close it. Platform.runLater is used to place the method containing the alert
     * in an event queue on the JavaFX Application Thread to be scheduled and executed when possible.
     * This is necessary because every interaction with the interface must happen through the Application Thread.
     */
    @Override
    public void executeAction() {    
        
        Platform.runLater(() -> {
            Alert dialogBox = new Alert(AlertType.NONE, message, ButtonType.OK);
            Stage stage = (Stage) dialogBox.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("messageAlert.png")));
            dialogBox.setTitle(" REMINDER");
            dialogBox.show();
        });
        
    }

    /**
     * Returns a textual representation of the class.
     * @return A string representing the ShowMessageAction object.
     */
    @Override
    public String toString() {
        return "Show the message: " + message + '\n';
    }
    
}