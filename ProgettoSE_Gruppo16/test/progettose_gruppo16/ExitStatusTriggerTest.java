package progettose_gruppo16;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettose_gruppo16.trigger.ExitStatusTrigger;

/**
 * Test class for the ExitStatusTrigger.
 * On git in the "Programs for testing" folder there are the programs used to test this class.
 * N.B. Download them and change the path to test it on your device.
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

    /**
     * Test if the checkCondition method returns true when the exit status matches the expected value and the trigger is not negated.
     */
    @Test
    public void testCheckCondition1() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus.exe", "5", 5, false);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status does not match the expected value and the trigger is not negated.
     */
    @Test
    public void testCheckCondition2() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus.exe", "5", 7, false);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status matches the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition3() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus3.exe", "5 8 12", 25, false);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status does not match the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition4() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus3.exe", "5 8 12", 22, false);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status matches the expected value and the trigger is not negated.
     */
    @Test
    public void testCheckCondition5() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus\\dist\\ExitStatus.jar", "5", 5, false);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status does not match the expected value and the trigger is not negated.
     */
    @Test
    public void testCheckCondition6() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus\\dist\\ExitStatus.jar", "5", 2, false);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status matches the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition7() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus2\\dist\\ExitStatus2.jar", "5 9 6", 20, false);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status does not match the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition8() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus2\\dist\\ExitStatus2.jar", "5 9 6", 22, false);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status matches the expected value and the trigger is not negated.
     */
    @Test
    public void testCheckCondition9() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\SE\\prova.bat", "5", 5, false);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status does not match the expected value and the trigger is not negated.
     */
    @Test
    public void testCheckCondition10() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\SE\\prova.bat", "5", 8, false);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status matches the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition11() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\SE\\prova.ps1", "5", 5, false);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status does not match the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition12() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\SE\\prova.ps1", "5", 8, false);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status matches the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition13() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus.exe", "5", 5, true);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status does not match the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition14() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus.exe", "5", 7, true);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status matches the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition15() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus3.exe", "5 8 12", 25, true);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status does not match the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition16() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\HPC\\MOSCATO\\Esercizi HPC\\exitStatus3.exe", "5 8 12", 22, true);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status matches the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition17() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus\\dist\\ExitStatus.jar", "5", 5, true);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status does not match the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition18() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus\\dist\\ExitStatus.jar", "5", 2, true);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status matches the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition19() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus2\\dist\\ExitStatus2.jar", "5 9 6", 20, true);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status does not match the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition20() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Documents\\NetBeansProjects\\ExitStatus2\\dist\\ExitStatus2.jar", "5 9 6", 22, true);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status matches the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition21() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\SE\\prova.bat", "5", 5, true);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status does not match the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition22() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\SE\\prova.bat", "5", 8, true);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns false when the exit status matches the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition23() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\SE\\prova.ps1", "5", 5, true);
        // Execute the test
        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the exit status does not match the expected value and the trigger is negated.
     */
    @Test
    public void testCheckCondition24() {
        ExitStatusTrigger trigger = new ExitStatusTrigger("C:\\Users\\amost\\Desktop\\SE\\prova.ps1", "5", 8, true);
        // Execute the test
        assertTrue(trigger.checkCondition());
    }
}