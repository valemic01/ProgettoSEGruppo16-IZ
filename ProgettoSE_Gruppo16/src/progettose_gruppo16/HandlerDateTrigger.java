/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author amost
 */
public class HandlerDateTrigger extends BaseHandlerTrigger{
    
    private DatePicker date= new DatePicker();
            
    @Override
    public void handleGUI(AnchorPane ap, String s){
        if(s.equals("Date")){
            ap.getChildren().clear();
            ap.setId("DatePane");
        
            ap.getChildren().add(date);
            date.setLayoutX(40);
            date.setLayoutY(0);
        }
        else{
            super.handleGUI(ap, s);
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
