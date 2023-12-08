/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.trigger.DayOfWeekTrigger;
import java.time.DayOfWeek;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Classe handler che gestisce il trigger "DayOfWeekTrigger"
 * @author raffa
 */
public class HandlerDayOfWeekTrigger extends BaseHandlerTrigger{
    
    /**
     * Quando l'utente aggiunge la regola viene preso il valore del giorno della settimana selezionato
     * nel combo box e passato al costruttore del trigger
     * @param ap
     * @param ht
     * @param x
     * @param notVBox
     * @return
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox) {
        if(ap.getId().equalsIgnoreCase("DayOfWeekPane")){
            boolean not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
            return new DayOfWeekTrigger(DayOfWeek.valueOf(((ComboBox<String>) ap.getChildren().get(0)).getValue().toUpperCase()), not);
        }
        return super.handleBehaviour(ap, ht, x, notVBox);
    }

    /**
     * Quando si seleziona il trigger "Day of the week" viene creato dinamicamente l'elemento
     * che permette di selezionare il giorno della settimana (default: Monday)
     * @param ap
     * @param cb
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        if(cb.getValue().equalsIgnoreCase("Day of the week")){
            ap.getChildren().clear();
            ap.setId("DayOfWeekPane");
            
            ComboBox<String> daysCB = new ComboBox<>();
            initializeCB(daysCB);
            ap.getChildren().add(daysCB);
            daysCB.setLayoutX(100);
            daysCB.setLayoutY(7);
            daysCB.setValue("Monday");
        }
        else{
            super.handleGUI(ap, cb);
        }
    }
    
    // Imppsta gli elementi nel combo box per permettere di selezionare qualsiasi giorno della settimana
    private void initializeCB(ComboBox<String> daysCB){
        daysCB.getItems().add("Monday");
        daysCB.getItems().add("Tuesday");
        daysCB.getItems().add("Wednesday");
        daysCB.getItems().add("Thursday");
        daysCB.getItems().add("Friday");
        daysCB.getItems().add("Saturday");
        daysCB.getItems().add("Sunday");
    }
    
}
