package progettose_gruppo16.trigger;

import java.time.LocalDate;

/**
 * Classe DayOfMonthTrigger implementa l'interfaccia Trigger per controllare il giorno del mese.
 */
public class DayOfMonthTrigger implements Trigger{
    
    private int dayOfMonth;

    /**
     * Costruttore che prende come paramentro un intero da 1 a 31.
     * @param dayOfMonth
     */
    public DayOfMonthTrigger(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    /**
     * Metodo che controlla se il giorno selezionato dall'utente corrisponde al giorno attuale del mese
     * @return true se la condizione è vera; false altrimenti
     */
    @Override
    public boolean checkCondition() {
        return this.dayOfMonth == LocalDate.now().getDayOfMonth();
    }

    @Override
    public String toString() {
        return "Day of month: " + String.format("%02d", dayOfMonth);
    }
}
