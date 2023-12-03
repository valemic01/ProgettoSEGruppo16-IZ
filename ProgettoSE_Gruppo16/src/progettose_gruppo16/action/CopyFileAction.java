/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.action;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class CopyFileAction implements Action{
    private final String fileToCopyPath;
    private String fileDestinationPath;
    
    public CopyFileAction(String fileToCopyPath, String fileDestinationPath)  {
        this.fileToCopyPath = fileToCopyPath;
        this.fileDestinationPath = fileDestinationPath;
    }    
    
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

    @Override
    public String toString() {
        return "CopyFileAction" + "-" + fileToCopyPath + "-" + fileDestinationPath;
    }
    
}
