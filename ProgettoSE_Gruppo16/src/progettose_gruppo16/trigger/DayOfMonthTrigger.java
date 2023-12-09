package progettose_gruppo16.trigger;

import java.time.LocalDate;

/**
 * Implements the Trigger interface for checking the day of the month.
 * This class allows users to check if a specified day of the month matches the current day of the month.
 * The trigger can also be negated to check for inequality.
 */
public class DayOfMonthTrigger implements Trigger{
    
    private int dayOfMonth;
    private boolean not;

    /**
     * Constructs a DayOfMonthTrigger with the specified day of the month and negation status.
     * 
     * @param dayOfMonth The day of the month to be checked.
     * @param not True if the trigger should check for inequality, false for equality.
     */
    public DayOfMonthTrigger(int dayOfMonth, boolean not){
        this.dayOfMonth = dayOfMonth;
        this.not = not;
    }

    /**
     * Checks if the specified day of the month matches the current day of the month, considering negation status.
     * 
     * @return True if the condition is satisfied, false otherwise.
     */
    @Override
    public boolean checkCondition() {
        if(not) 
            return !(this.dayOfMonth == LocalDate.now().getDayOfMonth());
        return this.dayOfMonth == LocalDate.now().getDayOfMonth();
    }

    /**
     * Returns a string representation of the DayOfMonthTrigger, including negation status.
     * 
     * @return The string representation of the DayOfMonthTrigger.
     */
    @Override
    public String toString(){
        if (not)
            return "(NOT Day of month: " + String.format("%02d", dayOfMonth) +")";
        return "Day of month: " + String.format("%02d", dayOfMonth);        
    }
}