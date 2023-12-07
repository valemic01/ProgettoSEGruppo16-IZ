/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerAction;

import progettose_gruppo16.action.Action;
import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 *
 * @author raffa
 */
public class BaseHandlerAction implements HandlerAction{
    
    private HandlerAction next;
    
    @Override
    public void setNext(HandlerAction h) {
        next = h;
    }

    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb, Button btn) {
        if(next != null) next.handleGUI(ap, cb, btn);
    }

    @Override
    public Action handleBehaviour(AnchorPane ap) {
        if(next != null) return next.handleBehaviour(ap);
        return null;
    }
    
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
