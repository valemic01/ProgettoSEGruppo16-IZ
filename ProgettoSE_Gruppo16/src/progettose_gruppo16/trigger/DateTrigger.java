package progettose_gruppo16.trigger;

import java.time.LocalDate;

/**
 * Classe DateTrigger implementa l'interfaccia Trigger per il controllo della data.
 */
public class DateTrigger implements Trigger{

    private LocalDate date; 

    /**
     * Costruttore che prende come paramentro la data inserita dall'utente
     * @param date
     */
    public DateTrigger(LocalDate date) {
        this.date = date;
    }
    
    /**
     * Metodo che controlla se la data selezionata dall'utente corrisponde alla data attuale
     * @return
     */
    @Override
    public boolean checkCondition() {
        return date.equals(LocalDate.now());
    }

    @Override
    public String toString() {
        return "Date: " + date;
    }
    
    
}
