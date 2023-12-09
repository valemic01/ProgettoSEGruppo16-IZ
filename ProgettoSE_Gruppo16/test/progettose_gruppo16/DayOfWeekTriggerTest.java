package progettose_gruppo16;

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
    * Test that a DayOfWeekTrigger with the current day and no negation returns true.
    */
    @Test
    public void testCheckCondition1() {
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false);

        assertTrue(trigger.checkCondition());
    }

    /**
     * Test that a DayOfWeekTrigger with the next day of the week and no negation returns false.
     */
    @Test
    public void testCheckCondition2() {
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek().plus(1), false);

        assertFalse(trigger.checkCondition());
    }

    /**
     * Test that a DayOfWeekTrigger with the current day of the week and negation returns false.
     */
    @Test
    public void testCheckCondition3() {
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), true);

        assertFalse(trigger.checkCondition());
    }

    /**
     * Test that a DayOfWeekTrigger with the next day of the week and negation returns true.
     */
    @Test
    public void testCheckCondition4() {
        DayOfWeekTrigger trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek().plus(1), true);

        assertTrue(trigger.checkCondition());
    }
}