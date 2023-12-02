package progettose_gruppo16;


import java.io.File;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amost
 */
public class HandlerExitStatusTrigger extends BaseHandlerTrigger {
    private Label labelFile = new Label();
    private TextField textExitStatus = new TextField();
    private Button selectFileBtn = new Button();
    private Label labelSelectedFile = new Label();
    private Label labelArguments = new Label();
    private Label labelExitStatus = new Label();
    private Label labelErrorExitStatus = new Label();
    private TextField textArguments = new TextField();
    private String file;
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Exit status")){
            ap.getChildren().clear();
            ap.setId("ExitStatusPane");
            
            labelFile.setText("Select a file");
            ap.getChildren().add(labelFile);
            labelFile.setLayoutX(75);
            labelFile.setLayoutY(5);
            
            selectFileBtn.setText("Select file");
            ap.getChildren().add(selectFileBtn);
            selectFileBtn.setLayoutX(100);
            selectFileBtn.setLayoutY(40);
            
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(75);
            labelSelectedFile.setLayoutY(75);
            
            labelArguments.setText("Command Line Arguments ");
            ap.getChildren().add(labelArguments);
            labelArguments.setLayoutX(75);
            labelArguments.setLayoutY(100);
            
            textArguments.setPromptText("Arguments...");
            ap.getChildren().add(textArguments);
            textArguments.setLayoutX(75);
            textArguments.setLayoutY(130);
            
            labelExitStatus.setText("Write the exit status: ");
            ap.getChildren().add(labelExitStatus);
            labelExitStatus.setLayoutX(75);
            labelExitStatus.setLayoutY(170);
            
            textExitStatus.setPromptText("Exit Status...(default 0)");
            ap.getChildren().add(textExitStatus);
            textExitStatus.setLayoutX(75);
            textExitStatus.setLayoutY(200);
         
            labelErrorExitStatus.setText("");
            ap.getChildren().add(labelErrorExitStatus);
            labelErrorExitStatus.setLayoutX(75);
            labelErrorExitStatus.setLayoutY(240);
            labelErrorExitStatus.setTextFill(Color.RED);
            
            selectFileBtn.setOnAction(event -> file = chooseFile());
            
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
    @Override 
    public Trigger handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("ExitStatusPane")){
            if(file!=null){
                labelErrorExitStatus.setText("");
                if(textArguments.getText().isEmpty())
                    textArguments.setText("");
                if(textExitStatus.getText().isEmpty())
                    return new ExitStatusTrigger(file, textArguments.getText(), 0);
                if(!textExitStatus.getText().matches("\\d*")){
                    labelErrorExitStatus.setText("You must write a number!");
                    return null;
                }
                return new ExitStatusTrigger(file, textArguments.getText(), Integer.parseInt(textExitStatus.getText()));
            }else
                return null;
        }else{
            return super.handleBehaviour(ap);
        }
    }

    
    private String chooseFile(){
        FileChooser fileChooser = new FileChooser();
        String path;
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("Accepted extensions (*.exe), (*.jar)", "*.exe", "*.jar");
        fileChooser.getExtensionFilters().add(filter);
        File filePath = fileChooser.showOpenDialog(selectFileBtn.getScene().getWindow());
        if(filePath!=null){           
            labelSelectedFile.setText("Selected file: " + filePath.getName());
            return filePath.getAbsolutePath();
        }
        labelSelectedFile.setText("");
        return null;
    }
}
