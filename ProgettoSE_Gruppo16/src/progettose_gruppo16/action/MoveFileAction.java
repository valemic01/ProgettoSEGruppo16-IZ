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
 * Class that implements the action of moving a file to a user-specified directory.
 * @author valentina
 */
public class MoveFileAction implements Action{
    private final String fileSourcePath;
    private String fileDestinationPath;
    private String filename;
    private String fileExtension;
    private int fileExtensionIndex;
    private String pathBase;


    /**
     * Constructor of the class that initializes the paths of the file to move and the destination path.
     * @param fileSourcePath      The path of the file to move.
     * @param fileDestinationPath The destination path where to move the file.
     */
    public MoveFileAction(String fileSourcePath, String fileDestinationPath) {
        this.fileSourcePath = fileSourcePath;
        this.fileDestinationPath = fileDestinationPath;
        this.fileExtensionIndex = fileSourcePath.lastIndexOf('.');
        this.fileExtension = fileSourcePath.substring(fileExtensionIndex); 
        this.filename = fileSourcePath.substring(fileSourcePath.lastIndexOf('\\')+1, fileExtensionIndex);
        this.pathBase = fileDestinationPath + "\\";
    }
    
    /**
     * Executes the action of moving the file, handling any name collisions.
     * Uses Files.move to perform the file move.
     */
    @Override
    public void executeAction() {   
        String message;
        Alert.AlertType alert;
        File file = new File(fileSourcePath);
        
        System.out.println("ciaoo " + file.exists() + " " + new File(pathBase).exists());
        if(file.exists() && new File(pathBase).exists()){
            int counterCopy = 1;          
            
            fileDestinationPath = pathBase.concat(filename + fileExtension);
            
            synchronized(file){
                while(new File(fileDestinationPath).exists()){
                    fileDestinationPath = pathBase.concat(filename + '(' + counterCopy +')' +fileExtension);
                    counterCopy ++;
                }

                try {
                    System.out.println(fileSourcePath + " " + fileDestinationPath);
                    Files.move(Paths.get(fileSourcePath), Paths.get(fileDestinationPath));
                } catch(FileSystemException ex){
                    Platform.runLater(() -> {
                        Alert dialogBox = new Alert(Alert.AlertType.ERROR, "Error while moving file: " + filename + fileExtension + " is currently in use by another process." , ButtonType.OK);
                        Stage stage = (Stage) dialogBox.getDialogPane().getScene().getWindow();
                        stage.getIcons().add(new Image(getClass().getResourceAsStream("messageAlert.png")));
                        dialogBox.setTitle(" FILE MOVING");
                        dialogBox.show();
                    });

                    return;
                } catch (IOException ex) {
                    Logger.getLogger(CopyFileAction.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            message = filename + fileExtension + "\n\nhas been moved into\n\n " + pathBase;
            alert = INFORMATION;
        }else{
            message = "Error while moving file:\n " + filename + fileExtension + " \n\nor\n\n" + pathBase + " \n\ndoesn't exist";
            alert = ERROR;
        }
          
        
        Platform.runLater(() -> {
            Alert dialogBox = new Alert(alert, message , ButtonType.OK);
            Stage stage = (Stage) dialogBox.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("messageAlert.png")));
            dialogBox.setTitle(" FILE MOVING");
            dialogBox.show();
        });
    }

    /**
     * Returns a textual representation of the class.
     * @return A string representing the MoveFileAction object.
     */
    @Override
    public String toString() {
        return "MoveFileAction" + ":" + filename + fileExtension + " into " + pathBase + '\n';
    }
    
}