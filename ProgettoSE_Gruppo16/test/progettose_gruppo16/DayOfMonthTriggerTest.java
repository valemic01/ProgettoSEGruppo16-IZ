package progettose_gruppo16;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettose_gruppo16.trigger.DayOfMonthTrigger;

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

    /**
    * Test that a DayOfMonthTrigger with the current day and no negation returns true.
    */
    @Test
    public void testCheckCondition1() {
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);

        assertTrue(trigger.checkCondition());
    }

    /**
     * Test that a DayOfMonthTrigger with the next day and no negation returns false.
     */
    @Test
    public void testCheckCondition2() {
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth() + 1, false);

        assertFalse(trigger.checkCondition());
    }

    /**
     * Test that a DayOfMonthTrigger with the current day and negation returns false.
     */
    @Test
    public void testCheckCondition3() {
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), true);

        assertFalse(trigger.checkCondition());
    }

    /**
     * Test that a DayOfMonthTrigger with the next day and negation returns true.
     */
    @Test
    public void testCheckCondition4() {
        DayOfMonthTrigger trigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth() + 1, true);

        assertTrue(trigger.checkCondition());
    }
}