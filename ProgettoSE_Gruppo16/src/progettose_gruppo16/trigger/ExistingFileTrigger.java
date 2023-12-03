/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.trigger;

import java.io.File;

/**
 *
 * @author amost
 */
public class ExistingFileTrigger implements Trigger {
    
    private String path;
    private String nameFile;

    public ExistingFileTrigger(String path, String nameFile) {
        this.path = path;
        this.nameFile = nameFile;
    }

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
