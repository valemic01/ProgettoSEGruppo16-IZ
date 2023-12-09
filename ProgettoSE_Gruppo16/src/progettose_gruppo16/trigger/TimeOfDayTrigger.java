package progettose_gruppo16.trigger;

import java.time.LocalTime;

/**
 * Represents a trigger based on the time of day, comparing the current time with the user-selected hour and minute.
 * 
 * The trigger can also be negated to check for non-matching times.
 * 
 */
public class TimeOfDayTrigger implements Trigger{
    private LocalTime time;
    private boolean not;

    /**
     * Constructs a TimeOfDayTrigger with the specified hour and minute, and negation status.
     * 
     * @param time The time selected by the user.
     * @param not True if the trigger should check for non-matching times, false for matching times.
     */
    public TimeOfDayTrigger(LocalTime time, boolean not){
        this.time = time;
        this.not = not;
    }    
    
    /**
     * Checks if the current time matches the specified hour and minute, considering negation status.
     * 
     * @return True if the condition is satisfied, false otherwise.
     */
    @Override
    public boolean checkCondition(){
        if (not)
            return !(this.time.getHour() == LocalTime.now().getHour() && this.time.getMinute() == LocalTime.now().getMinute());
        return this.time.getHour() == LocalTime.now().getHour() && this.time.getMinute() == LocalTime.now().getMinute();
    }   

    /**
     * Returns a string representation of the TimeOfDayTrigger, including negation status.
     * 
     * @return The string representation of the TimeOfDayTrigger.
     */
    @Override
    public String toString() {
        if (not)
            return "(NOT Time of day - " + time +")";
        return "Time of day - " + time;
    }
}