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
     * Test of checkCondition method, of class DateTrigger.
     */
    @Test
    public void testCheckCondition1() {
        DateTrigger trigger = new DateTrigger(LocalDate.now());
        
        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition2() {
        DateTrigger trigger = new DateTrigger(LocalDate.of(2001,12 , 19));
        
        assertFalse(trigger.checkCondition());
    }
    
}