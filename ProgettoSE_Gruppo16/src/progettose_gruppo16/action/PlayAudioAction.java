package progettose_gruppo16.action;

import java.io.File;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

/**
 * Class that implements the Action interface to allow the user to choose playing audio
 * as an action when creating a rule.
 * @author valentina
 */
public class PlayAudioAction implements Action{
    private final String filePath;
    private transient Media audioFile;
    private transient MediaPlayer player;
    private File file;
    
    /**
     * Constructor of the class that initializes the path of the audio file to play.
     * @param fileAudio The path of the audio file to play.
     */
    public PlayAudioAction(String fileAudio) {
        this.filePath = fileAudio;
        this.file = new File(filePath);
    }
    
    /**
     * Executes the audio playback action using JavaFX Media and MediaPlayer.
     * Creates a Media object and a MediaPlayer object associated with the specified audio file,
     * then starts playing the audio file.
     */
    @Override
    public void executeAction() {  
        
        if(file.exists()){            
            synchronized(file){               
                audioFile = new Media(file.toURI().toString());
                player = new MediaPlayer(audioFile);              
                player.play();  
            }
        }else{
            Platform.runLater(() -> {
                Alert dialogBox = new Alert(Alert.AlertType.ERROR, file.getName() + " doesn't exist" , ButtonType.OK);
                Stage stage = (Stage) dialogBox.getDialogPane().getScene().getWindow();
                stage.getIcons().add(new Image(getClass().getResourceAsStream("messageAlert.png")));
                dialogBox.setTitle(" PLAY AUDIO");
                dialogBox.show();
            });
        }
    }

    /**
     * Returns a textual representation of the class.
     * @return A string representing the PlayAudioAction object.
     */
    @Override
    public String toString() {
        return "PlayAudioAction: " + file.getName() + '\n';
    }
    
}