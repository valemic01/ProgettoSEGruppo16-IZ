package progettose_gruppo16;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettose_gruppo16.trigger.FileSizeTrigger;

public class FileSizeTriggerTest{

    private static File testFile;

    @BeforeClass
    public static void setUpClass() throws Exception{
        // Create a single temporary file for the entire test class
        testFile = createTemporaryFileWithSize(200 * 1024); // 200 KB
    }

    @AfterClass
    public static void tearDownClass(){
        // Teardown tasks that run once for the entire test class
        // This method is annotated with @AfterClass
        testFile.delete(); // Assuming you want to delete the file after all tests are done
    }

    @Before
    public void setUp(){
    }

    @After
    public void tearDown(){
    }
    /**
     * Test if the checkCondition method returns false when the file size exceeds the specified limit and the trigger is not negated.
     */
    @Test
    public void testCheckCondition1(){
        FileSizeTrigger trigger = new FileSizeTrigger(testFile, 100000, 0, false);

        assertTrue(trigger.checkCondition());
    }

    /**
     * Test if the checkCondition method returns true when the file size is less than the specified limit and the trigger is not negated.
     */
    @Test
    public void testCheckCondition2(){
        FileSizeTrigger trigger = new FileSizeTrigger(testFile, 300000, 0, false); // Set a limit lower than the file size

        assertFalse(trigger.checkCondition());
    }
    
    /**
     * Test if the checkCondition method returns true when the file size exceeds the specified limit and the trigger is negated.
     */
    @Test
    public void testCheckCondition3(){
        FileSizeTrigger trigger = new FileSizeTrigger(testFile, 100000, 0, true);

        assertFalse(trigger.checkCondition());
    }

    /**
     * Test if the checkCondition method returns false when the file size is less than the specified limit and the trigger is negated.
     */
    @Test
    public void testCheckCondition4(){
        FileSizeTrigger trigger = new FileSizeTrigger(testFile, 300000, 0, true); // Set a limit lower than the file size

        assertTrue(trigger.checkCondition());
    }

    private static File createTemporaryFileWithSize(long size) throws Exception{
        // Create a temporary directory
        Path tempDirectory = Files.createTempDirectory("test");

        // Create a temporary file inside the directory with the specified size
        Path tempFile = Files.createTempFile(tempDirectory, "testfile", ".txt");
        Files.write(tempFile, new byte[(int) size]);

        // Return the file as a File object
        return tempFile.toFile();
    }
}