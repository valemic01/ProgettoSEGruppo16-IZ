/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.time.LocalTime;
import java.util.Objects;


/**
 * 
 */
public class TimeOfDayTrigger implements Trigger{
    private LocalTime time;

    
    public TimeOfDayTrigger(LocalTime time) {
        this.time = time;
    }    

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