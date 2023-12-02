/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class MoveFileAction implements Action{
    private final String fileSourcePath;
    private String fileDestinationPath;

    public MoveFileAction(String fileSourcePath, String fileDestinationPath) {
        this.fileSourcePath = fileSourcePath;
        this.fileDestinationPath = fileDestinationPath;
    }
    
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

    @Override
    public String toString() {
        return "MoveFileAction" + "-" + fileSourcePath + "-" + fileDestinationPath;
    }
    
}
