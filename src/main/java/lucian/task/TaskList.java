package lucian.task;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> listOfTasks;

    public TaskList() {
        this.listOfTasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> existingTasks) {
        this.listOfTasks = existingTasks;
    }

    public void addTask(Task task) {
        listOfTasks.add(task);
    }

    public Task removeTask(int index) {
        return listOfTasks.remove(index);
    }

    public Task getTask(int index) {
        return listOfTasks.get(index);
    }

    public int getSize() {
        return listOfTasks.size();
    }

    public void printTasks() {
        if (listOfTasks.size() == 0) {
            System.out.println("There is nothing in the list now.");
        } else {
            System.out.println("The following items are in the list now.");
            for (int i = 0; i < listOfTasks.size(); i++) {
                System.out.println(i + 1 + "." + listOfTasks.get(i).toString());
            }
        }
    }
}
