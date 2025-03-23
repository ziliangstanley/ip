package lucian.command;

import java.io.IOException;

import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.ui.Ui;

/**
 * Command to display a goodbye to the user
 */
public class ByeCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws IOException {
        storage.save(tasks);
        return ui.showGoodbye();
    }
}
