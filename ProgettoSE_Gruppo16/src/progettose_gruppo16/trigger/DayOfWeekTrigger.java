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
    private boolean not;

    public DayOfWeekTrigger(DayOfWeek dayOfWeek, boolean not) {
        this.dayOfWeek = dayOfWeek;
        this.not = not;
    }
    
    

    @Override
    public boolean checkCondition() {
        if(not) return !(this.dayOfWeek == LocalDate.now().getDayOfWeek());
        return this.dayOfWeek == LocalDate.now().getDayOfWeek();
    }

    @Override
    public String toString() {
        if (not)
            return "(NOT Day of the week: (" + dayOfWeek.toString().charAt(0)+dayOfWeek.toString().toLowerCase().substring(1) +")";
        return "Day of the week: " + dayOfWeek.toString().charAt(0)+dayOfWeek.toString().toLowerCase().substring(1);
    }
    
}
