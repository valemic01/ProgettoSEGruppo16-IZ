/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.action;

import java.io.File;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**Classe che implementa l'interfaccia Action per permettere all'utente di scegliere come azione
 * la riproduzione di un audio scelto in fase di creazione della regola.
 *
 * @author valentina <your.name at your.org>
 */
public class PlayAudioAction implements Action{
    private final String filePath;
    private transient Media audioFile;
    private transient MediaPlayer player;
    
    /**
     * Costruttore della classe che inizializza il percorso del file audio da riprodurre.
     * @param fileAudio Il percorso del file audio da riprodurre.
     */
    public PlayAudioAction(String fileAudio) {
        this.filePath = fileAudio;
    }
    
    /**
     * Esegue l'azione di riproduzione audio utilizzando JavaFX Media e MediaPlayer.
     * Crea un oggetto Media e un oggetto MediaPlayer associato al file audio specificato,
     * quindi avvia la riproduzione del file audio.
     */
    @Override
    public void executeAction() {
            audioFile = new Media(new File(filePath).toURI().toString());
            player = new MediaPlayer(audioFile);
            player.play();
    }

    /**
     * Restituisce una rappresentazione testuale della classe.
     * @return Una stringa che rappresenta l'oggetto PlayAudioAction.
     */
    @Override
    public String toString() {
        return "PlayAudioAction: " + filePath;
    }
    
    
}
