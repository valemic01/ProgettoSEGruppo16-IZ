package progettose_gruppo16;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 * Class that performs the saving of rules to a predefined file in the controller.
 */
public class BackupFileService extends Service<ObservableList<Rule>>{
    private List<Rule> rulesList = new ArrayList();
    private final String backupFile;

    /**
     * Constructor
     * @param rulesList list of rules currently present in the application
     * @param backupFile predefined backup file in the controller (not chosen by the user)
     */
    public BackupFileService(ObservableList<Rule> rulesList, String backupFile) {
        this.rulesList.addAll(rulesList);
        this.backupFile = backupFile;
    }
    
    /**
     * Creation of a task that writes the list of rules received from the controller to the file.
     * @return
     */
    @Override
    protected Task<ObservableList<Rule>> createTask() {
        return new Task<ObservableList<Rule>>(){
            @Override
            protected ObservableList<Rule> call() throws Exception {
                ObjectOutputStream oos = null;                         
                    try{
                    oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(backupFile)));
                    oos.writeObject(rulesList);
                    }finally{
                        oos.close();
                    }                    
                    System.out.println(rulesList);
                return (ObservableList<Rule>) rulesList;
            }
            
        };
    }  
}