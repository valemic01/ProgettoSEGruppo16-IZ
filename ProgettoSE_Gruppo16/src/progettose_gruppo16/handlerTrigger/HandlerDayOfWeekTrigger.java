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
import progettose_gruppo16.trigger.BaseHandlerTrigger;

/**
 *
 * @author raffa
 */
public class HandlerDayOfWeekTrigger extends BaseHandlerTrigger{

    ComboBox<String> daysCB = new ComboBox<>();
    
    @Override
    public Trigger handleBehaviour(AnchorPane ap) {
        if(ap.getId().equalsIgnoreCase("DayOfWeekPane")){
            return new DayOfWeekTrigger(DayOfWeek.valueOf(daysCB.getValue().toUpperCase()));
        }
        return super.handleBehaviour(ap);
    }

    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn) {
        if(s.equalsIgnoreCase("Day of the week")){
            ap.getChildren().clear();
            ap.setId("DayOfWeekPane");
            
            initializeCB();
            ap.getChildren().add(daysCB);
            daysCB.setLayoutX(100);
            daysCB.setLayoutY(0);
            daysCB.setValue("Monday");
        }
        else{
            daysCB.setValue("Monday");
            super.handleGUI(ap, s, btn);
        }
    }
    
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
