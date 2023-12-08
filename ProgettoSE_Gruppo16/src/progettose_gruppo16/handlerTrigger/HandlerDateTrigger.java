package progettose_gruppo16.handlerTrigger;

import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.trigger.DateTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 *
 * Classe che estende la classe BaseHandlerTrigger. 
 */
public class HandlerDateTrigger extends BaseHandlerTrigger{
    
            
    /**
     * Permette all'utente di selezionare una data (non precedente a quella attuale) 
     * quando decide di utilizzare DateTrigger
     * @param ap
     * @param cb
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb){
        if(cb.getValue().equals("Date")){
            ap.getChildren().clear();
            ap.setId("DatePane");
            DatePicker date= new DatePicker();
            ap.getChildren().add(date);
            date.setLayoutX(40);
            date.setLayoutY(0);
            date.setValue(LocalDate.now());
            
            
            date.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
                // Verifica se la nuova data è precedente al giorno corrente
                if (newValue != null && newValue.isBefore(LocalDate.now())) {
                    date.setValue(null);
                }
            });
        }
        else{
            super.handleGUI(ap, cb);
        }
    }
    
    /**
     * Se l'utente ha selezionato una data, allora viene creato un oggetto di tipo DateTrigger
     * @param ap
     * @param ht
     * @param x
     * @param notVBox
     * @return
     */
    @Override 
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox){     
        if(ap.getId().equals("DatePane")){  
            boolean not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
            LocalDate date= ((DatePicker) ap.getChildren().get(0)).getValue();
            return new DateTrigger(date, not);
        }else{
            return super.handleBehaviour(ap, ht, x, notVBox);
        }
    }
}
