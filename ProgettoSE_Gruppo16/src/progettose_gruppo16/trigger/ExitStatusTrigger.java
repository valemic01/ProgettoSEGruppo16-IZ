package progettose_gruppo16.trigger;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Implements the Trigger interface for checking if the exit status matches the expected exit status
 * of an external program chosen by the user.
 * 
 * The class uses ProcessBuilder to execute an external program and captures its exit status.
 * The trigger can also be negated to check for inequality.
 * 
 * Supports execution of JAR, PS1, EXE, or BAT files.
 * 
 */
public class ExitStatusTrigger implements Trigger{
    
    private String path;
    private String args;
    private int exitStatus;
    private boolean not;

    /**
     * Constructs an ExitStatusTrigger with the specified program path, arguments, exit status, and negation status.
     * 
     * @param path The path of the program to be executed.
     * @param args The list of arguments to be passed to the program.
     * @param exitStatus The expected exit status.
     * @param not True if the trigger should check for non-matching exit status, false for matching exit status.
     */
    public ExitStatusTrigger(String path, String args, int exitStatus, boolean not){
        this.path = path;
        this.args = args;
        this.exitStatus = exitStatus;
        this.not = not;
    }

    /**
     * Executes the external program, captures its exit status, and checks if it matches the expected exit status,
     * considering negation status.
     * 
     * @return True if the exit status matches the expected exit status, false otherwise.
     */
    @Override
    public boolean checkCondition(){
        List<String> list;
        StringTokenizer st;
        ProcessBuilder pb;
        int exitStatusProcess;
        Process process;
        
        try {            
            list = new LinkedList<>();
            if (path.substring(path.lastIndexOf('.')+1).equals("jar")){  // JAR file
                list.add("java");
                list.add("-jar");
            }
            if (path.substring(path.lastIndexOf('.')+1).equals("ps1")){  // PowerShell script
                list.add("powershell.exe");
                list.add("-File");
            }
            // Otherwise, it's an EXE or BAT file
            list.add(path);
            st = new StringTokenizer(args);
            while(st.hasMoreTokens()){
                list.add(st.nextToken(" "));  // Add arguments to the command list
            }
             // Build and start the process
            pb = new ProcessBuilder(list); 
            process = pb.start();          
            exitStatusProcess= process.waitFor();   // Wait for the process to complete and get the exit status
            if (exitStatusProcess == exitStatus){
                return !not;
            } 
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return not;
    }

    /**
     * Returns a string representation of the ExitStatusTrigger, including negation status.
     * 
     * @return The string representation of the ExitStatusTrigger.
     */
    @Override
    public String toString(){     
        String filename = path.substring(path.lastIndexOf('\\')+1);
        if (not)
            return "(NOT (Program: " + filename + " ExitStatus: " + exitStatus + "))";
        return "Program: " + filename + " ExitStatus: " + exitStatus;
    }   
}