/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.action;

import java.io.File;

/**
 *Classe che implementa l'azione che consiste di eliminare un file
 * specificato dall'utente.
 * @author valentina <your.name at your.org>
 */
public class DeleteFileAction implements Action{
    private final String filePath;

    /**
     * Costruttore della classe che inizializza il percorso del file da eliminare.
     * @param filePath Il percorso del file da eliminare.
     */
    public DeleteFileAction(String filePath) {
        this.filePath = filePath;
    }
    
    /**
     *Metodo che implementa l'eliminazione di un file.
     * Utilizza il metodo delete() della classe File per eliminare il file dal sistema.
     */
    @Override
    public void executeAction() {
        if(new File(filePath).delete())
            System.out.println("File deleted successfully.");
        else
            System.out.println("Error while deleting the file.");
    }
    
    /**
     * Restituisce una rappresentazione testuale della classe.
     * @return Una stringa che rappresenta l'oggetto DeleteFileAction.
     */
    @Override
    public String toString() {
        return "DeleteFileAction: " + filePath;
    }
    
    
}
