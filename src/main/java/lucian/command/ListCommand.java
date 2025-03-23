package lucian.command;

import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.ui.Ui;
public class ListCommand extends Command {

    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.showMessage(tasks.printTasks());
    }
}
