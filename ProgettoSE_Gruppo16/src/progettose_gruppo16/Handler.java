package progettose_gruppo16;

import javafx.scene.control.ComboBox;
import javafx.scene.layout.AnchorPane;

/**
 * Interface handler to apply the Chain of Responsibility pattern.
 */
public interface Handler {
    public void handleGUI(AnchorPane ap, ComboBox<String> cb);
}