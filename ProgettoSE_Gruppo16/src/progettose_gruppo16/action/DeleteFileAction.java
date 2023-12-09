package progettose_gruppo16.action;

import java.io.File;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class that implements the action of deleting a specified file by the user.
 * @author valentina
 */
public class DeleteFileAction implements Action {
    private final String filePath;
    private final File file;
    private final String filename;

    /**
     * Constructor of the class that initializes the path of the file to delete.
     * @param filePath The path of the file to delete.
     */
    public DeleteFileAction(String filePath) {
        this.filePath = filePath;
        this.file = new File(filePath);
        this.filename = file.getName();
    }
    
    /**
     * Method that implements the deletion of a file.
     * Uses the delete() method of the File class to delete the file from the system.
     */
    @Override
    public void executeAction() {    
        String message;
        Alert.AlertType alert;
        boolean deleted;
        
        if(file.exists()){
            synchronized(file){
                deleted = file.delete();
            }
            if(!deleted){                
                message = "Error while deleting " + filename;
                alert = ERROR;
            }else{           
                message = filename + " has been deleted successfully";
                alert = INFORMATION;
            }
        }else{
            message = "Error while deleting: \n" + filename + " \n  doesn't exist";
            alert = ERROR;
        }
        
        Platform.runLater(() -> {
            Alert dialogBox = new Alert(alert, message, ButtonType.OK);
            Stage stage = (Stage) dialogBox.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("messageAlert.png")));
            dialogBox.setTitle(" FILE DELETING");
            dialogBox.show();
        });
        
    }
    
    /**
     * Returns a textual representation of the class.
     * @return A string representing the DeleteFileAction object.
     */
    @Override
    public String toString() {
        return "DeleteFileAction: " + filename + '\n';
    }
    
}