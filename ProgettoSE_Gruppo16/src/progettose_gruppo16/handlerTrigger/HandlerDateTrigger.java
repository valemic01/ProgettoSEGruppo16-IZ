package progettose_gruppo16.handlerTrigger;

import java.time.LocalDate;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.trigger.DateTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 * Handler class for Date triggers.
 * Extends the BaseHandlerTrigger class.
 */
public class HandlerDateTrigger extends BaseHandlerTrigger {

    /**
     * Allows the user to select a date (not before the current date) when choosing DateTrigger.
     *
     * @param ap The AnchorPane associated with the trigger.
     * @param cb The ComboBox of the selected trigger.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        DatePicker date;
         
        if (cb.getValue().equals("Date")) {
            ap.getChildren().clear();
            ap.setId("DatePane");
            date = new DatePicker();
            ap.getChildren().add(date);
            date.setLayoutX(40);
            date.setLayoutY(0);
            date.setValue(LocalDate.now());

            date.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
                // Check if the new date is before the current day
                if (newValue != null && newValue.isBefore(LocalDate.now())) {
                    date.setValue(null);
                }
            });
        } else {
            super.handleGUI(ap, cb);
        }
    }

    /**
     * If the user has selected a date, creates a DateTrigger object.
     *
     * @param ap      The AnchorPane associated with the trigger.
     * @param ht      The HandlerTrigger instance.
     * @param x       An integer parameter for trigger behavior.
     * @param notVBox The VBox associated with the trigger with all the "Not" check boxes.
     * @return        The Trigger instance resulting from the behavior handling.
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox) {
        boolean not;
        LocalDate date;
        
        if (ap.getId().equals("DatePane")) {
            not = ((CheckBox) notVBox.getChildren().get(x - 1)).isSelected();
            date = ((DatePicker) ap.getChildren().get(0)).getValue();
            return new DateTrigger(date, not);
        } else {
            return super.handleBehaviour(ap, ht, x, notVBox);
        }
    }
}
