package progettose_gruppo16.handlerAction;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import progettose_gruppo16.action.Action;
import progettose_gruppo16.action.ExecuteProgramAction;

/**
 * Class that manages the GUI and functionalities necessary for
 * executing an external program. It extends the BaseHandlerAction class.
 */
public class HandlerExecuteProgramAction extends BaseHandlerAction{
    
    private Button selectFileBtn = new Button();
    private Label labelSelectedFile = new Label();
    private TextField textArguments = new TextField();
    private String file;
    
    /**
     * Allows the user to select a program and write a list of command-line arguments
     * when deciding to use ExecuteProgramAction.
     * @param ap The AnchorPane where GUI components are positioned.
     * @param cb Combo Box containing the action selected by the user.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){ 
        if(cb.getValue().equals("Execute program")){
            ap.getChildren().clear();
            ap.setId("ExecuteProgramPane");
            
            selectFileBtn.setText("Select Program to Execute");
            ap.getChildren().add(selectFileBtn);
            selectFileBtn.setLayoutX(0);
            selectFileBtn.setLayoutY(5);
            
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(40);
            labelSelectedFile.setPrefWidth(400);
            labelSelectedFile.setAlignment(Pos.CENTER);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            textArguments.setPromptText("Command Line Arguments...");
            ap.getChildren().add(textArguments);
            textArguments.setLayoutX(200);
            textArguments.setLayoutY(5);
            textArguments.setPrefWidth(200);
            
            selectFileBtn.setOnAction(event -> file = chooseFile(labelSelectedFile));
            
        }else{
            super.handleGUI(ap, cb);
        }      
    }
    
    /**
     * If the user has selected a file and written any command-line arguments,
     * an object of type ExecuteProgramAction is created.
     * @param ap The AnchorPane representing the current state.
     * @return An instance of Action based on the current state.
     */
    @Override 
    public Action handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("ExecuteProgramPane")){
            if(file!=null && !file.isEmpty()){
                if(textArguments.getText().isEmpty())
                    textArguments.setText("");
                return new ExecuteProgramAction(file, textArguments.getText());
            }else
                return null;
        }else{
            return super.handleBehaviour(ap);
        }
    } 
    
    @Override
    public String chooseFile(Label lbl){
        FileChooser fileChooser = new FileChooser();
        String path;
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("Accepted extensions (*.exe, *.jar, *.bat, *.ps1)", "*.exe", "*.jar", "*.bat", "*.ps1");
        fileChooser.getExtensionFilters().add(filter);
        File filePath = fileChooser.showOpenDialog(selectFileBtn.getScene().getWindow());
        if(filePath!=null){           
            lbl.setText("Selected file: " + filePath.getName());
            return filePath.getAbsolutePath();
        }
        lbl.textProperty().set("File not selected");
        return null;
    }
}
