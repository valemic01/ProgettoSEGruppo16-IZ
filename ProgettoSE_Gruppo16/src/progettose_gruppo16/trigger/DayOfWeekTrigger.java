/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16.trigger;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author amost
 */
public class DayOfWeekTrigger implements Trigger {
    
    private DayOfWeek dayOfWeek;

    public DayOfWeekTrigger(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;      
    }
    
    

    @Override
    public boolean checkCondition() {
        return this.dayOfWeek == LocalDate.now().getDayOfWeek();
    }

    @Override
    public String toString() {
        return "Day of the week: " + dayOfWeek.toString().charAt(0)+dayOfWeek.toString().toLowerCase().substring(1);
    }
    
}
