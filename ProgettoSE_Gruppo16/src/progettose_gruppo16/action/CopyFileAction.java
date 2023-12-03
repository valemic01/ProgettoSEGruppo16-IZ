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
 *Classe progettata per eseguire l'azione che consente di copiare un file in 
 * una directory specificata dall'utente.
 * @author valentina <your.name at your.org>
 */
public class CopyFileAction implements Action{
    private final String fileToCopyPath;
    private String fileDestinationPath;
    
    /**
     * Costruttore della classe che inizializza i percorsi del file da copiare e del percorso di destinazione.
     *
     * @param fileToCopyPath      Il percorso del file da copiare.
     * @param fileDestinationPath Il percorso di destinazione in cui copiare il file.
     */
    public CopyFileAction(String fileToCopyPath, String fileDestinationPath)  {
        this.fileToCopyPath = fileToCopyPath;
        this.fileDestinationPath = fileDestinationPath;
    }    
    
    /**
     * Esegue l'azione di copia del file, gestendo eventuali collisioni di nomi.
     * Utilizza Files.copy per eseguire la copia del file.
     */
    @Override
    public void executeAction() {
        Integer counterCopy = 1;
        int fileExtensionIndex = fileToCopyPath.lastIndexOf('.');
        String fileExtension = fileToCopyPath.substring(fileExtensionIndex);
        String filename = "\\"+fileToCopyPath.substring(fileToCopyPath.lastIndexOf('\\')+1, fileExtensionIndex);
        String pathBase = fileDestinationPath;
        fileDestinationPath = fileDestinationPath.concat(filename+fileExtension);
        
        while(new File(fileDestinationPath).exists()){
            fileDestinationPath = pathBase.concat(filename + '(' + counterCopy +')' +fileExtension);
            counterCopy ++;
        }
        
        try {
            Files.copy(Paths.get(fileToCopyPath), Paths.get(fileDestinationPath));
        } catch (IOException ex) {
            Logger.getLogger(CopyFileAction.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Restituisce una rappresentazione testuale della classe.
     * @return Una stringa che rappresenta l'oggetto CopyFileAction.
     */
    @Override
    public String toString() {
        return "CopyFileAction" + "-" + fileToCopyPath + "-" + fileDestinationPath;
    }
    
}
