/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.trigger.DayOfWeekTrigger;
import java.time.DayOfWeek;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 * Classe handler che gestisce il trigger "DayOfWeekTrigger"
 * @author raffa
 */
public class HandlerDayOfWeekTrigger extends BaseHandlerTrigger{

    ComboBox<String> daysCB = new ComboBox<>();
    
    /**
     * Quando l'utente aggiunge la regola viene preso il valore del giorno della settimana selezionato
     * nel combo box e passato al costruttore del trigger
     * @param ap
     * @return
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap) {
        if(ap.getId().equalsIgnoreCase("DayOfWeekPane")){
            return new DayOfWeekTrigger(DayOfWeek.valueOf(daysCB.getValue().toUpperCase()));
        }
        return super.handleBehaviour(ap);
    }

    /**
     * Quando si seleziona il trigger "Day of the week" viene creato dinamicamente l'elemento
     * che permette di selezionare il giorno della settimana (default: Monday)
     * @param ap
     * @param s
     * @param btn
     */
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn) {
        if(s.equalsIgnoreCase("Day of the week")){
            ap.getChildren().clear();
            ap.setId("DayOfWeekPane");
            
            initializeCB();
            ap.getChildren().add(daysCB);
            daysCB.setLayoutX(100);
            daysCB.setLayoutY(7);
            daysCB.setValue("Monday");
        }
        else{
            daysCB.setValue("Monday");
            super.handleGUI(ap, s, btn);
        }
    }
    
    // Imppsta gli elementi nel combo box per permettere di selezionare qualsiasi giorno della settimana
    private void initializeCB(){
        daysCB.getItems().add("Monday");
        daysCB.getItems().add("Tuesday");
        daysCB.getItems().add("Wednesday");
        daysCB.getItems().add("Thursday");
        daysCB.getItems().add("Friday");
        daysCB.getItems().add("Saturday");
        daysCB.getItems().add("Sunday");
    }
    
}
