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
public class DateTrigger implements Trigger{

    private LocalDate date; 

    public DateTrigger(LocalDate date) {
        this.date = date;
    }
    
    @Override
    public boolean checkCondition() {
        return date.equals(LocalDate.now());
    }
    
}
