package progettose_gruppo16.handlerTrigger;


import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import progettose_gruppo16.trigger.ExitStatusTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 * Classe che estende la classe BaseHandlerTrigger.
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
    
    /**
     * Permette all'utente, quando decide di utilizzare ExitStatusTrigger, di selezionare un programma,
     * scrivere una lista di argomenti da passare a linea di comando e inserire l'exit Status atteso.
     * @param ap
     * @param s
     * @param btn
     */
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){ 
        if(s.equals("Exit status")){
            ap.getChildren().clear();
            ap.setId("ExitStatusPane");
            
            labelFile.setText("Select a file");
            ap.getChildren().add(labelFile);
            labelFile.setLayoutX(-5);
            labelFile.setLayoutY(4);
            labelFile.setPrefWidth(344);
            labelFile.setAlignment(Pos.CENTER);
            
            selectFileBtn.setText("Select file");
            ap.getChildren().add(selectFileBtn);
            selectFileBtn.setLayoutX(125);
            selectFileBtn.setLayoutY(32);
            
            labelSelectedFile.setText("");
            ap.getChildren().add(labelSelectedFile);
            labelSelectedFile.setLayoutX(0);
            labelSelectedFile.setLayoutY(70);
            labelSelectedFile.setPrefWidth(344);
            labelSelectedFile.setAlignment(Pos.CENTER);
            labelSelectedFile.setTextFill(Color.web("#009999"));
            
            labelArguments.setText("Command Line Arguments ");
            ap.getChildren().add(labelArguments);
            labelArguments.setLayoutX(0);
            labelArguments.setLayoutY(102);
            labelArguments.setPrefWidth(344);
            labelArguments.setAlignment(Pos.CENTER);
            
            textArguments.setPromptText("Arguments...");
            ap.getChildren().add(textArguments);
            textArguments.setLayoutX(79);
            textArguments.setLayoutY(123);
            
            labelExitStatus.setText("Exit status: ");
            ap.getChildren().add(labelExitStatus);
            labelExitStatus.setPrefSize(114, 21);
            labelExitStatus.setLayoutX(30);
            labelExitStatus.setLayoutY(170);
            labelExitStatus.setAlignment(Pos.CENTER);
            
            textExitStatus.setPromptText("Exit Status...(default 0)");
            ap.getChildren().add(textExitStatus);
            textExitStatus.setPrefSize(168, 31);
            textExitStatus.setLayoutX(129);
            textExitStatus.setLayoutY(165);
            
            labelErrorExitStatus.setText("");
            ap.getChildren().add(labelErrorExitStatus);
            labelErrorExitStatus.setLayoutX(0);
            labelErrorExitStatus.setLayoutY(196);
            labelErrorExitStatus.setPrefWidth(344);
            labelErrorExitStatus.setTextFill(Color.RED);
            labelErrorExitStatus.setAlignment(Pos.CENTER);
            
            selectFileBtn.setOnAction(event -> file = chooseFile());
            
        }else{
            super.handleGUI(ap, s, btn);
        }      
    }
    
    /**
     * Se l'utente ha selezionato un file e scritto eventuali argomenti
     * a linea di comando, allora viene creato un oggetto di tipo ExitStatusTrigger. 
     * Nel caso in cui l'utente non dovesse scrivere l'exit Status, viene di default selezionato 0;
     * se l'utente non scrive un numero, l'applicazione lo avvisa con un messaggio e non gli permette
     * di creare la regola.
     * @param ap
     * @return
     */
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

    // permette di selezionare un file
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
        labelSelectedFile.setText("File not selected");
        return null;
    }
}
