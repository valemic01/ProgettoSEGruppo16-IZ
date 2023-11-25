/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettose_gruppo16;

import java.sql.Time;
import java.time.LocalTime;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author amost
 */
public class RuleTest {
    
    public RuleTest() {
    }
    private TimeOfDayTrigger time;
    private ActionTest action;
    
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
     * Test of evaluate method, of class Rule.
     */
    @Test
    public void testEvaluate1() {
        time = new TimeOfDayTrigger(LocalTime.now()); 
        action= new ActionTest();
        Rule rule = new Rule("ciao", time, action, false, null);
        
        assertTrue(rule.evaluate());    
    } 
    
    @Test
    public void testEvaluate2() {
        time = new TimeOfDayTrigger(LocalTime.now()); 
        action= new ActionTest();
        Rule rule = new Rule("ciao", time, action, false, null);
        
        assertTrue(rule.evaluate());   
        assertFalse(rule.evaluate());   
    } 
    
    @Test
    public void testEvaluate3() {
        time = new TimeOfDayTrigger(LocalTime.of(0,0));  //ora diversa da quella corrente 
        action= new ActionTest();
        Rule rule = new Rule("ciao", time, action, false, null);
        
        assertFalse(rule.evaluate());     
    } 
}
