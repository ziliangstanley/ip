package lucian;

import lucian.ui.Ui;
import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.task.Task;
import lucian.task.ToDo;
import lucian.task.Deadline;
import lucian.task.Event;
import lucian.exceptions.LucianException;

import java.io.IOException;

public class Lucian {
    private static final String FILE_NAME = "./data/tasks.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Lucian(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();

        boolean isExit = false;
        while (!isExit) {
            ui.showLine();
            String userInput = ui.readCommand();
            ui.showLine();

            isExit = handleCommand(userInput);
        }
    }

    private boolean handleCommand(String userInput) {
        try {
            String[] words = userInput.split(" ", 2);
            String command = words[0];

            switch (command) {
            case "bye":
                storage.save(tasks);
                ui.showGoodbye();
                return true;
            case "list":
                tasks.printTasks();
                break;
            case "mark":
                int markIndex = Integer.parseInt(words[1]) - 1;
                tasks.getTask(markIndex).markAsDone();
                ui.showMessage("Alright, I've marked this task as done:\n" + tasks.getTask(markIndex));
                break;
            case "unmark":
                int unmarkIndex = Integer.parseInt(words[1]) - 1;
                tasks.getTask(unmarkIndex).markAsNotDone();
                ui.showMessage("Alright, I've marked this task as not done yet:\n" + tasks.getTask(unmarkIndex));
                break;
            case "delete":
                int deleteIndex = Integer.parseInt(words[1]) - 1;
                Task removedTask = tasks.removeTask(deleteIndex);
                ui.showMessage("Sure, I'll remove this task:\n" + removedTask);
                ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
                break;
            case "todo":
            case "deadline":
            case "event":
                Task newTask = createTask(userInput);
                tasks.addTask(newTask);
                ui.showMessage("Roger. I'll be adding this task to the list:\n" + newTask);
                ui.showMessage("Now you have " + tasks.getSize() + " tasks in the list.");
                break;
            default:
                ui.showMessage("You did not give me a valid command...");
            }
        } catch (Exception e) {
            ui.showMessage("Error: " + e.getMessage());
        }
        return false;
    }

    private Task createTask(String input) throws LucianException {
        Task createdTask;
        String description;
        if (input.startsWith("todo ")) {
            description = input.substring(5);
            createdTask = new ToDo(description);
        } else if (input.startsWith("deadline ")) {
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) throw new LucianException("Your Deadline should have a /by date...");

            description = input.substring(9, byIndex - 1);
            String byString = input.substring(byIndex + 4).trim();

            try {
                createdTask = new Deadline(description, java.time.LocalDate.parse(byString));
            } catch (java.time.format.DateTimeParseException e) {
                throw new LucianException("Use YYYY-MM-DD format...");
            }
        } else {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) {
                throw new LucianException("Your Event must have /from and /to dates...");
            }
            description = input.substring(6, fromIndex - 1);
            String fromString = input.substring(fromIndex + 6, toIndex - 1).trim();
            String toString = input.substring(toIndex + 4).trim();

            try {
                createdTask = new Event(description, java.time.LocalDate.parse(fromString), java.time.LocalDate.parse(toString));
            } catch (java.time.format.DateTimeParseException e) {
                throw new LucianException("Use YYYY-MM-DD format...");
            }
        }
        return createdTask;
    }

    public static void main(String[] args) {
        new Lucian(FILE_NAME).run();
    }
}
