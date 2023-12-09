package progettose_gruppo16.handlerTrigger;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.trigger.DayOfMonthTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 * Handler class for Day of Month triggers.
 * Extends the BaseHandlerTrigger class.
 */
public class HandlerDayOfMonthTrigger extends BaseHandlerTrigger {

    /**
     * Allows the user to select a day of the month when choosing DayOfMonthTrigger.
     *
     * @param ap The AnchorPane associated with the trigger.
     * @param cb The ComboBox of the selected trigger.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        ComboBox<String> dayOfMonthBox = new ComboBox<>();
        if (cb.getValue().equals("Day of month")) {
            ap.getChildren().clear();
            ap.setId("DayOfMonthPane");

            ap.getChildren().add(dayOfMonthBox);
            dayOfMonthBox.setLayoutX(100);
            dayOfMonthBox.setLayoutY(7);
            initializeCBMonth(dayOfMonthBox);
            dayOfMonthBox.setValue("01");
        } else {
            dayOfMonthBox.setValue("01");
            super.handleGUI(ap, cb);
        }
    }

    /**
     * If the user has selected a day of the month, creates a DayOfMonthTrigger object.
     *
     * @param ap      The AnchorPane associated with the trigger.
     * @param ht      The HandlerTrigger instance.
     * @param x       An integer parameter for trigger behavior.
     * @param notVBox The VBox associated with the trigger with all the "Not" check boxes.
     * @return        The Trigger instance resulting from the behavior handling.
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox) {
        if (ap.getId().equals("DayOfMonthPane")) {
            boolean not = ((CheckBox) notVBox.getChildren().get(x - 1)).isSelected();
            return new DayOfMonthTrigger(Integer.parseInt(((ComboBox<String>) ap.getChildren().get(0)).getValue()), not);
        } else {
            return super.handleBehaviour(ap, ht, x, notVBox);
        }
    }

    /**
     * Initializes the ComboBox with options for days of the month.
     *
     * @param dayOfMonthBox The ComboBox to be initialized.
     */
    private void initializeCBMonth(ComboBox<String> dayOfMonthBox) {
        for (int i = 1; i <= 31; i++) {
            dayOfMonthBox.getItems().add(String.format("%02d", i));
        }
    }
}
