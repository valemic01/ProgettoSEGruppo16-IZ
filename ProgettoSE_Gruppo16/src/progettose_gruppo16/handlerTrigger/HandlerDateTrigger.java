/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.trigger.DateTrigger;
import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author amost
 */
public class HandlerDateTrigger extends BaseHandlerTrigger{
    
    private DatePicker date= new DatePicker();
            
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){
        if(s.equals("Date")){
            ap.getChildren().clear();
            ap.setId("DatePane");
        
            ap.getChildren().add(date);
            date.setLayoutX(40);
            date.setLayoutY(0);
            
            
            date.valueProperty().addListener(new ChangeListener<LocalDate>() {
                @Override
                public void changed(ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) {
                    // Verifica se la nuova data è precedente al giorno corrente
                    if (newValue != null && newValue.isBefore(LocalDate.now())) {
                        date.setValue(null);
                    }
                }
            });
        }
        else{
            super.handleGUI(ap, s, btn);
        }
    }
    
    @Override 
    public Trigger handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("DatePane")){              
            return new DateTrigger(date.getValue());           
        }else{
            return super.handleBehaviour(ap);
        }
    }
}
