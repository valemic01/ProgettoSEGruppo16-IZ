package progettose_gruppo16;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;

/**
 * Classe test
 * @author alexx
 */
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
     * Test per valutare se quando la condizione è vera, l'azione viene eseguita
     */
    @Test
    public void testEvaluate1() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek()); 
        rule = new RepeatableRuleTestClass("ciao", trigger, action, 0, LocalTime.of(0, 10));
        
        assertTrue(rule.evaluate());    
    } 
    
    /**
     * Test per valutare se la regola può essere eseguita durante lo sleeping period
     */
    @Test
    public void testEvaluate2() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek()); 
        rule = new RepeatableRuleTestClass("ciao", trigger, action, 0, LocalTime.of(0, 10));
        rule.evaluate();
        assertFalse(rule.evaluate());  
    } 
    
    /**
     * Test per valutare se la regola viene di nuovo eseguita al termine dello sleeping period (ore, minuti)
     */
    @Test
    public void testEvaluate3() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek()); 
        rule = new RepeatableRuleTestClass("ciao", trigger, action, 0, LocalTime.of(8, 59));
        rule.evaluate();
        rule.setTimeLastFired(rule.getTimeLastFired().minusMinutes(rule.getSleepPeriod().getMinute()).minusHours(rule.getSleepPeriod().getHour()).minusNanos(1));
        assertTrue(rule.evaluate());
    } 
    
    /**
     * Test per valutare se la regola viene di nuovo eseguita al termine dello sleeping period (giorni, ore, minuti)
     */
    @Test
    public void testEvaluate4() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek()); 
        rule = new RepeatableRuleTestClass("ciao", trigger, action, 1, LocalTime.of(3, 45));
        rule.evaluate();
        rule.setTimeLastFired(rule.getTimeLastFired().minusMinutes(rule.getSleepPeriod().getMinute()).minusHours(rule.getSleepPeriod().getHour()).minusNanos(1));
        rule.setDayLastFired(rule.getDayLastFired().minusDays(rule.getDaysToSleep()));
        assertTrue(rule.evaluate());
    } 
    
}