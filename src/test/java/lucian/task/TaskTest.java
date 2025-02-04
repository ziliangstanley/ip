package lucian.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TaskTest {

    @Test
    public void testMarkAsDone() {
        Task task = new ToDo("Read book");
        task.markAsDone();
        assertTrue(task.isDone, "Task should be marked as done");
    }

    @Test
    public void testMarkAsNotDone() {
        Task task = new ToDo("Read book");
        task.markAsDone();
        task.markAsNotDone();
        assertFalse(task.isDone, "Task should be marked as not done");
    }

}
