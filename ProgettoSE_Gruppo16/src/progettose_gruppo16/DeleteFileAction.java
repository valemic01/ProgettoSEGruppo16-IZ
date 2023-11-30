/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.File;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class DeleteFileAction implements Action{
    private final String filePath;

    public DeleteFileAction(String filePath) {
        this.filePath = filePath;
    }
    
    @Override
    public void executeAction() {
        if(new File(filePath).delete())
            System.out.println("File deleted successfully.");
        else
            System.out.println("Error while deleting the file.");
    }
    
}
