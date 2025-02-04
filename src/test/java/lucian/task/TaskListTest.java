package lucian.task;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TaskListTest {

    @Test
    public void testAddTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Finish CS2103T assignment");

        taskList.addTask(task);
        assertEquals(1, taskList.getSize(), "Task list should have 1 task");
        assertEquals(task, taskList.getTask(0), "The added task should match");
    }

    @Test
    public void testRemoveTask() {
        TaskList taskList = new TaskList();
        Task task = new ToDo("Submit report");
        taskList.addTask(task);
        taskList.removeTask(0);

        assertEquals(0, taskList.getSize(), "Task list should be empty after removing");
    }
}
