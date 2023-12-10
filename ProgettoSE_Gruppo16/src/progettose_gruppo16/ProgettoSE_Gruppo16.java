package progettose_gruppo16;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * Main class that launches the application.
 */
public class ProgettoSE_Gruppo16 extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLRuleTable.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle(" IFTTT ");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("image.png")));
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}