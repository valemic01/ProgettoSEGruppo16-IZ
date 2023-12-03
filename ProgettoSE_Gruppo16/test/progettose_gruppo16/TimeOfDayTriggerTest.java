package progettose_gruppo16;

import progettose_gruppo16.trigger.TimeOfDayTrigger;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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