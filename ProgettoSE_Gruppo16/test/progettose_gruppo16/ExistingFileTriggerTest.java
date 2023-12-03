package progettose_gruppo16;

import progettose_gruppo16.trigger.ExistingFileTrigger;
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
public class ExistingFileTriggerTest {
    
    public ExistingFileTriggerTest() {
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
    public void testSomeMethod1() {
        ExistingFileTrigger trigger= new ExistingFileTrigger("C:\\Users\\amost\\Desktop", "INTER-UDINESE_200257523298.pdf");
        assertTrue(trigger.checkCondition());
        
    }
    
    @Test
    public void testSomeMethod() {
        ExistingFileTrigger trigger= new ExistingFileTrigger("C:\\Users\\amost\\Desktop", "INTER-UDINESE.pdf");
        assertFalse(trigger.checkCondition());
        
    }
    
}
