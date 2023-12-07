package progettose_gruppo16.trigger;


import java.io.File;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author amost
 */
public class FileSizeTrigger implements Trigger {
    
    private File file;
    private long size;
    private int exp;
    private boolean not;

    public FileSizeTrigger(File file, long size, int exp, boolean not) {
        this.file = file;
        this.size = size;
        this.exp = exp;
        this.not = not;
    }

    @Override
    public boolean checkCondition() {
        long s = size;
        if(exp != 0){
           s = (long) (size*(Math.pow(1024, exp)));
        }
        if(not) return !(file.length()>=s);
        return file.length()>=s;
    }

    @Override
    public String toString() {
        if (not)
            return "File size: " + file.getName() + " < " + size + ((exp == 0) ? "B" : (exp == 1) ? "KB" : (exp == 2) ? "MB" : "GB");
        return "File size: " + file.getName() + " >= " + size + ((exp == 0) ? "B" : (exp == 1) ? "KB" : (exp == 2) ? "MB" : "GB");
    }
    
}
