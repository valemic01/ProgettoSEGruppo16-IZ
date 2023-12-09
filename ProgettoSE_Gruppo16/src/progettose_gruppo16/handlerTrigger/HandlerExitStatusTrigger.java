package progettose_gruppo16.handlerTrigger;


import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import progettose_gruppo16.trigger.ExitStatusTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 * Handler class for the "ExitStatusTrigger" trigger.
 * Extends the BaseHandlerTrigger class.
 * Manages the behavior and GUI elements specific to ExitStatusTrigger.
 */
public class HandlerExitStatusTrigger extends BaseHandlerTrigger {
    
    /**
     * Allows the user, when choosing ExitStatusTrigger, to select a program,
     * write a list of command line arguments, and enter the expected exit status.
     * @param ap AnchorPane to modify
     * @param cb ComboBox representing the trigger type
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){ 
        if(cb.getValue().equals("Exit status")){
            ap.getChildren().clear();
            ap.setId("ExitStatusPane");
            
            TextField textExitStatus = new TextField();
            Button selectFileBtn = new Button();
            Label labelSelectedFile = new Label();
            Label labelErrorExitStatus = new Label();
            TextField textArguments = new TextField();
            Label filePath = new Label();
            
            selectFileBtn.setText("Select file");
            ap.getChildren().add(selectFileBtn);
            selectFileBtn.setLayoutX(0);
            selectFileBtn.setLayoutY(7);
            
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(37);
            labelSelectedFile.setPrefWidth(344);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            textArguments.setPromptText("Arguments...");
            ap.getChildren().add(textArguments);
            textArguments.setPrefSize(180, 31);
            textArguments.setLayoutX(90);
            textArguments.setLayoutY(7);
            
            textExitStatus.setPromptText("Exit Status...");
            ap.getChildren().add(textExitStatus);
            textExitStatus.setPrefSize(100, 31);
            textExitStatus.setLayoutX(280);
            textExitStatus.setLayoutY(7);
            
            labelErrorExitStatus.setText("");
            ap.getChildren().add(labelErrorExitStatus);
            labelErrorExitStatus.setLayoutX(220);
            labelErrorExitStatus.setLayoutY(37);
            labelErrorExitStatus.setPrefWidth(344);
            labelErrorExitStatus.setTextFill(Color.RED);
            
            // invisible label to pass the file path string to the handleBehaviour method
            filePath.setText("");
            filePath.setVisible(false);
            ap.getChildren().add(filePath);
            
            selectFileBtn.setOnAction(event -> chooseFile(labelSelectedFile, filePath ));
            
        }else{
            super.handleGUI(ap, cb);
        }      
    }
    
    /**
     * Creates an ExitStatusTrigger object if the user has selected a file and entered command line arguments.
     * If the user does not enter an exit status, 0 is selected by default.
     * If the user does not enter a number, the application notifies them with a message and does not allow
     * the creation of the rule.
     * @param ap AnchorPane containing the GUI elements
     * @param ht HandlerTrigger instance
     * @param x An integer parameter for trigger behavior
     * @param notVBox VBox containing "not" CheckBox elements
     * @return Trigger object representing the exit status trigger
     */
    @Override 
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox){     
        if(ap.getId().equals("ExitStatusPane")){
            boolean not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
            String file= ((Label) ap.getChildren().get(5)).getText();
            if(file!=null){
                Label labelErrorExitStatus= ((Label) ap.getChildren().get(4));
                TextField textArguments= ((TextField) ap.getChildren().get(2));
                TextField textExitStatus= ((TextField) ap.getChildren().get(3));
                
                labelErrorExitStatus.setText("");
                if(textArguments.getText().isEmpty())
                    textArguments.setText("");
                if(textExitStatus.getText().isEmpty())
                    return new ExitStatusTrigger(file, textArguments.getText(), 0, not);
                if(!textExitStatus.getText().matches("\\d*")){
                    labelErrorExitStatus.setText("You must write a number!");
                    return null;
                }
                return new ExitStatusTrigger(file, textArguments.getText(), Integer.parseInt(textExitStatus.getText()), not);
            }else
                return null;
        }else{
            return super.handleBehaviour(ap, ht, x, notVBox);
        }
    }

    /**
     * Allows the user to select a file.
     * @param labelSelectedFile Label displaying the selected file
     * @param filePath Label to pass the file path.
     */
    private void chooseFile(Label labelSelectedFile, Label filePath){
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("Accepted extensions (*.exe, *.jar, *.bat, *.ps1)", "*.exe", "*.jar", "*.bat", "*.ps1");
        fileChooser.getExtensionFilters().add(filter);
        File file = fileChooser.showOpenDialog(labelSelectedFile.getScene().getWindow());
        if(file!=null){    
            filePath.setText(file.getAbsolutePath());
            labelSelectedFile.setText("Selected file: " + file.getName());
        }
        else
            labelSelectedFile.setText("File not selected");
    }
}
