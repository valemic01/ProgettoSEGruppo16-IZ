/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package progettose_gruppo16;

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
public class ExitStatusTriggerTest {
    
    public ExitStatusTriggerTest() {
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

        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus.exe", "5", 5);
        // Esegui il test
        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition2() {
      
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus.exe", "5", 7);
        // Esegui il test
        assertFalse(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition3() {
      
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus3.exe", "5 8 12", 25);
        // Esegui il test
        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition4() {
      
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus3.exe", "5 8 12", 22);
        // Esegui il test
        assertFalse(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition5() {
      
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus\\dist\\ExitStatus.jar", "5", 5);
        // Esegui il test
        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition6() {
      
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus\\dist\\ExitStatus.jar", "5", 2);
        // Esegui il test
        assertFalse(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition7() {
      
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus2\\dist\\ExitStatus2.jar", "5 9 6", 20);
        // Esegui il test
        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testCheckCondition8() {
      
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus2\\dist\\ExitStatus2.jar", "5 9 6", 22);
        // Esegui il test
        assertFalse(trigger.checkCondition());
    }
}
    
