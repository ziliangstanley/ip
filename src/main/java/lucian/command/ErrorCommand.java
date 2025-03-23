package lucian.command;

import lucian.exceptions.LucianException;
import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.ui.Ui;

/**
 * Displays an error message
 */
public class ErrorCommand extends Command {
    private final String message;

    public ErrorCommand(String message) throws LucianException {
        this.message = message;
        throw new LucianException(message);
    }

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.showMessage(message);
    }
}
