package progettose_gruppo16;


import java.io.File;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amost
 */
public class SizeFileTrigger implements Trigger {
    
    private File file;
    private long size;

    public SizeFileTrigger(File file, long size) {
        this.file = file;
        this.size = size;
    }

    @Override
    public boolean checkCondition() {
       return file.length()>=size;
    }
    
    
    
}
