/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

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

    //Override del metodo equals e hashcode per effettuare il confronto dei tempi considerando solo minuti e ore
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + Objects.hashCode(this.time);
        return hash;
    }
     
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeOfDayTrigger other = (TimeOfDayTrigger) obj;
        return this.time.getHour() == other.time.getHour() && this.time.getMinute() == other.time.getMinute();
    }
    
    /**
     * Implementazione metodo dell'interfaccia Trigger
     * @return --> restituisce true se i tempi coincidono; false altrimenti
     */
    @Override
    public boolean checkCondition() {
        TimeOfDayTrigger currentTimeTrigger = new TimeOfDayTrigger(LocalTime.now());
            return this.equals(currentTimeTrigger);
    }   

    @Override
    public String toString() {
        return "Time of day - " + time;
    }
    
    
}
