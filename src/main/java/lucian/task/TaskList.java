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
