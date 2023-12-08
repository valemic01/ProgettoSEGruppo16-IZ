package progettose_gruppo16.trigger;

import java.io.File;

/**
 * Implements the Trigger interface for checking the existence of a file in a specific location.
 * This class allows users to check if a file is present at a specified path.
 * The trigger can also be negated to check for non-existence.
 * 
 */
public class ExistingFileTrigger implements Trigger{
    
    private String path;
    private String nameFile;
    private boolean not;

    /**
     * Constructs an ExistingFileTrigger with the specified file path, file name, and negation status.
     * 
     * @param path The path where the file is expected to be located.
     * @param nameFile The name of the file to be checked.
     * @param not True if the trigger should check for non-existence, false for existence.
     */
    public ExistingFileTrigger(String path, String nameFile, boolean not){
        this.path = path;
        this.nameFile = nameFile;
        this.not = not;
    }

    /**
     * Checks if the specified file is present at the given path, considering negation status.
     * 
     * @return True if the condition is satisfied, false otherwise.
     */
    @Override
    public boolean checkCondition() {
        File file = new File (path, nameFile);
        if(not) 
            return !file.exists();
        return file.exists();
    }
    
    
    /**
     * Returns a string representation of the ExistingFileTrigger, including negation status.
     * 
     * @return The string representation of the ExistingFileTrigger.
     */
    @Override
    public String toString(){
        if (not)
            return "(NOT Check file existence: " + nameFile + ")";
        return "Check file existence: " + nameFile;       
    }
}