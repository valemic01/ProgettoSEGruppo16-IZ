/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettose_gruppo16;

import java.time.LocalTime;
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
public class TimeOfDayTriggerTest {
    
    public TimeOfDayTriggerTest() {
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

 
    /**
     * Test of equals method, of class TimeOfDayTrigger.
     */
    @Test
    public void testEquals() {
        LocalTime time1 = LocalTime.of(10, 30);
        LocalTime time2 = LocalTime.of(10, 30);
        TimeOfDayTrigger trigger1 = new TimeOfDayTrigger(time1);
        TimeOfDayTrigger trigger2 = new TimeOfDayTrigger(time2);

        assertTrue(trigger1.equals(trigger2));
    }

    @Test
    public void testNotEquals() {
        LocalTime time1 = LocalTime.of(10, 30);
        LocalTime time2 = LocalTime.of(12, 45);
        TimeOfDayTrigger trigger1 = new TimeOfDayTrigger(time1);
        TimeOfDayTrigger trigger2 = new TimeOfDayTrigger(time2);

        assertFalse(trigger1.equals(trigger2));
    }

    /**
     * Test of checkCondition method, of class TimeOfDayTrigger.
     */
    @Test
    public void testCheckCondition1() {
        LocalTime currentTime = LocalTime.now();
        TimeOfDayTrigger trigger = new TimeOfDayTrigger(currentTime);

        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition2() {
        LocalTime currentTime = LocalTime.of(0, 0);
        TimeOfDayTrigger trigger = new TimeOfDayTrigger(currentTime);

        assertFalse(trigger.checkCondition());
    }

    
    
}
