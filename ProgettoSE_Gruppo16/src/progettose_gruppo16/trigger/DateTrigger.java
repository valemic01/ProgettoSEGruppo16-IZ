package progettose_gruppo16.trigger;

import java.time.LocalDate;

/**
 * Classe DateTrigger implementa l'interfaccia Trigger per il controllo della data.
 */
public class DateTrigger implements Trigger{

    private LocalDate date; 
    private boolean not;

    /**
     * Costruttore che prende come paramentro la data inserita dall'utente
     * @param date
     */
    public DateTrigger(LocalDate date, boolean not) {
        this.date = date;
        this.not = not;
    }
    
    /**
     * Metodo che controlla se la data selezionata dall'utente corrisponde alla data attuale
     * @return
     */
    @Override
    public boolean checkCondition() {
        if(not) return !date.equals(LocalDate.now());
        return date.equals(LocalDate.now());
    }

    @Override
    public String toString() {
        if(not) 
            return "(NOT Date: " + date + ")";
        return "Date: " + date;
    }
    
    
}
