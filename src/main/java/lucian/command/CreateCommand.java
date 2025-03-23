package lucian.command;

import lucian.exceptions.LucianException;
import lucian.storage.Storage;
import lucian.task.Deadline;
import lucian.task.Event;
import lucian.task.Task;
import lucian.task.TaskList;
import lucian.task.ToDo;
import lucian.ui.Ui;

/**
 * Command which adds a task into the taskList
 */
public class CreateCommand extends Command {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";
    private final String input;

    public CreateCommand(String input) {
        this.input = input;
    }

    /**
     * Creates a task based on the user input.
     *
     * @param tasks The taskList to be updated.
     * @return The created {@code Task} object.
     * @throws LucianException If the task format is invalid.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws LucianException {
        Task createdTask;
        String description;
        if (input.startsWith(TODO)) {
            assert input.length() > 5 : "Todo description cannot be empty.";
            description = input.substring(5);
            if (description.isBlank()) {
                throw new LucianException("Your Todo description cannot be empty...");
            }
            createdTask = new ToDo(description);
        } else if (input.startsWith(DEADLINE)) {
            int byIndex = input.indexOf("/by");
            if (byIndex == -1) {
                throw new LucianException("Your Deadline should have a /by date...");
            }
            if (9 >= (byIndex - 1) || input.substring(9, byIndex - 1).isBlank()) {
                throw new LucianException("Your Deadline description cannot be empty...");
            }
            description = input.substring(9, byIndex - 1);
            String byString = input.substring(byIndex + 4).trim();

            assert !byString.isEmpty() : "Deadline date cannot be empty.";
            try {
                createdTask = new Deadline(description, java.time.LocalDate.parse(byString));
            } catch (java.time.format.DateTimeParseException e) {
                throw new LucianException("Use YYYY-MM-DD format...");
            }
        } else if (input.startsWith(EVENT)) {
            int fromIndex = input.indexOf("/from");
            int toIndex = input.indexOf("/to");
            if (fromIndex == -1 || toIndex == -1) {
                throw new LucianException("Your Event must have /from and /to dates...");
            }
            if (6 >= (fromIndex - 1) || input.substring(6, fromIndex - 1).isBlank()) {
                throw new LucianException("Your Event description cannot be empty...");
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
        } else {
            throw new LucianException("What task are you trying to create here?");
        }
        tasks.addTask(createdTask);
        return ui.showMessage("Roger. I'll be adding this task to the list:\n" + createdTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
