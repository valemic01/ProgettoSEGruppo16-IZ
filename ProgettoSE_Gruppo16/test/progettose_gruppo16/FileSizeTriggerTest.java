package progettose_gruppo16;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class FileSizeTriggerTest {
    
    public FileSizeTriggerTest() {
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
        FileSizeTrigger trigger= new FileSizeTrigger(file, 300000, 0);

        assertTrue(trigger.checkCondition());
    }
    
    @Test
    public void testcheckCondition2() {
        File file= new File("C:\\Users\\amost\\Desktop\\INTER-UDINESE_200257523298.pdf");
        FileSizeTrigger trigger= new FileSizeTrigger(file, 500000, 0);

        assertFalse(trigger.checkCondition());
    }
}