package lucian.command;

import lucian.exceptions.LucianException;
import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.ui.Ui;

public class UnmarkCommand extends Command {
    private final String[] words;

    public UnmarkCommand(String[] words) {
        this.words = words;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws LucianException {
        if (words.length == 1) {
            throw new LucianException("You didn't specify the index to unmark...");
        }
        int unmarkIndex = Integer.parseInt(words[1]) - 1;
        if (unmarkIndex < 0 || unmarkIndex > tasks.getSize()) {
            throw new LucianException("This index isn't in the list...");
        }
        tasks.getTask(unmarkIndex).markAsNotDone();
        return ui.showMessage("Task marked as not done yet:\n" + tasks.getTask(unmarkIndex));
    }
}
