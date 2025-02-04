package lucian.task;

import java.util.ArrayList;

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
    public void printTasks() {
        if (listOfTasks.isEmpty()) {
            System.out.println("There is nothing in the list now.");
        } else {
            System.out.println("The following items are in the list now.");
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.println(i + 1 + "." + listOfTasks.get(i).toString());
            }
        }
    }

    public void findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < listOfTasks.size(); i++) {
            Task currentTask = listOfTasks.get(i);
            if (currentTask.getDescription().contains(keyword)) {
                matchingTasks.add(currentTask);
            }
        }
        if (matchingTasks.isEmpty()) {
            System.out.println("There are no tasks in your list that has this keyword.");
        } else {
            for (int j = 0; j < matchingTasks.size(); j++) {
                System.out.println(j + 1 + "." + matchingTasks.get(j).toString());
            }
        }
    }
}
