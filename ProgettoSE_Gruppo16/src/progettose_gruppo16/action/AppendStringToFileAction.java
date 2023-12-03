/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.action;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *Classe progettata per eseguire l'azione che consente di aggiungere una 
 * stringa ad un file esistente.
 * @author valentina <your.name at your.org>
 */
public class AppendStringToFileAction implements Action {
    private final String filePath;
    private final String textToAppend;
    
    /**
     * Costruttore della classe che inizializza il percorso del file e la stringa da aggiungere.
     *
     * @param filePath     Il percorso del file a cui aggiungere la stringa.
     * @param textToAppend La stringa da aggiungere al file.
     */
    public AppendStringToFileAction(String filePath, String textToAppend) {
        this.filePath = filePath;
        this.textToAppend = textToAppend;
    }
    
     /**
     * Aggiunge un testo al file.
     * Utilizza PrintWriter e BufferedWriter per garantire una scrittura efficiente e gestire le eccezioni.
     */
    @Override
    public void executeAction() {
       PrintWriter pw = null;
       
        try {
            pw = new PrintWriter(new BufferedWriter(new FileWriter(filePath, true))); //true indica che il file deve essere sovrascritto
            pw.append(textToAppend + "\n");
        } catch (IOException ex) {
            Logger.getLogger(AppendStringToFileAction.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            pw.close();
        }
    }
    
    /**
     * Restituisce una rappresentazione testuale della classe.
     * @return Una stringa che rappresenta l'oggetto AppendStringToFileAction.
     */
    @Override
    public String toString() {
        return "AppendStringToFileAction: " + filePath + "-" + textToAppend;
    }
    
    
    
}
