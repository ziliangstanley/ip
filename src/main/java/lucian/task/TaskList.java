package lucian.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Manages a list of tasks.
 */
public class TaskList {
    private ArrayList<Task> listOfTasks;

    /**
     * Creates an empty task list.
     */
    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    /**
     * Creates a task list with preloaded tasks.
     *
     * @param existingTasks The tasks to load into the list.
     */
    public TaskList(ArrayList<Task> existingTasks) {
        this.listOfTasks = existingTasks;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    /**
     * Removes a task from the task list.
     *
     * @param index The index of the task to remove.
     * @return The removed task.
     */
    public Task removeTask(int index) {
        return listOfTasks.remove(index);
    }

    /**
     * Retrieves a task by its index.
     *
     * @param index The index of the task.
     * @return The task at the specified index.
     */
    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return listOfTasks.size();
    }

    /**
     * Prints all tasks in the list.
     * If the list is empty, a message is returned.
     */
    public String printTasks() {
        if (listOfTasks.isEmpty()) {
            return "There is nothing in the list now.";
        } else {
            StringBuilder output = new StringBuilder("The following items are in the list now.\n");
            for (int i = 0; i < listOfTasks.size(); i++) {
                output.append(i + 1).append(". ").append(listOfTasks.get(i).toString() + "\n");
            }
            return output.toString();
        }
    }

    public String findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currentTask = listOfTasks.get(i);
            if (currentTask.getDescription().contains(keyword)) {
                matchingTasks.add(currentTask);
            }
        }
        if (matchingTasks.isEmpty()) {
            return "There are no tasks in your list that has this keyword.";
        } else {
            StringBuilder output = new StringBuilder("The following items match the keyword.\n");
            for (int j = 0; j < matchingTasks.size(); j++) {
                output.append(j + 1).append(". ").append(matchingTasks.get(j).toString() + "\n");
            }
            return output.toString();
        }
    }


    /**
     * Computes statistics about the tasks.
     *
     * @return A formatted string containing task statistics.
     */
    public String getStatistics() {
        int totalTasks = listOfTasks.size();
        int completedTasks = 0;
        Map<String, Integer> taskTypeCounts = new HashMap<>();
        taskTypeCounts.put("ToDo", 0);
        taskTypeCounts.put("Deadline", 0);
        taskTypeCounts.put("Event", 0);

        for (Task task : listOfTasks) {
            if (task.isDone) {
                completedTasks++;
            }
            String taskType = task.getClass().getSimpleName(); // Gets "ToDo", "Deadline", or "Event"
            taskTypeCounts.put(taskType, taskTypeCounts.getOrDefault(taskType, 0) + 1);
        }

        String mostCommonType = taskTypeCounts.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();

        double completionRate = totalTasks > 0 ? (completedTasks * 100.0) / totalTasks : 0.0;

        return String.format(
                "Task Statistics:\n- Total tasks: %d\n- Completed tasks: %d (%.1f%%)\n- Most common task type: %s",
                totalTasks, completedTasks, completionRate, mostCommonType
        );
    }
}
