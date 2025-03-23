package lucian.command;

import java.io.IOException;

import lucian.exceptions.LucianException;
import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.ui.Ui;

/**
 * Action to be completed according to user input
 */
public abstract class Command {
    /**
     * Executes the command depending on the user input
     * @param tasks taskList to be altered
     * @param storage updates hard disc if tasks are altered
     * @param ui displays the message from a command
     */
    public abstract String execute(TaskList tasks, Storage storage, Ui ui) throws LucianException, IOException;
}
