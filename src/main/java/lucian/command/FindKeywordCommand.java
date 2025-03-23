package lucian.command;

import lucian.exceptions.LucianException;
import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.ui.Ui;

public class FindKeywordCommand extends Command {
    private final String[] words;

    public FindKeywordCommand(String[] words) {
        this.words = words;
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws LucianException {
        if (words.length < 1) {
            throw new LucianException("You didn't specify the keyword...");
        }
        String keyword = words[1];
        return ui.showMessage(tasks.findTasks(keyword));
    }
}
