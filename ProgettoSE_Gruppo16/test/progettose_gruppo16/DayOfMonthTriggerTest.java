/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettose_gruppo16;

import java.time.LocalDate;
import java.time.MonthDay;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author amost
 */
public class DayOfMonthTriggerTest {
    
    public DayOfMonthTriggerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testCheckCondition1() {
        
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth());

        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition2() {
        
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(1);
        assertFalse(trigger.checkCondition());
    }
    
}
