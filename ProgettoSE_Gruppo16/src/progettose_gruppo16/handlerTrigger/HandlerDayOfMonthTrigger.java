package progettose_gruppo16.handlerTrigger;

import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.trigger.DayOfMonthTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 * Classe che estende la classe BaseHandlerTrigger. 
 */
public class HandlerDayOfMonthTrigger extends BaseHandlerTrigger{
    
    /**
     * Permette all'utente di selezionare un giorno del mese quando decide di utilizzare dayOfMonthTrigger.
     * @param ap
     * @param cb
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){
        ComboBox<String> dayOfMonthBox= new ComboBox();
        if(cb.getValue().equals("Day of month")){
            ap.getChildren().clear();
            ap.setId("DayOfMonthPane");
 
            ap.getChildren().add(dayOfMonthBox);
            dayOfMonthBox.setLayoutX(100);
            dayOfMonthBox.setLayoutY(7);
            initializeCBMonth(dayOfMonthBox);
            dayOfMonthBox.setValue("01");
        }
        else{
            dayOfMonthBox.setValue("01");
            super.handleGUI(ap, cb);
        }
    }
    
    /**
     * Se l'utente ha selezionato un giorno del mese, allora viene creato 
     * un oggetto di tipo DayOfMonthTrigger
     * @param ap
     * @param ht
     * @param x
     * @param notVBox
     * @return
     */
    @Override 
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox){
        if(ap.getId().equals("DayOfMonthPane")){
            boolean not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
            return new DayOfMonthTrigger(Integer.parseInt(((ComboBox<String>) ap.getChildren().get(0)).getValue()), not);           
        }else{
            return super.handleBehaviour(ap, ht, x, notVBox);
        }
    }
    
    private void initializeCBMonth(ComboBox<String> dayOfMonthBox){
        for (int i = 1; i <= 31; i++) {
            dayOfMonthBox.getItems().add(String.format("%02d", i));
        }
    }
    
}
