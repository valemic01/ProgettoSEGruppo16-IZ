/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class AppendStringToFileAction implements Action {
    private final String filePath;
    private final String textToAppend;
    
    public AppendStringToFileAction(String filePath, String textToAppend) {
        this.filePath = filePath;
        this.textToAppend = textToAppend;
    }
    
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

    @Override
    public String toString() {
        return "AppendStringToFileAction: " + filePath + "-" + textToAppend;
    }
    
    
    
}
