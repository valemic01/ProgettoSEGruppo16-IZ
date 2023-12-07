package progettose_gruppo16.trigger;

import java.io.File;

/**
 * Classe che implementa l'interfaccia trigger per controllare se un file è presente in una specifica locazione.
 */
public class ExistingFileTrigger implements Trigger {
    
    private String path;
    private String nameFile;
    private boolean not;

    /**
     *
     * @param path  
     * @param nameFile
     */
    public ExistingFileTrigger(String path, String nameFile, boolean not) {
        this.path = path;
        this.nameFile = nameFile;
        this.not = not;
    }

    /**
     * Controlla se il file è presente nel path
     * @return true se la condizione è vera; false altrimenti
     */
    @Override
    public boolean checkCondition() {
        File file = new File (path, nameFile);
        if(not) return !file.exists();
        return file.exists();
        
    }
    
    @Override
    public String toString() {
        if (not) 
            return "(NOT Check file existence: " + nameFile +")";
        return "Check file existence: " + nameFile;
    }
    
}
