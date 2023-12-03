package progettose_gruppo16.handlerTrigger;

import java.time.LocalDate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;

import progettose_gruppo16.trigger.DateTrigger;
import progettose_gruppo16.trigger.Trigger;


/**
 *
 * Classe che estende la classe BaseHandlerTrigger. 
 */
public class HandlerDateTrigger extends BaseHandlerTrigger{
    
    private DatePicker date= new DatePicker();
            
    /**
     * Permette all'utente di selezionare una data (non precedente a quella attuale) 
     * quando decide di utilizzare DateTrigger
     * @param ap
     * @param s
     * @param btn
     */
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
    
    /**
     * Se l'utente ha selezionato una data, allora viene creato un oggetto di tipo DateTrigger
     * @param ap
     * @return
     */
    @Override 
    public Trigger handleBehaviour(AnchorPane ap){     
        if(ap.getId().equals("DatePane")){     
            if (date.getValue()!=null)
                return new DateTrigger(date.getValue());   
            else
                return null;
        }else{
            return super.handleBehaviour(ap);
        }
    }
}
