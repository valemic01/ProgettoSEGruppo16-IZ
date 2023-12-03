package progettose_gruppo16.handlerTrigger;

import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

import progettose_gruppo16.trigger.DayOfMonthTrigger;
import progettose_gruppo16.trigger.Trigger;


/**
 * Classe che estende la classe BaseHandlerTrigger. 
 */
public class HandlerDayOfMonthTrigger extends BaseHandlerTrigger{
    private ComboBox<String> dayOfMonthBox= new ComboBox();
    
    /**
     * Permette all'utente di selezionare un giorno del mese quando decide di utilizzare dayOfMonthTrigger.
     * @param ap
     * @param s
     * @param btn
     */
    @Override
    public void handleGUI(AnchorPane ap, String s, Button btn){
        if(s.equals("Day of month")){
            ap.getChildren().clear();
            ap.setId("DayOfMonthPane");
        
            ap.getChildren().add(dayOfMonthBox);
            dayOfMonthBox.setLayoutX(100);
            dayOfMonthBox.setLayoutY(7);
            initializeCBMonth();
            dayOfMonthBox.setValue("01");
        }
        else{
            dayOfMonthBox.setValue("01");
            super.handleGUI(ap, s, btn);
        }
    }
    
    /**
     * Se l'utente ha selezionato un giorno del mese, allora viene creato 
     * un oggetto di tipo DayOfMonthTrigger
     * @param ap
     * @return
     */
    @Override 
    public Trigger handleBehaviour(AnchorPane ap){
        if(ap.getId().equals("DayOfMonthPane")){  
            
            return new DayOfMonthTrigger(Integer.parseInt(dayOfMonthBox.getValue()));           
        }else{
            return super.handleBehaviour(ap);
        }
    }
    
    private void initializeCBMonth(){
        for (int i = 1; i <= 31; i++) {
            dayOfMonthBox.getItems().add(String.format("%02d", i));
        }
    }
    
}
