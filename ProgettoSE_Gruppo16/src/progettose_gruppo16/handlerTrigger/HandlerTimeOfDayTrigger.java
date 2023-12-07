/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.TimeOfDayTrigger;
import progettose_gruppo16.trigger.Trigger;
import java.time.LocalTime;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Classe handler che gestisce il trigger "TimeOfDayTrigger"
 * @author raffa
 */
public class HandlerTimeOfDayTrigger extends BaseHandlerTrigger{
    
    /**
     * Quando l'utente aggiunge la regola vengono presi i valori di ora e minuti dai rispettivi combo box
     * e passati al costruttore del trigger
     * @param ap
     * @return
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox) {
        if(ap.getId().equalsIgnoreCase("TimeOfDayPane")){
    
            boolean not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
            LocalTime time = LocalTime.of(Integer.parseInt(((ComboBox<String>) ap.getChildren().get(0)).getValue()), Integer.parseInt(((ComboBox<String>) ap.getChildren().get(2)).getValue()));
            return new TimeOfDayTrigger(time, not);
        }
        else return super.handleBehaviour(ap, ht, x, notVBox);
    }

    /**
     * Quando si seleziona il trigger "Time of day" vengono creati dinamicamente gli elementi
     * che permettono di impostare l'orario di attivazione del trigger (default: 00:00)
     * @param ap
     * @param s
     * @param btn
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb, Button btn) {
        
        if(cb.getValue().equalsIgnoreCase("Time of day")){
            ap.getChildren().clear();
            ap.setId("TimeOfDayPane");
            
            ComboBox<String> hoursCB = new ComboBox<>();
            ComboBox<String> minutesCB = new ComboBox<>();
            Label lbl = new Label(":");
            
            initializeCBs(hoursCB, minutesCB);
            hoursCB.setValue("00");
            minutesCB.setValue("00");

            ap.getChildren().add(hoursCB);
            hoursCB.setLayoutX(50);
            hoursCB.setLayoutY(7);
            hoursCB.setPrefSize(76, 31);
            
            ap.getChildren().add(lbl);
            lbl.setLayoutX(136);
            lbl.setLayoutY(10);
            
            ap.getChildren().add(minutesCB);
            minutesCB.setLayoutX(152);
            minutesCB.setLayoutY(7);
            minutesCB.setPrefSize(76, 31);
            
        }
        else{
            super.handleGUI(ap, cb, btn);        
        }
    }
    
    // Imposta gli elementi nelle combo box per permettere di selezionare qualsiasi orario del giorno
    private void initializeCBs(ComboBox<String> hoursCB, ComboBox<String> minutesCB){
        for (int i = 0; i <= 23; i++) {
            hoursCB.getItems().add(String.format("%02d", i));
        }
 
        for (int i = 0; i <= 59; i++) {
            minutesCB.getItems().add(String.format("%02d", i));
        }
    }
}
