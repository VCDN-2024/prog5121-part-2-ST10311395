
package progpart2;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

public class ProgPart2Test {
    
    private ProgPart2 progPart2;

    @BeforeEach
    public void setUp() {
        progPart2 = new ProgPart2("kyl_1", "Ch&&sec@ke99!");
    }

    @Test
    public void testCheckUserName_Valid() {
        assertTrue(ProgPart2.checkUserName("kyl_1"));
    }

    @Test
    public void testCheckUserName_Invalid() {
        assertFalse(ProgPart2.checkUserName("kyle!!!!!"));
       
    }

    @Test
    public void testCheckPasswordComplexity_Valid() {
        assertTrue(ProgPart2.checkPasswordComplexity("Ch&&sec@ke99!"));
    }

    @Test
    public void testCheckPasswordComplexity_Invalid() {
        assertFalse(ProgPart2.checkPasswordComplexity("password"));
        
    }
 @Test
    public void testCheckTaskDescription_Valid() {
        // Test valid task description
        assertTrue(ProgPart2.checkTaskDescription("Short description"));
    }

    @Test
    public void testCheckTaskDescription_Invalid() {
        // Test invalid task description that exceeds 50 characters
        assertFalse(ProgPart2.checkTaskDescription("This description is definitely way too long to be valid since it exceeds 50 characters."));
    }
 @Test
    public void testCreateTaskID() {
        // Test task ID creation logic
        String taskID = ProgPart2.createTaskID("Peter story", "katelyn");
        assertNotNull(taskID);
        assertTrue(taskID.startsWith("PE:"));
        assertTrue(taskID.endsWith(":LYN"));
    } 

 @Test
    public void testGetTaskstatus() {
        
        assertEquals("To Do", ProgPart2.getTaskstatus("1"));
        assertEquals("Done", ProgPart2.getTaskstatus("2"));
        assertEquals("Doing", ProgPart2.getTaskstatus("3"));
       
        
    }

   
}