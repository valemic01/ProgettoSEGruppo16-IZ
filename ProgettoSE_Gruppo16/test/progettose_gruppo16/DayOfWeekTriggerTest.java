package progettose_gruppo16;

import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettose_gruppo16.trigger.DayOfWeekTrigger;

public class DayOfWeekTriggerTest {
    
    public DayOfWeekTriggerTest() {
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
     * Test of checkCondition method, of class DayOfWeekTrigger.
     */
    @Test
    public void testCheckCondition1() {
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek());

        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition2() {
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek().plus(1));

        assertFalse(trigger.checkCondition());
    }
    
}