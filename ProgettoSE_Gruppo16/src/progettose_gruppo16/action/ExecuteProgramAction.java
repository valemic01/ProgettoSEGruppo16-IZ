/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.action;

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
public class ExecuteProgramAction implements Action{

    private String path;
    private String args;

    public ExecuteProgramAction(String path, String args) {
        this.path = path;
        this.args = args;
    }
    
    @Override
    public void executeAction() {
       int exit;
       try {            
            List<String> list= new LinkedList<>();
            if (path.substring(path.lastIndexOf('.')+1).equals("jar")){  //file jar
                list.add("java");
                list.add("-jar");
            }
            if (path.substring(path.lastIndexOf('.')+1).equals("ps1")){
                list.add("powershell.exe");
                list.add("-File");
            }
            //altrimenti file exe o bat
            list.add(path);
            StringTokenizer st= new StringTokenizer(args);
            while(st.hasMoreTokens()){
                list.add(st.nextToken(" "));
            }
            ProcessBuilder pb= new ProcessBuilder(list); 
            Process process= pb.start();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    @Override
    public String toString() {     
        String filename = path.substring(path.lastIndexOf('\\')+1);
        return "Program: " + filename;
    }   
}
