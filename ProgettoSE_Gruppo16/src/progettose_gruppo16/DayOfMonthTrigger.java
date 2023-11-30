/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package progettose_gruppo16;

import java.time.LocalDate;

/**
 *
 * @author amost
 */
public class DayOfMonthTrigger implements Trigger{
    
    private int dayOfMonth;

    public DayOfMonthTrigger(int dayOfMonth) {
        this.dayOfMonth = dayOfMonth;
    }

    @Override
    public boolean checkCondition() {
        return this.dayOfMonth == LocalDate.now().getDayOfMonth();
    }

    @Override
    public String toString() {
        return "Day of month: " + String.format("%02d", dayOfMonth);
    }
}
