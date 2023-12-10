package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.TimeOfDayTrigger;
import progettose_gruppo16.trigger.Trigger;
import java.time.LocalTime;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Handler class for the "TimeOfDayTrigger" trigger.
 * Manages the behavior and GUI for the TimeOfDayTrigger.
 */
public class HandlerTimeOfDayTrigger extends BaseHandlerTrigger{
    
    /**
     * When the "Time of day" trigger is selected, dynamically creates elements
     * to set the activation time of the trigger (default: 00:00).
     * @param ap AnchorPane to modify
     * @param cb ComboBox representing the trigger type
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        ComboBox<String> hoursCB;
        ComboBox<String> minutesCB;
        Label lbl;
        
        if(cb.getValue().equalsIgnoreCase("Time of day")){
            ap.getChildren().clear();
            ap.setId("TimeOfDayPane");
            
            hoursCB = new ComboBox<>();
            minutesCB = new ComboBox<>();
            lbl = new Label(":");
            
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
            super.handleGUI(ap, cb);        
        }
    }
    
    /**
     * When the user adds the rule, the values of hours and minutes are taken from their respective combo boxes
     * and passed to the trigger constructor.
     * @param ap AnchorPane containing the GUI elements
     * @param ht HandlerTrigger instance
     * @param x Integer parameter for trigger behavior
     * @param notVBox VBox containing "not" CheckBox elements
     * @return Trigger object representing the time of day trigger
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox) {
        boolean not;
        LocalTime time;
        
        if(ap.getId().equalsIgnoreCase("TimeOfDayPane")){   
            not = ((CheckBox) notVBox.getChildren().get(x-1)).isSelected();
            time = LocalTime.of(Integer.parseInt(((ComboBox<String>) ap.getChildren().get(0)).getValue()), Integer.parseInt(((ComboBox<String>) ap.getChildren().get(2)).getValue()));
            return new TimeOfDayTrigger(time, not);
        }
        else return super.handleBehaviour(ap, ht, x, notVBox);
    }
    
    /**
     * Initializes the items in the combo boxes to allow selecting any time of the day.
     * @param hoursCB ComboBox for selecting hours
     * @param minutesCB ComboBox for selecting minutes
     */
    private void initializeCBs(ComboBox<String> hoursCB, ComboBox<String> minutesCB){
        for (int i = 0; i <= 23; i++) {
            hoursCB.getItems().add(String.format("%02d", i));
        }
 
        for (int i = 0; i <= 59; i++) {
            minutesCB.getItems().add(String.format("%02d", i));
        }
    }
}
