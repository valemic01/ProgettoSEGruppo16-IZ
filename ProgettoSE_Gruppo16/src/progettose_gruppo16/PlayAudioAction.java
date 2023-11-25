/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.File;
import java.io.Serializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class PlayAudioAction implements Action{
    private String filePath;
    private Media audioFile;
    private MediaPlayer player;

    public PlayAudioAction(String fileAudio) {
        this.filePath = fileAudio;
    }
    
    @Override
    public void executeAction() {
            audioFile = new Media(new File(filePath).toURI().toString());
            player = new MediaPlayer(audioFile);
            player.play();
    }

    @Override
    public String toString() {
        return "PlayAudioAction: " + filePath;
    }
    
    
}
