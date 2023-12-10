package progettose_gruppo16.handlerAction;

import java.io.File;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

/**
* This class defines a chooseFile method than will be inherited by different classes.
*/

public class HandlerFileSelection extends BaseHandlerAction{
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
    
}
