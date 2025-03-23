package lucian.command;

import lucian.exceptions.LucianException;
import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.ui.Ui;


public class MarkCommand extends Command {
    private final String[] words;

    public MarkCommand(String[] words) {
        this.words = words;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws LucianException {
        assert words.length > 1 : "Missing index for mark command.";
        if (words.length == 1) {
            throw new LucianException("You didn't specify the index to mark...");
        }
        int markIndex = Integer.parseInt(words[1]) - 1;
        assert markIndex >= 0 && markIndex < tasks.getSize() : "Invalid task index for mark command.";
        if (markIndex < 0 || markIndex > tasks.getSize()) {
            throw new LucianException("This index isn't in the list...");
        }
        tasks.getTask(markIndex).markAsDone();
        return ui.showMessage("Alright, I've marked this task as done:\n" + tasks.getTask(markIndex));
    }
}
