/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Objects;


/**
 * TimeOfDayTrigger rappresenta il trigger che confronta il tempo corrente con l'ora e i minuti passati dall'utente
 *
 */
public class TimeOfDayTrigger implements Trigger{
    private LocalTime time;

    /**
     * costruttore
     * @param time  --> Ora e minuti selezionati dall'utente
     */
    public TimeOfDayTrigger(LocalTime time) {
        this.time = time;
    }    
    
    /**
     * Implementazione metodo dell'interfaccia Trigger
     * @return --> restituisce true se i tempi coincidono; false altrimenti
     */
    @Override
    public boolean checkCondition() {
        return this.time.getHour() == LocalTime.now().getHour() && this.time.getMinute() == LocalTime.now().getMinute();
    }   

    @Override
    public String toString() {
        return "Time of day - " + time;
    }
    
    
}
