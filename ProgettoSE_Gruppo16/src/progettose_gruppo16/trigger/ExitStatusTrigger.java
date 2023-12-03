
package progettose_gruppo16.trigger;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe che implementa l'interfaccia trigger per controllare se l'exitstatus atteso dall'utente coincide con quello del programma
 * esterno che ha scelto di eseguire.
 */
public class ExitStatusTrigger implements Trigger {
    
    private String path;
    private String args;
    private int exitStatus;

    /**
     *
     * @param path  -->path del programma da eseguire
     * @param args  --> lista di argomenti
     * @param exitStatus
     */
    public ExitStatusTrigger(String path, String args, int exitStatus) {
        this.path = path;
        this.args = args;
        this.exitStatus = exitStatus;
    }

    /**
     * Costruisce il processo tramite il path del programma e la lista di argomenti; dopodichè fa partire la sua esecuzione
     * e salva l'exit status.
     * Viene passato al ProcessBuilder una lista contenente il programma e gli argomenti passati a linea di comando.
     * Se il formato del programma è jar, la lista contiene anche "java -jar".
     * @return true se l'exit status del programma coincide con quello inserito dall'utente; false altrimenti
     */
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
