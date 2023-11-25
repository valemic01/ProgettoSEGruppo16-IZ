/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

/**
 *
 * @author valentina <your.name at your.org>
 */
public class BackupFileService extends Service<ObservableList<Rule>>{
    private List<Rule> rulesList = new ArrayList();
    private String backupFile;

    public BackupFileService(ObservableList<Rule> rulesList, String backupFile) {
        this.rulesList.addAll(rulesList);
        this.backupFile = backupFile;
    }
    
    @Override
    protected Task<ObservableList<Rule>> createTask() {
        return new Task<ObservableList<Rule>>(){
            @Override
            protected ObservableList<Rule> call() throws Exception {
                ObjectOutputStream oos = null;                         
                System.out.println("regole da salvare " + rulesList);
                    try{
                    oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(backupFile)));
                    oos.writeObject(rulesList);
                    }finally{
                        oos.close();
                    }                    
                return (ObservableList<Rule>) rulesList;
            }
            
        };
    }  
}
