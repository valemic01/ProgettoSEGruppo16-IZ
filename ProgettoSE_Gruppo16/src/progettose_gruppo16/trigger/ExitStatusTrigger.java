/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.trigger;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author amost
 */
public class ExitStatusTrigger implements Trigger {
    
    private String path;
    private String args;
    private int exitStatus;

    public ExitStatusTrigger(String path, String args, int exitStatus) {
        this.path = path;
        this.args = args;
        this.exitStatus = exitStatus;
    }

    @Override
    public boolean checkCondition() {
        try {            
            List<String> list= new LinkedList<>();
            if (path.substring(path.lastIndexOf('.')+1).equals("jar")){  //file jar
                list.add("java");
                list.add("-jar");
            }
            //altrimenti file exe
            list.add(path);
            StringTokenizer st= new StringTokenizer(args);
            while(st.hasMoreTokens()){
                list.add(st.nextToken(" "));
            }
            ProcessBuilder pb= new ProcessBuilder(list); 
            Process process= pb.start();          
            int exitStatusProcess= process.waitFor();  //è un metodo bloccante
            if (exitStatusProcess==exitStatus)
                return true;
        } catch (IOException | InterruptedException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    public String toString() {     
        String filename = path.substring(path.lastIndexOf('\\')+1);
        return "Program: " + filename + " ExitStatus: " + exitStatus;
    }   
}
