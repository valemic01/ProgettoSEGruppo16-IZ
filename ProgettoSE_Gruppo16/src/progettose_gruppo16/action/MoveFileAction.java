/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Classe che implementa l'azione di spostare un file in una directory specificata dall'utente.
 * @author valentina <your.name at your.org>
 */
public class MoveFileAction implements Action{
    private final String fileSourcePath;
    private String fileDestinationPath;

     /**
     * Costruttore della classe che inizializza i percorsi del file da spostare e del percorso di destinazione.
     *
     * @param fileSourcePath      Il percorso del file da spostare.
     * @param fileDestinationPath Il percorso di destinazione in cui spostare il file.
     */
    public MoveFileAction(String fileSourcePath, String fileDestinationPath) {
        this.fileSourcePath = fileSourcePath;
        this.fileDestinationPath = fileDestinationPath;
    }
    
    /**
     * Esegue l'azione di spostamento del file, gestendo eventuali collisioni di nomi.
     * Utilizza Files.move per eseguire lo spostamento del file.
     */
    @Override
    public void executeAction() {
        Integer counterCopy = 1;
        int fileExtensionIndex = fileSourcePath.lastIndexOf('.');
        String fileExtension = fileSourcePath.substring(fileExtensionIndex);
        String filename = "\\" + fileSourcePath.substring(fileSourcePath.lastIndexOf('\\')+1, fileExtensionIndex);
        String pathBase = fileDestinationPath;
        fileDestinationPath = fileDestinationPath.concat(filename+fileExtension);
        
        while(new File(fileDestinationPath).exists()){
            fileDestinationPath = pathBase.concat(filename + '(' + counterCopy +')' +fileExtension);
            counterCopy ++;
        }
        
        try {
            Files.move(Paths.get(fileSourcePath), Paths.get(fileDestinationPath));
        } catch (IOException ex) {
            Logger.getLogger(CopyFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Restituisce una rappresentazione testuale della classe.
     * @return Una stringa che rappresenta l'oggetto MoveFileAction.
     */
    @Override
    public String toString() {
        return "MoveFileAction" + "-" + fileSourcePath + "-" + fileDestinationPath;
    }
    
}
