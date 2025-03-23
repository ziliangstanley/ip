package lucian.command;

import lucian.exceptions.LucianException;
import lucian.storage.Storage;
import lucian.task.Task;
import lucian.task.TaskList;
import lucian.ui.Ui;

public class DeleteCommand extends Command {
    private final String[] words;

    public DeleteCommand(String[] words) {
        this.words = words;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws LucianException {
        if (words.length == 1) {
            throw new LucianException("You didn't specify the index to delete...");
        }
        int deleteIndex = Integer.parseInt(words[1]) - 1;
        if (deleteIndex < 0 || deleteIndex > tasks.getSize()) {
            throw new LucianException("This index isn't in the list...");
        }
        Task removedTask = tasks.removeTask(deleteIndex);
        return ui.showMessage("Sure, I'll remove this task:\n" + removedTask + "\nNow you have "
                + tasks.getSize() + " tasks in the list.");
    }
}
