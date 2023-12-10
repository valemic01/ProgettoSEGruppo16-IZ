package progettose_gruppo16.handlerTrigger;

import java.io.File;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.DirectoryChooser;
import progettose_gruppo16.trigger.ExistingFileTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 * Handler class for the "ExistingFileTrigger" trigger.
 * Extends the BaseHandlerTrigger class.
 * Manages the behavior and GUI elements specific to ExistingFileTrigger.
 */
public class HandlerExistingFileTrigger extends BaseHandlerTrigger {

    /**
     * Allows the user to enter the file name to check and select a folder when choosing the Existing file trigger.
     *
     * @param ap The AnchorPane to handle.
     * @param cb The ComboBox of the selected trigger.
     */
    @Override
    public void handleGUI(AnchorPane ap, ComboBox<String> cb) {
        TextField textFile;
        Button selectFolderBtn;
        Label labelSelectedFolder;
        Label filePath;
         
        if (cb.getValue().equals("Existing file")) {
            ap.getChildren().clear();
            ap.setId("ExistingFilePane");

            textFile = new TextField();
            selectFolderBtn = new Button();
            labelSelectedFolder = new Label();
            filePath = new Label();

            textFile.setPromptText("Your file...");
            ap.getChildren().add(textFile);
            textFile.setLayoutX(0);
            textFile.setLayoutY(7);

            selectFolderBtn.setText("Select folder");
            ap.getChildren().add(selectFolderBtn);
            selectFolderBtn.setLayoutX(220);
            selectFolderBtn.setLayoutY(7);

            labelSelectedFolder.setText("");
            ap.getChildren().add(labelSelectedFolder);
            labelSelectedFolder.setLayoutX(0);
            labelSelectedFolder.setLayoutY(37);
            labelSelectedFolder.setPrefWidth(344);
            labelSelectedFolder.setAlignment(Pos.CENTER);
            labelSelectedFolder.setTextFill(Color.web("#009999"));

            // invisible label to pass the file path string to the handleBehaviour method
            filePath.setText("");
            filePath.setVisible(false);
            ap.getChildren().add(filePath);

            selectFolderBtn.setOnAction(event -> chooseFolder(labelSelectedFolder, filePath));

        } else {
            super.handleGUI(ap, cb);
        }
    }

    /**
     * If the user has entered the name of a file and selected a folder,
     * creates an ExistingFileTrigger object.
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
        String folder;
        
        if (ap.getId().equals("ExistingFilePane")) {
            folder = ((Label) ap.getChildren().get(3)).getText();
            if (folder != null && !folder.isEmpty()) {
                not = ((CheckBox) notVBox.getChildren().get(x - 1)).isSelected();
                return new ExistingFileTrigger(folder, ((TextField) ap.getChildren().get(0)).getText(), not);
            } else return null;
        } else {
            return super.handleBehaviour(ap, ht, x, notVBox);
        }
    }

    /**
     * Opens a directory chooser dialog and sets the selected folder information in the GUI.
     *
     * @param labelSelectedFolder The label displaying the selected folder information.
     * @param filePath            The label to pass the file path.
     */
    private void chooseFolder(Label labelSelectedFolder, Label filePath) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File directory;

        directory = directoryChooser.showDialog(labelSelectedFolder.getScene().getWindow());
        if (directory != null && !directory.getAbsolutePath().isEmpty()) {
            filePath.setText(directory.getAbsolutePath());
            labelSelectedFolder.textProperty().set("Destination: " + directory.getName());
        } else {
            labelSelectedFolder.textProperty().set("Destination not selected");
        }
    }
}
