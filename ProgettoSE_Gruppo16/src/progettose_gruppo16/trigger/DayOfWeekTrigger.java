package progettose_gruppo16.trigger;

import java.time.DayOfWeek;
import java.time.LocalDate;

/**
 * Implements the Trigger interface for checking the day of the week.
 * This class allows users to check if a specified day of the week matches the current day of the week.
 * The trigger can also be negated to check for inequality.
 */
public class DayOfWeekTrigger implements Trigger{
    
    private DayOfWeek dayOfWeek;
    private boolean not;

    /**
     * Constructs a DayOfWeekTrigger with the specified day of the week and negation status.
     * 
     * @param dayOfWeek The day of the week to be checked.
     * @param not True if the trigger should check for inequality, false for equality.
     */
    public DayOfWeekTrigger(DayOfWeek dayOfWeek, boolean not){
        this.dayOfWeek = dayOfWeek;
        this.not = not;
    }
    
    /**
     * Checks if the specified day of the week matches the current day of the week, considering negation status.
     * 
     * @return True if the condition is satisfied, false otherwise.
     */
    @Override
    public boolean checkCondition() {
        if(not) 
            return !(this.dayOfWeek == LocalDate.now().getDayOfWeek());
        return this.dayOfWeek == LocalDate.now().getDayOfWeek();
    }

    /**
     * Returns a string representation of the DayOfWeekTrigger, including negation status.
     * 
     * @return The string representation of the DayOfWeekTrigger.
     */
    @Override
    public String toString() {
        if (not)
            return "(NOT Day of the week: (" + dayOfWeek.toString().charAt(0)+dayOfWeek.toString().toLowerCase().substring(1) +")";
        return "Day of the week: " + dayOfWeek.toString().charAt(0)+dayOfWeek.toString().toLowerCase().substring(1);        
    }
}