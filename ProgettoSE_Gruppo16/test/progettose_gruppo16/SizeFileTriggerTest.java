package progettose_gruppo16;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class SizeFileTriggerTest {
    
    public SizeFileTriggerTest() {
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
    public void testcheckCondition1() {
        File file= new File("C:\\Users\\amost\\Desktop\\INTER-UDINESE_200257523298.pdf");
        SizeFileTrigger trigger= new SizeFileTrigger(file, 300000);

        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testcheckCondition2() {
        File file= new File("C:\\Users\\amost\\Desktop\\INTER-UDINESE_200257523298.pdf");
        SizeFileTrigger trigger= new SizeFileTrigger(file, 500000);

        assertFalse(trigger.checkCondition());
    }
}