package progettose_gruppo16.action;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Class that implements the action of executing an external program
 * with certain command line arguments.
 * @author amost
 */
public class ExecuteProgramAction implements Action{

    private final String path;
    private final String args;
    private final String filename;
    
    /**
     * Constructor of the class that initializes the path of the program to execute
     * and the command line arguments specified by the user.
     * @param path -> path of the program to execute
     * @param args -> string containing command line arguments separated by space
     */ 
    public ExecuteProgramAction(String path, String args) {
        this.path = path;
        this.args = args;
        this.filename = new File(path).getName();
    }
    
    /**
     * Method that implements the execution of the external program using the
     * ProcessBuilder class.
     */
    @Override
    public void executeAction() {        
        String message;
        Alert.AlertType alert;
        
        if(new File(path).exists()){
            try {            
                 List<String> list = new LinkedList<>();
                 if (path.substring(path.lastIndexOf('.')+1).equals("jar")){
                     list.add("java");
                     list.add("-jar");
                 }
                 if (path.substring(path.lastIndexOf('.')+1).equals("ps1")){
                list.add("powershell.exe");
                list.add("-File");
                }
                 //altrimenti file exe
                 list.add(path);
                 StringTokenizer st= new StringTokenizer(args);
                 while(st.hasMoreTokens()){
                     list.add(st.nextToken(" "));
                 }
                 ProcessBuilder pb= new ProcessBuilder(list); 
                 pb.start();
             } catch (IOException ex) {
                 ex.printStackTrace();
             }
            
            message = filename + " has been executed";
            alert = INFORMATION;
        }else{
            message = "Error while executing program: \n" + filename + "\n   doesn't exist";
            alert = ERROR;
        }
        
        Platform.runLater(() -> {
             Alert dialogBox = new Alert(alert, message , ButtonType.OK);
             Stage stage = (Stage) dialogBox.getDialogPane().getScene().getWindow();
             stage.getIcons().add(new Image(getClass().getResourceAsStream("messageAlert.png")));
             dialogBox.setTitle(" PROGRAM EXECUTING");
             dialogBox.show();
         });
    }
    
    /**
     * Returns a textual representation of the class.
     * @return A string representing the ExecuteProgramAction object. 
     */
    @Override
    public String toString() {     
        return "Program: " + filename + '\n';
    }  
    
}
