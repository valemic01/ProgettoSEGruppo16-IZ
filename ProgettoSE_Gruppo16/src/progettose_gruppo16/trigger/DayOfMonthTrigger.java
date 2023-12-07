package progettose_gruppo16.trigger;

import java.time.LocalDate;

/**
 * Classe DayOfMonthTrigger implementa l'interfaccia Trigger per controllare il giorno del mese.
 */
public class DayOfMonthTrigger implements Trigger{
    
    private int dayOfMonth;
    private boolean not;

    /**
     * Costruttore che prende come paramentro un intero da 1 a 31.
     * @param dayOfMonth
     */
    public DayOfMonthTrigger(int dayOfMonth, boolean not) {
        this.dayOfMonth = dayOfMonth;
        this.not = not;
    }

    /**
     * Metodo che controlla se il giorno selezionato dall'utente corrisponde al giorno attuale del mese
     * @return true se la condizione è vera; false altrimenti
     */
    @Override
    public boolean checkCondition() {
        if(not) return !(this.dayOfMonth == LocalDate.now().getDayOfMonth());
        return this.dayOfMonth == LocalDate.now().getDayOfMonth();
    }

    @Override
    public String toString() {
        if (not)
            return "(NOT Day of month: " + String.format("%02d", dayOfMonth) +")";
        return "Day of month: " + String.format("%02d", dayOfMonth);
    }
}
