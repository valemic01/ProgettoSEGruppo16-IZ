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
 * Test Class for One Time Rule
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
     * Test if the rule executes the action when the condition is true.
     */
    @Test
    public void testEvaluate1() {
        trigger = new TimeOfDayTrigger(LocalTime.now(), false);
        rule = new OneTimeRuleTestClass("ciao", trigger, action);

        assertTrue(rule.evaluate());
    }

    /**
     * Test if the rule is deactivated after its execution.
     */
    @Test
    public void testEvaluate2() {
        trigger = new TimeOfDayTrigger(LocalTime.now(), false);
        rule = new OneTimeRuleTestClass("ciao", trigger, action);
        rule.evaluate();
        assertFalse(rule.isActive());
    }

    /**
     * Test if the action is not executed when the condition is false.
     */
    @Test
    public void testEvaluate3() {
        trigger = new TimeOfDayTrigger(LocalTime.now().minusHours(1), false);
        rule = new OneTimeRuleTestClass("ciao", trigger, action);

        assertFalse(rule.evaluate());
    }

    /**
     * Test if the rule is executable again after being reactivated.
     */
    @Test
    public void testEvaluate4() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false);
        rule = new OneTimeRuleTestClass("ciao", trigger, action);
        rule.evaluate();
        rule.setActive(true);

        assertTrue(rule.evaluate());
    }

    /**
     * Test if the action is not executed when the condition is true with negation.
     */
    @Test
    public void testEvaluate5() {
        trigger = new TimeOfDayTrigger(LocalTime.now(), true);
        rule = new OneTimeRuleTestClass("ciao", trigger, action);

        assertFalse(rule.evaluate());
    }

    /**
     * Test if the rule remains active after execution with negation.
     */
    @Test
    public void testEvaluate6() {
        trigger = new TimeOfDayTrigger(LocalTime.now(), true);
        rule = new OneTimeRuleTestClass("ciao", trigger, action);
        rule.evaluate();
        assertTrue(rule.isActive());
    }

    /**
     * Test if the action is executed when the condition is false with negation.
     */
    @Test
    public void testEvaluate7() {
        trigger = new TimeOfDayTrigger(LocalTime.now().minusHours(1), true);
        rule = new OneTimeRuleTestClass("ciao", trigger, action);

        assertTrue(rule.evaluate());
    }

    /**
     * Test if the rule becomes inactive after execution when reactivated with negation.
     */
    @Test
    public void testEvaluate8() {
        trigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), true);
        rule = new OneTimeRuleTestClass("ciao", trigger, action);
        rule.evaluate();
        rule.setActive(true);

        assertFalse(rule.evaluate());
    }
}