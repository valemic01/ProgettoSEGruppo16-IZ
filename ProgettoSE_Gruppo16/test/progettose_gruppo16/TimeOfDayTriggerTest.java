package progettose_gruppo16;

import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettose_gruppo16.trigger.TimeOfDayTrigger;

/**
 * Test class for the TimeOfDayTrigger.
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
     * Test if the checkCondition method returns true when the current time matches the trigger time.
     */
    @Test
    public void testCheckCondition1() {
        LocalTime currentTime = LocalTime.now();
        TimeOfDayTrigger trigger = new TimeOfDayTrigger(currentTime, false);

        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the current time is earlier than the trigger time.
     */
    @Test
    public void testCheckCondition2() {
        LocalTime time = LocalTime.now().minusMinutes(1);
        TimeOfDayTrigger trigger = new TimeOfDayTrigger(time, false);

        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the current time matches the trigger time and the trigger is negated.
     */
    @Test
    public void testCheckCondition3() {
        LocalTime currentTime = LocalTime.now();
        TimeOfDayTrigger trigger = new TimeOfDayTrigger(currentTime, true);

        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the current time is earlier than the trigger time and the trigger is negated.
     */
    @Test
    public void testCheckCondition4() {
        LocalTime time = LocalTime.now().minusMinutes(1);
        TimeOfDayTrigger trigger = new TimeOfDayTrigger(time, true);

        assertTrue(trigger.checkCondition());
    }
}