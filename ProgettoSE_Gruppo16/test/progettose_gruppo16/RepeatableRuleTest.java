package progettose_gruppo16;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import progettose_gruppo16.trigger.DayOfWeekTrigger;
import progettose_gruppo16.trigger.Trigger;


public class RepeatableRuleTest {
    
    private Trigger trigger;
    private ActionTest action;
    private RepeatableRuleTestClass rule;
    
    public RepeatableRuleTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        action = new ActionTest();
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test to check if the action is executed when the condition is true.
     */
    @Test
    public void testEvaluate1() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false); 
        rule = new RepeatableRuleTestClass("ciao", trigger, action, 0, LocalTime.of(0, 10));
        
        assertTrue(rule.evaluate());    
    } 
    
    /**
     * Test to check if the rule can be executed during the sleeping period.
     */
    @Test
    public void testEvaluate2() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false); 
        rule = new RepeatableRuleTestClass("ciao", trigger, action, 0, LocalTime.of(0, 10));
        rule.evaluate();
        assertFalse(rule.evaluate());  
    } 
    
    /**
     * Test to check if the rule is executed again at the end of the sleeping period (hours, minutes).
     */
    @Test
    public void testEvaluate3() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false); 
        rule = new RepeatableRuleTestClass("ciao", trigger, action, 0, LocalTime.of(8, 59));
        rule.evaluate();
        rule.setTimeLastFired(rule.getTimeLastFired().minusMinutes(rule.getSleepPeriod().getMinute()).minusHours(rule.getSleepPeriod().getHour()).minusNanos(1));
        assertTrue(rule.evaluate());
    } 
    
    /**
     * Test to check if the rule is executed again at the end of the sleeping period (days, hours, minutes).
     */
    @Test
    public void testEvaluate4() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false); 
        rule = new RepeatableRuleTestClass("ciao", trigger, action, 1, LocalTime.of(3, 45));
        rule.evaluate();
        rule.setTimeLastFired(rule.getTimeLastFired().minusMinutes(rule.getSleepPeriod().getMinute()).minusHours(rule.getSleepPeriod().getHour()).minusNanos(1));
        rule.setDayLastFired(rule.getDayLastFired().minusDays(rule.getDaysToSleep()));
        assertTrue(rule.evaluate());
    } 
}