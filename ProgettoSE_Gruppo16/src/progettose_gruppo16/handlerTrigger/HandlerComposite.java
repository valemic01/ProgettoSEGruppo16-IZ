package progettose_gruppo16.handlerTrigger;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import progettose_gruppo16.trigger.CompositeTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 * This class represents a handler for Composite triggers in the application.
 * It extends BaseHandlerTrigger and provides specific implementations for handling
 * GUI elements and behavior triggers related to Composite triggers.
 */
public class HandlerComposite extends BaseHandlerTrigger{

    /**
     * Handles the behavior triggers related to Composite triggers.
     *
     * @param ap        The AnchorPane associated with the trigger.
     * @param ht        The HandlerTrigger instance.
     * @param x         An integer parameter for trigger behavior.
     * @param notVBox   The VBox associated with the trigger with all the "Not" check boxes.
     * @return          The Trigger instance resulting from the behavior handling.
     */
    @Override
    public Trigger handleBehaviour(AnchorPane ap, HandlerTrigger ht, int x, VBox notVBox) {
        if (ap.getId().substring(0, ap.getId().length() - 1).equalsIgnoreCase("CompositePane")) {
            Trigger t1, t2;
            AnchorPane ap1 = new AnchorPane();
            AnchorPane ap2 = new AnchorPane();
            AnchorPane a = new AnchorPane();
            int n1 = 0, n2 = 0;
            String pane = ap.getId().substring(13);
            String op;
            boolean not;
            /* check wich anchor panes and indexes have to be passed as parameters to the
            *  handleBehaviour function of the triggers in this composite
            */
            switch (pane) {
                case "1":
                    ap1 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(1);
                    ap2 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(2);
                    a = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(7);
                    n1 = 2;
                    n2 = 3;
                    break;
                case "2":
                    ap1 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(3);
                    ap2 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(4);
                    a = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(8);
                    n1 = 4;
                    n2 = 5;
                    break;
                case "3":
                    ap1 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(5);
                    ap2 = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(6);
                    a = (AnchorPane) ap.getParent().getChildrenUnmodifiable().get(9);
                    n1 = 6;
                    n2 = 7;
                    break;
            }
            
            // get the logical operator
            op = ((ComboBox<String>) a.getChildren().get(2)).getValue();
            // get the value of the "Not" checkbox
            not = ((CheckBox) notVBox.getChildren().get(x - 1)).isSelected();
            // get the triggers componing this composite
            t1 = ht.handleBehaviour(ap1, ht, n1, notVBox);
            t2 = ht.handleBehaviour(ap2, ht, n2, notVBox);

            if (t1 == null || t2 == null)
                return null;
            else return new CompositeTrigger(t1, t2, not, op);
        } else {
            return super.handleBehaviour(ap, ht, x, notVBox);
        }
    }

    /**
     * Handles the GUI elements related to Composite triggers.
     *
     * @param ap The AnchorPane to handle.
     * @param cb The ComboBox of the selected trigger.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        if (cb.getValue().equalsIgnoreCase("Composite")) {
            ap.getChildren().clear();
            //set the anchor pane id using the same index of the combo box id
            ap.setId(cb.getId().replace("trigDD", "CompositePane"));
        } else {
            super.handleGUI(ap, cb);
        }
    }
}
