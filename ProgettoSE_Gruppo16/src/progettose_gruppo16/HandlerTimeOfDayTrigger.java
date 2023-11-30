/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.time.LocalTime;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author raffa
 */
public class HandlerTimeOfDayTrigger extends BaseHandlerTrigger{

    private ComboBox<String> hoursCB = new ComboBox<>();
    private ComboBox<String> minutesCB = new ComboBox<>();
    private Label lbl = new Label(":");
    
    @Override
    public Trigger handleBehaviour(AnchorPane ap) {
        if(ap.getId().equalsIgnoreCase("TimeOfDayPane")){
            LocalTime time = LocalTime.of(Integer.parseInt(hoursCB.getValue()), Integer.parseInt(minutesCB.getValue()));
            return new TimeOfDayTrigger(time);
        }
        else return super.handleBehaviour(ap);
    }

    @Override
    public void handleGUI(AnchorPane ap, String s) {
        
        if(s.equalsIgnoreCase("Time of day")){
            ap.getChildren().clear();
            ap.setId("TimeOfDayPane");
            
            initializeCBs();
            hoursCB.setValue("00");
            minutesCB.setValue("00");

            ap.getChildren().add(hoursCB);
            hoursCB.setLayoutX(97);
            minutesCB.setLayoutY(4);
            
            ap.getChildren().add(lbl);
            lbl.setLayoutX(184);
            lbl.setLayoutY(-8);
            
            ap.getChildren().add(minutesCB);
            minutesCB.setLayoutX(194);
            minutesCB.setLayoutY(5);
            
        }
        else{
            super.handleGUI(ap, s);
            hoursCB.setValue("00");
            minutesCB.setValue("00");
        }
    }
    
    private void initializeCBs(){
        for (int i = 0; i <= 23; i++) {
            if(i<10)
                hoursCB.getItems().add("0"+String.valueOf(i));
            else
                hoursCB.getItems().add(String.valueOf(i));
        }
 
        for (int i = 0; i <= 59; i++) {
            if(i<10)
                minutesCB.getItems().add("0"+String.valueOf(i));
            else
                minutesCB.getItems().add(String.valueOf(i));
        }
    }
}
