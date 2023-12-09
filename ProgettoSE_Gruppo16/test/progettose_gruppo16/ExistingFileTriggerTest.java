package progettose_gruppo16;

import java.io.File;
import java.io.FileWriter;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import progettose_gruppo16.trigger.ExistingFileTrigger;

public class ExistingFileTriggerTest {

    private static File existingFolder;
    private static File existingFile;
    private static File nonExistingFile;

    @BeforeClass
    public static void setUpClass() throws Exception {
        // Setup tasks that run once for the entire test class
        // This method is annotated with @BeforeClass

        // Create a temporary folder
        existingFolder = new File(System.getProperty("java.io.tmpdir"), "existingfolder");
        existingFolder.mkdir();

        // Create a temporary file inside the existing folder
        existingFile = new File(existingFolder, "existingfile.txt");
        try (FileWriter writer = new FileWriter(existingFile)) {
            writer.write("Test content");
        }

        // Create a temporary file that does not exist
        nonExistingFile = new File(existingFolder, "nonexistingfile.txt");
        nonExistingFile.delete(); // Delete the file to make it non-existing
    }

    @AfterClass
    public static void tearDownClass() {
        // Teardown tasks that run once for the entire test class
        // This method is annotated with @AfterClass

        // Delete the temporary files and folder after all tests are done
        if (existingFile != null && existingFile.exists()) {
            existingFile.delete();
        }

        if (nonExistingFile != null && nonExistingFile.exists()) {
            nonExistingFile.delete();
        }

        if (existingFolder != null && existingFolder.exists()) {
            existingFolder.delete();
        }
    }

    @Before
    public void setUp() {
        // Setup tasks that run before each test method
        // This method is annotated with @Before
        // No additional setup needed in this case
    }

    @After
    public void tearDown() {
        // Teardown tasks that run after each test method
        // This method is annotated with @After
        // No additional teardown needed in this case
    }

    /**
     * Test if the checkCondition method returns true when the file exists in the specified folder and the trigger is not negated.
     */
    @Test
    public void testCheckCondition1() {
        ExistingFileTrigger trigger = new ExistingFileTrigger(existingFolder.getAbsolutePath(), "existingfile.txt", false);
        assertTrue(trigger.checkCondition());
    }

    /**
     * Test if the checkCondition method returns false when the file does not exist in the specified folder and the trigger is not negated.
     */
    @Test
    public void testCheckCondition2() {
        ExistingFileTrigger trigger = new ExistingFileTrigger(existingFolder.getAbsolutePath(), "nonexistingfile.txt", false);
        assertFalse(trigger.checkCondition());
    }

    /**
     * Test if the checkCondition method returns false when the file exists in the specified folder and the trigger is negated.
     */
    @Test
    public void testCheckCondition3() {
        ExistingFileTrigger trigger = new ExistingFileTrigger(existingFolder.getAbsolutePath(), "existingfile.txt", true);
        assertFalse(trigger.checkCondition());
    }

    /**
     * Test if the checkCondition method returns true when the file does not exist in the specified folder and the trigger is negated.
     */
    @Test
    public void testCheckCondition4() {
        ExistingFileTrigger trigger = new ExistingFileTrigger(existingFolder.getAbsolutePath(), "nonexistingfile.txt", true);
        assertTrue(trigger.checkCondition());
    }
}