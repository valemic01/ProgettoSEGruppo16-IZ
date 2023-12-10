package progettose_gruppo16.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystemException;
import java.nio.file.Files;
import java.nio.file.Paths;
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
 * Class designed to perform the action of copying a file to a user-specified directory.
 * @author valentina
 */
public class CopyFileAction implements Action {
    private final String fileToCopyPath;
    private String fileDestinationPath;
    private final String filename;
    private final String fileExtension;
    private final int fileExtensionIndex;
    private final String pathBase;
    
    /**
     * Constructor of the class that initializes the paths of the file to copy and the destination path.
     *
     * @param fileToCopyPath      The path of the file to copy.
     * @param fileDestinationPath The destination path where to copy the file.
     */
    public CopyFileAction(String fileToCopyPath, String fileDestinationPath)  {
        this.fileToCopyPath = fileToCopyPath;
        this.fileDestinationPath = fileDestinationPath;
        this.fileExtensionIndex = fileToCopyPath.lastIndexOf('.');
        this.fileExtension = fileToCopyPath.substring(fileExtensionIndex); 
        this.filename = fileToCopyPath.substring(fileToCopyPath.lastIndexOf('\\')+1, fileExtensionIndex);
        this.pathBase = fileDestinationPath + "\\";
    }    
    
    /**
     * Executes the action of copying the file, handling any name collisions.
     * Uses Files.copy to perform the file copy.
     */
    @Override
    public void executeAction() { 
        String message;
        Alert.AlertType alert;
        File file = new File(fileToCopyPath);
        int counterCopy = 0;
        
        
        if(file.exists() && new File(pathBase).exists()){     
            fileDestinationPath = pathBase.concat(filename+fileExtension);
            counterCopy = 1;
            synchronized(file){
                while(new File(fileDestinationPath).exists()){           
                    fileDestinationPath = pathBase.concat(filename + '(' + counterCopy +')' +fileExtension);
                    counterCopy ++;
                }

                try {
                    Files.copy(Paths.get(fileToCopyPath), Paths.get(fileDestinationPath));
                } catch(FileSystemException ex){
                    Platform.runLater(() -> {
                        Alert dialogBox = new Alert(Alert.AlertType.ERROR, "Error while copying file: " + filename + fileExtension + " is currently in use by another process." , ButtonType.OK);
                        Stage stage = (Stage) dialogBox.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("messageAlert.png")));
                        dialogBox.setTitle(" FILE COPYING");
                        dialogBox.show();
                    });

                    return;
                } catch (IOException ex) {
                    Logger.getLogger(CopyFileAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }   
            message = filename + fileExtension + " \n\nhas been copied into\n\n " + pathBase;
            alert = INFORMATION;
        }else{
            message = "Error while copying file: \n" + filename + fileExtension + "\n\nor\n\n" + pathBase + "\n\ndoesn't exist";
            alert = ERROR;
        }
               
        Platform.runLater(() -> {
            Alert dialogBox = new Alert(alert, message, ButtonType.OK);
            Stage stage = (Stage) dialogBox.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("messageAlert.png")));
            dialogBox.setTitle(" FILE COPYING");
            dialogBox.show();
        });
    }
    
    /**
     * Returns a textual representation of the class.
     * @return A string representing the CopyFileAction object.
     */
    @Override
    public String toString() {
        return "CopyFileAction" + ":" + filename + fileExtension + " into " + pathBase + '\n';
    }
    
}