package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.Action;
import java.io.File;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * Base class for implementing the Chain of Responsibility pattern.
 * Manages the traversal of the chain by various handlers.
 * @author raffa
 */
public class BaseHandlerAction implements HandlerAction{
    
    private HandlerAction next;
    
    /**
     * Method to set the next handler in the chain.
     * @param h The next handler in the chain.
     */
    @Override
    public void setNext(HandlerAction h) {
        next = h;
    }

    /**
     * Method for handling the chain for creating graphical objects.
     * @param ap The anchor pane on which the handler should operate.
     * @param cb The combo box selected by the user.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        if(next != null) next.handleGUI(ap, cb);
    }

    /**
     * Method for handling the chain for retrieving user-input values
     * and creating the corresponding action.
     * @param ap The anchor pane on which the handler works.
     * @return The action specified by the user with its respective parameters.
     */
    @Override
    public Action handleBehaviour(AnchorPane ap) {
        if(next != null) return next.handleBehaviour(ap);
        return null;
    }
    
    /**
     * General method for choosing files.
     * @param lbl The label for displaying the selected file.
     * @return The path of the selected file or null.
     */
    public String chooseFile(Label lbl){
        FileChooser fileChooser = new FileChooser();
        File file;
        
        file = fileChooser.showOpenDialog(lbl.getScene().getWindow());
        if(file!=null){
            lbl.textProperty().set("Selected file: " + file.getName());
            return file.getAbsolutePath();
        }else{
            lbl.textProperty().set("File not selected");
            return null;
        }
    }
    
    /**
     * General method for choosing directories.
     * @param lbl The label for displaying the selected directory.
     * @return The path of the selected directory or null.
     */
    public String chooseDirectory(Label lbl){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory;
        
        directory = directoryChooser.showDialog(lbl.getScene().getWindow());
        if(directory!=null){
            lbl.textProperty().set("Destination: " + directory.getName());
            return directory.getAbsolutePath();
        }else{
            lbl.textProperty().set("Destination not selected");
            return null;
        }       
    }
    
}