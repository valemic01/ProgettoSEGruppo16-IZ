package progettose_gruppo16;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettose_gruppo16.trigger.CompositeTrigger;
import progettose_gruppo16.trigger.DateTrigger;
import progettose_gruppo16.trigger.DayOfMonthTrigger;
import progettose_gruppo16.trigger.DayOfWeekTrigger;
import progettose_gruppo16.trigger.TimeOfDayTrigger;

/**
 * This class contains unit tests for the CompositeTrigger class.
 * It covers various scenarios to ensure the proper functioning of CompositeTrigger.
 */
public class CompositeTriggerTest {

    public CompositeTriggerTest() {
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
     * Test method to check the condition when using the "and" operator.
     * It creates a CompositeTrigger with DateTrigger and DayOfMonthTrigger, both with positive conditions.
     * The expected result is true.
     */
    @Test
    public void testCheckCondition1() {
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        CompositeTrigger compositeTrigger = new CompositeTrigger(dateTrigger, dayOfMonthTrigger, false, "and");
        assertTrue(compositeTrigger.checkCondition());
    }

    /**
     * Test method to check the condition when using the "and" operator with negation.
     * It creates a CompositeTrigger with DateTrigger and DayOfMonthTrigger, one with negation.
     * The expected result is false.
     */
    @Test
    public void testCheckCondition2() {
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        CompositeTrigger compositeTrigger = new CompositeTrigger(dateTrigger, dayOfMonthTrigger, true, "and");
        assertFalse(compositeTrigger.checkCondition());
    }

    /**
     * Test method to check the condition when using the "or" operator.
     * It creates a CompositeTrigger with DateTrigger and DayOfMonthTrigger, one with positive condition.
     * The expected result is true.
     */
    @Test
    public void testCheckCondition3() {
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), true);
        CompositeTrigger compositeTrigger = new CompositeTrigger(dateTrigger, dayOfMonthTrigger, false, "or");
        assertTrue(compositeTrigger.checkCondition());
    }

    /**
     * Test method to check the condition when using the "or" operator with negation.
     * It creates a CompositeTrigger with DateTrigger and DayOfMonthTrigger, both with negation.
     * The expected result is false.
     */
    @Test
    public void testCheckCondition4() {
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), true);
        CompositeTrigger compositeTrigger = new CompositeTrigger(dateTrigger, dayOfMonthTrigger, true, "or");
        assertFalse(compositeTrigger.checkCondition());
    }

    /**
     * Test method to check the condition with reversed trigger order and "and" operator.
     * It creates a CompositeTrigger with DayOfMonthTrigger and DateTrigger, both with positive conditions.
     * The expected result is true.
     */
    @Test
    public void testCheckCondition5() {
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        CompositeTrigger compositeTrigger = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "and");
        assertTrue(compositeTrigger.checkCondition());
    }

    /**
     * Test method to check the condition with nested composite triggers using the "and" operator.
     * It creates two CompositeTriggers, each with different triggers, and combines them with "and".
     * The expected result is true.
     */
    @Test
    public void testCheckCondition6() {
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        DayOfWeekTrigger dayOfWeekTrigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false);
        CompositeTrigger compositeTrigger1 = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "and");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger(compositeTrigger1, dayOfWeekTrigger, false, "and");
        assertTrue(compositeTrigger2.checkCondition());
    }

    /**
     * Test method to check the condition with nested composite triggers using "or" and "and" operators.
     * It creates two CompositeTriggers, each with different triggers, and combines them with "and" and "or".
     * The expected result is true.
     */
    @Test
    public void testCheckCondition7() {
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        DayOfWeekTrigger dayOfWeekTrigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), true);
        CompositeTrigger compositeTrigger1 = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "and");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger(compositeTrigger1, dayOfWeekTrigger, false, "or");
        assertTrue(compositeTrigger2.checkCondition());
    }

    /**
     * Test method to check the condition with nested composite triggers using "or" and "and" operators with negation.
     * It creates two CompositeTriggers, each with different triggers, and combines them with "and" and "or" with negation.
     * The expected result is false.
     */
    @Test
    public void testCheckCondition8() {
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        DayOfWeekTrigger dayOfWeekTrigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), true);
        CompositeTrigger compositeTrigger1 = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "and");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger(compositeTrigger1, dayOfWeekTrigger, true, "or");
        assertFalse(compositeTrigger2.checkCondition());
    }

    /**
     * Test method to check the condition with nested composite triggers using "and" operator and reversed order.
     * It creates two CompositeTriggers, each with different triggers, and combines them with "and" with reversed order.
     * The expected result is true.
     */
    @Test
    public void testCheckCondition9() {
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        DayOfWeekTrigger dayOfWeekTrigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false);
        CompositeTrigger compositeTrigger1 = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "and");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger(dayOfWeekTrigger, compositeTrigger1, false, "and");
        assertTrue(compositeTrigger2.checkCondition());
    }

    /**
     * Test method to check the condition with multiple levels of nested composite triggers.
     * It creates three CompositeTriggers, each with different triggers, and combines them with "and".
     * The expected result is true.
     */
    @Test
    public void testCheckCondition10() {
        LocalTime currentTime = LocalTime.now();
        TimeOfDayTrigger timeOfDayTrigger = new TimeOfDayTrigger(currentTime, false);
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), false);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        DayOfWeekTrigger dayOfWeekTrigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false);
        CompositeTrigger compositeTrigger1 = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "and");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger(dayOfWeekTrigger, timeOfDayTrigger, false, "and");
        CompositeTrigger compositeTrigger3 = new CompositeTrigger(compositeTrigger1, compositeTrigger2, false, "and");
        assertTrue(compositeTrigger3.checkCondition());
    }

    /**
     * Test method to check the condition with a complex combination of triggers.
     * It creates three CompositeTriggers, each with different triggers, and combines them with "and" and "or".
     * The expected result is false.
     */
    @Test
    public void testCheckCondition11() {
        LocalTime currentTime = LocalTime.now();
        TimeOfDayTrigger timeOfDayTrigger = new TimeOfDayTrigger(currentTime, false);
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), true);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        DayOfWeekTrigger dayOfWeekTrigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false);
        CompositeTrigger compositeTrigger1 = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "and");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger(dayOfWeekTrigger, timeOfDayTrigger, false, "and");
        CompositeTrigger compositeTrigger3 = new CompositeTrigger(compositeTrigger1, compositeTrigger2, false, "and");
        assertFalse(compositeTrigger3.checkCondition());
    }

    /**
     * Test method to check the condition with a complex combination of triggers with "or" and "and" operators.
     * It creates three CompositeTriggers, each with different triggers, and combines them with "and" and "or".
     * The expected result is true.
     */
    @Test
    public void testCheckCondition12() {
        LocalTime currentTime = LocalTime.now();
        TimeOfDayTrigger timeOfDayTrigger = new TimeOfDayTrigger(currentTime, false);
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), true);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        DayOfWeekTrigger dayOfWeekTrigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false);
        CompositeTrigger compositeTrigger1 = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "or");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger(dayOfWeekTrigger, timeOfDayTrigger, false, "and");
        CompositeTrigger compositeTrigger3 = new CompositeTrigger(compositeTrigger1, compositeTrigger2, false, "and");
        assertTrue(compositeTrigger3.checkCondition());
    }

    /**
     * Test method to check the condition with a complex combination of triggers with "and" operator and negation.
     * It creates three CompositeTriggers, each with different triggers, and combines them with "and" with negation.
     * The expected result is true.
     */
    @Test
    public void testCheckCondition13() {
        LocalTime currentTime = LocalTime.now();
        TimeOfDayTrigger timeOfDayTrigger = new TimeOfDayTrigger(currentTime, false);
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), true);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        DayOfWeekTrigger dayOfWeekTrigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false);
        CompositeTrigger compositeTrigger1 = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "and");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger(dayOfWeekTrigger, timeOfDayTrigger, false, "and");
        CompositeTrigger compositeTrigger3 = new CompositeTrigger(compositeTrigger1, compositeTrigger2, false, "or");
        assertTrue(compositeTrigger3.checkCondition());
    }

    /**
     * Test method to check the condition with a complex combination of triggers with "and" operator and negation.
     * It creates three CompositeTriggers, each with different triggers, and combines them with "and" with negation.
     * The expected result is true.
     */
    @Test
    public void testCheckCondition14() {
        LocalTime currentTime = LocalTime.now();
        TimeOfDayTrigger timeOfDayTrigger = new TimeOfDayTrigger(currentTime, false);
        DateTrigger dateTrigger = new DateTrigger(LocalDate.now(), true);
        DayOfMonthTrigger dayOfMonthTrigger = new DayOfMonthTrigger(LocalDate.now().getDayOfMonth(), false);
        DayOfWeekTrigger dayOfWeekTrigger = new DayOfWeekTrigger(LocalDate.now().getDayOfWeek(), false);
        CompositeTrigger compositeTrigger1 = new CompositeTrigger(dayOfMonthTrigger, dateTrigger, false, "and");
        CompositeTrigger compositeTrigger2 = new CompositeTrigger(dayOfWeekTrigger, timeOfDayTrigger, false, "and");
        CompositeTrigger compositeTrigger3 = new CompositeTrigger(compositeTrigger1, compositeTrigger2, true, "and");
        assertTrue(compositeTrigger3.checkCondition());
    }
}