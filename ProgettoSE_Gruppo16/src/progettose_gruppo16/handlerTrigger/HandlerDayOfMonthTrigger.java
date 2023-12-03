/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.trigger.DayOfMonthTrigger;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import progettose_gruppo16.trigger.BaseHandlerTrigger;

/**
 *
 * @author amost
 */
public class HandlerDayOfMonthTrigger extends BaseHandlerTrigger{
    private ComboBox<String> dayOfMonthBox= new ComboBox();
    
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){
        if(s.equals("Day of month")){
            ap.getChildren().clear();
            ap.setId("DayOfMonthPane");
        
            ap.getChildren().add(dayOfMonthBox);
            dayOfMonthBox.setLayoutX(100);
            dayOfMonthBox.setLayoutY(0);
            initializeCBMonth();
            dayOfMonthBox.setValue("01");
        }
        else{
            dayOfMonthBox.setValue("01");
            super.handleGUI(ap, s, btn);
        }
    }
    
    @Override 
    public Trigger handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("DayOfMonthPane")){  
            
            return new DayOfMonthTrigger(Integer.parseInt(dayOfMonthBox.getValue()));           
        }else{
            return super.handleBehaviour(ap);
        }
    }
    
    
    private void initializeCBMonth(){
        //inizialization of the combo boxes for the time selection (TECHNICAL DEBT!)
        for (int i = 1; i <= 31; i++) {
            dayOfMonthBox.getItems().add(String.format("%02d", i));
        }
    }
    
}
