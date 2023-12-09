package progettose_gruppo16.trigger;

import java.io.File;

/**
 * Implements the Trigger interface for checking the size of a file.
 * This class allows users to check if the size of a specified file meets certain criteria.
 * The trigger can also be negated to check for inequality.
 * 
 * Supports specifying the size in bytes, kilobytes, megabytes, or gigabytes.
 */
public class FileSizeTrigger implements Trigger{
    
    private File file;
    private long size;
    private int exp;
    private boolean not;

    /**
     * Constructs a FileSizeTrigger with the specified file, size, exponent, and negation status.
     * 
     * @param file The file whose size will be checked.
     * @param size The size criteria.
     * @param exp The exponent for size units (0 for bytes, 1 for kilobytes, 2 for megabytes, 3 for gigabytes).
     * @param not True if the trigger should check for non-matching size, false for matching size.
     */
    public FileSizeTrigger(File file, long size, int exp, boolean not){
        this.file = file;
        this.size = size;
        this.exp = exp;
        this.not = not;
    }

    /**
     * Checks if the size of the specified file meets the specified criteria, considering negation status.
     * 
     * @return True if the condition is satisfied, false otherwise.
     */
    @Override
    public boolean checkCondition(){
        long s = size;
        if(exp != 0){
           s = (long) (size*(Math.pow(1024, exp)));
        }
        if(not) 
            return !(file.length()>=s);
        return file.length()>=s;
    }

    /**
     * Returns a string representation of the FileSizeTrigger, including negation status and size units.
     * 
     * @return The string representation of the FileSizeTrigger.
     */
    @Override
    public String toString() {
        if (not)
            return "File size: " + file.getName() + " < " + size + ((exp == 0) ? "B" : (exp == 1) ? "KB" : (exp == 2) ? "MB" : "GB");
        return "File size: " + file.getName() + " >= " + size + ((exp == 0) ? "B" : (exp == 1) ? "KB" : (exp == 2) ? "MB" : "GB");
    }   
}