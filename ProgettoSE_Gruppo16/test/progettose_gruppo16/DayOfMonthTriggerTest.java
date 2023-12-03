package progettose_gruppo16;

import progettose_gruppo16.trigger.DayOfMonthTrigger;
import java.time.LocalDate;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

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