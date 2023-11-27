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
