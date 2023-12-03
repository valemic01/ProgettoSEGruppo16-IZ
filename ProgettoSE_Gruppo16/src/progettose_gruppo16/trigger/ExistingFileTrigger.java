package progettose_gruppo16.trigger;

import java.io.File;

/**
 * Classe che implementa l'interfaccia trigger per controllare se un file è presente in una specifica locazione.
 */
public class ExistingFileTrigger implements Trigger {
    
    private String path;
    private String nameFile;

    /**
     *
     * @param path  
     * @param nameFile
     */
    public ExistingFileTrigger(String path, String nameFile) {
        this.path = path;
        this.nameFile = nameFile;
    }

    /**
     * Controlla se il file è presente nel path
     * @return true se la condizione è vera; false altrimenti
     */
    @Override
    public boolean checkCondition() {
        File file = new File (path, nameFile);
        return file.exists();
        
    }
    
    @Override
    public String toString() {
        return "Check file: " + nameFile;
    }
    
}
