package progettose_gruppo16.handlerTrigger;

import progettose_gruppo16.trigger.Trigger;
import progettose_gruppo16.trigger.DayOfWeekTrigger;
import java.time.DayOfWeek;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

/**
 * Handler class for the "DayOfWeekTrigger" trigger.
 * Extends the BaseHandlerTrigger class.
 * Manages the behavior and GUI elements specific to DayOfWeekTrigger.
 */
public class HandlerDayOfWeekTrigger extends BaseHandlerTrigger {

    /**
     * When the user adds the rule, the selected day of the week value from the combo box
     * is taken and passed to the trigger's constructor.
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
        
        if (ap.getId().equalsIgnoreCase("DayOfWeekPane")) {
            not = ((CheckBox) notVBox.getChildren().get(x - 1)).isSelected();
            return new DayOfWeekTrigger(DayOfWeek.valueOf(((ComboBox<String>) ap.getChildren().get(0)).getValue().toUpperCase()), not);
        }
        return super.handleBehaviour(ap, ht, x, notVBox);
    }

    /**
     * When the "Day of the week" trigger is selected, dynamically creates the element
     * that allows the user to select the day of the week (default: Monday).
     *
     * @param ap The AnchorPane to handle.
     * @param cb The ComboBox of the selected trigger.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        if (cb.getValue().equalsIgnoreCase("Day of the week")) {
            ap.getChildren().clear();
            ap.setId("DayOfWeekPane");

            ComboBox<String> daysCB = new ComboBox<>();
            initializeCB(daysCB);
            ap.getChildren().add(daysCB);
            daysCB.setLayoutX(100);
            daysCB.setLayoutY(7);
            daysCB.setValue("Monday");
        } else {
            super.handleGUI(ap, cb);
        }
    }

    // Initializes the elements in the combo box to allow selecting any day of the week
    private void initializeCB(ComboBox<String> daysCB) {
        daysCB.getItems().add("Monday");
        daysCB.getItems().add("Tuesday");
        daysCB.getItems().add("Wednesday");
        daysCB.getItems().add("Thursday");
        daysCB.getItems().add("Friday");
        daysCB.getItems().add("Saturday");
        daysCB.getItems().add("Sunday");
    }
}
