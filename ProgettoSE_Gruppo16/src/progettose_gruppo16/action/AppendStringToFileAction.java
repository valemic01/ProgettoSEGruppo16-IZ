package progettose_gruppo16.action;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class designed to perform the action of appending a string to an existing file.
 * @author valentina
 */
public class AppendStringToFileAction implements Action {
    private final String filePath;
    private final String textToAppend;
    private String filename;
 
    /**
     * Constructor of the class that initializes the file path and the string to append.
     *
     * @param filePath     The path of the file to which the string will be appended.
     * @param textToAppend The string to append to the file.
     */
    public AppendStringToFileAction(String filePath, String textToAppend) {
        this.filePath = filePath;
        this.textToAppend = textToAppend;
    }
    
     /**
     * Adds text to the file.
     * Uses PrintWriter and BufferedWriter to ensure efficient writing and handle exceptions.
     */
    @Override
    public void executeAction() {        
        PrintWriter pw = null;
        File file = new File(filePath);
        Alert.AlertType alert;
        String message;
        
        if(file.exists()){
            synchronized(file){
                try {
                    pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true))); //true indica che il file deve essere sovrascritto
                    pw.append(textToAppend + "\n");
                } catch (IOException ex) {
                    Logger.getLogger(AppendStringToFileAction.class.getName()).log(Level.SEVERE, null, ex);
                } finally{
                    pw.close();
                }
            }
            filename = file.getName();
            alert = INFORMATION;
            message = textToAppend + "\n     has been added into\n " + filename;
        }else{
            alert = ERROR;
            message = "Error while adding text to file:\n" + filename + " \n    doesn't exist";
        }
        
        Platform.runLater(() -> {
            Alert dialogBox = new Alert(alert, message, ButtonType.OK);
            Stage stage = (Stage) dialogBox.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("messageAlert.png")));
            dialogBox.setTitle(" TEXT ADDING");
            dialogBox.show();
        });
    }
    
    /**
     * Returns a textual representation of the class.
     * @return A string representing the AppendStringToFileAction object.
     */
    @Override
    public String toString() {
        return "AppendStringToFileAction: " + filename + " - " + textToAppend;
    }
    
}