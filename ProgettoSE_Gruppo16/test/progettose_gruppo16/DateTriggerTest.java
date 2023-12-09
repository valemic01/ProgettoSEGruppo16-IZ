package progettose_gruppo16;

import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettose_gruppo16.trigger.DateTrigger;

public class DateTriggerTest {
    
    public DateTriggerTest() {
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
    * Test that a DateTrigger with the current date and no negation returns true.
    */
    @Test
    public void testCheckCondition1() {
        DateTrigger trigger = new DateTrigger(LocalDate.now(), false);

        assertTrue(trigger.checkCondition());
    }

    /**
     * Test that a DateTrigger with a future date and no negation returns false.
     */
    @Test
    public void testCheckCondition2() {
        DateTrigger trigger = new DateTrigger(LocalDate.now().plusDays(1), false);

        assertFalse(trigger.checkCondition());
    }

    /**
     * Test that a DateTrigger with the current date and negation returns false.
     */
    @Test
    public void testCheckCondition3() {
        DateTrigger trigger = new DateTrigger(LocalDate.now(), true);

        assertFalse(trigger.checkCondition());
    }

    /**
     * Test that a DateTrigger with a future date and negation returns true.
     */
    @Test
    public void testCheckCondition4() {
        DateTrigger trigger = new DateTrigger(LocalDate.now().plusDays(1), true);

        assertTrue(trigger.checkCondition());
    }

    /**
     * Test that a DateTrigger with a null date throws a NullPointerException.
     */
    @Test(expected = NullPointerException.class)
    public void testCheckCondition5() {
        DateTrigger trigger = new DateTrigger(null, true);

        assertFalse(trigger.checkCondition());
    }
    
}