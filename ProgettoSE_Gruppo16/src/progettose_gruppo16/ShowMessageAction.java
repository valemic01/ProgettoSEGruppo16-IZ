package progettose_gruppo16;

import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *  Classe che implementa l'interfaccia Action che permette di mostrare un messaggio
 *  scritto dall'utente a schermo all'attivazione del trigger. Il messaggio deve essere
 *  esplicitamente chiuso dall'utente.
 */
public class ShowMessageAction implements Action{
    
    private final String message;

    /**
     *  Costruttore
     * @param message -> messaggio scritto dall'utente tramite GUI
     */
    public ShowMessageAction(String message) {
        this.message = message;
    }

    /**
     *  Metodo implementato dall'interfaccia Action. Crea un Alert contenente il messaggio dell'utente
     *  e il bottone per chiuderlo. Platform.runLater serve per mettere il metodo contenente l'alert
     *  in una coda di eventi sul JavaFX Application Thread in modo che venga schedulato ed eseguito, quando possibile.
     *  Questo è necessario poiché ogni interazione con l'interfaccia deve avvenire attraverso l'Application Thread.
     */
    @Override
    public void executeAction() {        
        Platform.runLater(() -> {
            Alert dialogBox = new Alert(AlertType.NONE, message, ButtonType.OK);
            dialogBox.show();
        });
    }
    
}