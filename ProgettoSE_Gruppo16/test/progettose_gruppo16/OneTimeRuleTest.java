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
import progettose_gruppo16.trigger.TimeOfDayTrigger;
import progettose_gruppo16.trigger.Trigger;

/**
 *  Test Class for One Time Rule
 */

public class OneTimeRuleTest {
    
    private Trigger trigger;
    private ActionTest action;
    private OneTimeRuleTestClass rule;
    
    public OneTimeRuleTest() {
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
        trigger = new TimeOfDayTrigger(LocalTime.now()); 
        rule = new OneTimeRuleTestClass("ciao", trigger, action);
        
        assertTrue(rule.evaluate());    
    } 
    
    /**
     * Test per valutare se la regola viene disattivata dopo la sua esecuzione
     */
    @Test
    public void testEvaluate2() {
        trigger = new TimeOfDayTrigger(LocalTime.now()); 
        rule = new OneTimeRuleTestClass("ciao", trigger, action);
        rule.evaluate();
        assertFalse(rule.isActive());   
    } 
    
    /**
     * Test per valutare se quando la condizione è falsa, l'azione non viene eseguita
     */
    @Test
    public void testEvaluate3() {
        trigger = new TimeOfDayTrigger(LocalTime.now().minusHours(1));      //different time
        rule = new OneTimeRuleTestClass("ciao", trigger, action);
        
        assertFalse(rule.evaluate());     
    } 
    
    /**
     * Test per valutare se dopo che la regola è stata riattivata è di nuovo eseguibile
     */
    @Test
    public void testEvaluate4() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek());
        rule = new OneTimeRuleTestClass("ciao", trigger, action);
        rule.evaluate();
        rule.setActive(true);
        
        assertTrue(rule.evaluate());     
    } 
    
}