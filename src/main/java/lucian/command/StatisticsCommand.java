package lucian.command;

import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.ui.Ui;

public class StatisticsCommand extends Command {
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return ui.showMessage(tasks.getStatistics());
    }
}
