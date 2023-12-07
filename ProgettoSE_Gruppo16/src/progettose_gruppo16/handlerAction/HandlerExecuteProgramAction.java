
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
 * Classe che estende la classe BaseHandlerAction.
 */
public class HandlerExecuteProgramAction extends BaseHandlerAction{
    
    private Label labelFile = new Label();
    private Button selectFileBtn = new Button();
    private Label labelSelectedFile = new Label();
    private Label labelArguments = new Label();
    private TextField textArguments = new TextField();
    private String file;
    
    /**
     * Permette all'utente di selezionare un programma e scrivere una lista di argomenti da passare a linea
     * di comando quando decide di utlizzare ExecuteProgramAction.
     * @param ap
     * @param s
     * @param btn
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb, Button btn){ 
        if(cb.getValue().equals("Execute program")){
            ap.getChildren().clear();
            ap.setId("ExecuteProgramPane");
            
            labelFile.setText("Select a file");
            ap.getChildren().add(labelFile);
            labelFile.setLayoutX(-5);
            labelFile.setLayoutY(30);
            labelFile.setPrefWidth(400);
            labelFile.setAlignment(Pos.CENTER);
            
            selectFileBtn.setText("Select file");
            ap.getChildren().add(selectFileBtn);
            selectFileBtn.setLayoutX(152);
            selectFileBtn.setLayoutY(57);
            
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(95);
            labelSelectedFile.setPrefWidth(400);
            labelSelectedFile.setAlignment(Pos.CENTER);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            labelArguments.setText("Command Line Arguments ");
            ap.getChildren().add(labelArguments);
            labelArguments.setLayoutX(0);
            labelArguments.setLayoutY(132);
            labelArguments.setPrefWidth(400);
            labelArguments.setAlignment(Pos.CENTER);
            
            textArguments.setPromptText("Arguments...");
            ap.getChildren().add(textArguments);
            textArguments.setLayoutX(106);
            textArguments.setLayoutY(153);
            
            
            selectFileBtn.setOnAction(event -> file = chooseFile(labelSelectedFile));
            
        }else{
            super.handleGUI(ap, cb, btn);
        }      
    }
    
    /**
     * Se l'utente ha selezionato un file e scritto eventuali argomenti
     * a linea di comando, allora viene creato un oggetto di tipo ExecuteProgramAction
     * @param ap
     * @return
     */
    @Override 
    public Action handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("ExecuteProgramPane")){
            if(file!=null){
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
        FileChooser.ExtensionFilter filter= new FileChooser.ExtensionFilter ("Accepted extensions (*.exe), (*.jar)", "*.exe", "*.jar");
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
