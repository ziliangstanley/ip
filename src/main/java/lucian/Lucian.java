package lucian;

import java.io.IOException;

import lucian.exceptions.LucianException;
import lucian.storage.Storage;
import lucian.task.Deadline;
import lucian.task.Event;
import lucian.task.Task;
import lucian.task.TaskList;
import lucian.task.ToDo;
import lucian.ui.Ui;

/**
 * Represents the main entry point for the Lucian chatbot.
 * It initializes components and handles user interactions.
 */
public class Lucian {
    private static final String FILE_NAME = "./data/tasks.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    /**
     * Initializes the Lucian chatbot with a storage file.
     */
    public Lucian() {
        ui = new Ui();
        storage = new Storage(FILE_NAME);
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public String getGreeting() {
        return ui.showWelcome();
    }

//    /**
//     * Starts the chatbot's main event loop.
//     */
//    public void run() {
//        ui.showWelcome();
//
//        String output = "";
//        while (output != ui.showGoodbye()) {
//            String userInput = ui.readCommand();
//            output = handleCommand(userInput);
//        }
//    }

    /**
     * Processes a user command.
     *
     * @param userInput The input command entered by the user.
     * @return {@code true} if the program should exit, {@code false} otherwise.
     */
    public String handleCommand(String userInput) {
        try {
            String[] words = userInput.split(" ", 2);
            String command = words[0];

            switch (command) {
            case "bye":
                storage.save(tasks);
                return ui.showGoodbye();
            case "find":
                String keyword = userInput.substring(6);
                return ui.showMessage(tasks.findTasks(keyword));
            case "list":
                return ui.showMessage(tasks.printTasks());
            case "mark":
                assert words.length > 1 : "Missing index for mark command.";
                int markIndex = Integer.parseInt(words[1]) - 1;
                assert markIndex >= 0 && markIndex < tasks.getSize() : "Invalid task index for mark command.";
                tasks.getTask(markIndex).markAsDone();
                return ui.showMessage("Alright, I've marked this task as done:\n" + tasks.getTask(markIndex));
            case "unmark":
                int unmarkIndex = Integer.parseInt(words[1]) - 1;
                tasks.getTask(unmarkIndex).markAsNotDone();
                return ui.showMessage("Alright, I've marked this task as not done yet:\n" + tasks.getTask(unmarkIndex));
            case "delete":
                int deleteIndex = Integer.parseInt(words[1]) - 1;
                Task removedTask = tasks.removeTask(deleteIndex);
                return ui.showMessage("Sure, I'll remove this task:\n" + removedTask + "\nNow you have "
                        + tasks.getSize() + " tasks in the list.");
            case "todo":
            case "deadline":
            case "event":
                Task newTask = createTask(userInput);
                tasks.addTask(newTask);
                return ui.showMessage("Roger. I'll be adding this task to the list:\n" + newTask + "\nNow you have "
                        + tasks.getSize() + " tasks in the list.");
            default:
                return ui.showMessage("You did not give me a valid command...");
            }
        } catch (Exception e) {
            return ui.showMessage("Error: " + e.getMessage());
        }
    }

    /**
     * Creates a task based on the user input.
     *
     * @param input The user's input containing the task type and details.
     * @return The created {@code Task} object.
     * @throws LucianException If the task format is invalid.
     */
    private Task createTask(String input) throws LucianException {
        Task createdTask;
        String description;
        if (input.startsWith("todo ")) {
            assert input.length() > 5 : "Todo description cannot be empty.";
            description = input.substring(5);
            createdTask = new ToDo(description);
        } else if (input.startsWith("deadline ")) {
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) {
                throw new LucianException("Your Deadline should have a /by date...");
            }
            description = input.substring(9, byIndex - 1);
            String byString = input.substring(byIndex + 4).trim();

            assert !byString.isEmpty() : "Deadline date cannot be empty.";
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
                createdTask = new Event(description, java.time.LocalDate.parse(fromString),
                        java.time.LocalDate.parse(toString));
            } catch (java.time.format.DateTimeParseException e) {
                throw new LucianException("Use YYYY-MM-DD format...");
            }
        }
        return createdTask;
    }

//    /**
//     * The main entry point for the application.
//     *
//     * @param args Command-line arguments (not used).
//     */
//    public static void main(String[] args) {
//        new Lucian().run();
//    }
}
