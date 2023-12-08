package progettose_gruppo16.trigger;

import java.time.LocalDate;

/**
 * Implements the Trigger interface for date-based conditions.
 * This class allows users to check if a specified date matches the current date.
 * The trigger can also be negated to check for inequality.
 */
public class DateTrigger implements Trigger{

    private LocalDate date; 
    private boolean not;

    /**
     * Constructs a DateTrigger with the specified date and negation status.
     * 
     * @param date The date to be checked against the current date.
     * @param not True if the trigger should check for inequality, false for equality.
     */
    public DateTrigger(LocalDate date, boolean not){
        this.date = date;
        this.not = not;
    }
    
    /**
     * Checks if the specified date matches the current date, considering negation status.
     * 
     * @return True if the condition is satisfied, false otherwise.
     */
    @Override
    public boolean checkCondition() {
        if(not) return !date.equals(LocalDate.now());
            return date.equals(LocalDate.now());
    }

    /**
     * Returns a string representation of the DateTrigger, including negation status.
     * 
     * @return The string representation of the DateTrigger.
     */
    @Override
    public String toString() {
        if(not) 
            return "(NOT Date: " + date + ")";
        return "Date: " + date;
    }
}
