package lucian;

import java.io.IOException;

import lucian.command.Command;
import lucian.exceptions.LucianException;
import lucian.parser.Parser;
import lucian.storage.Storage;
import lucian.task.TaskList;
import lucian.ui.Ui;

/**
 * Represents the main entry point for the Lucian chatbot.
 * It initializes components and handles user interactions.
 */
public class Lucian {
    private static final String FILE_NAME = "./data/tasks.txt";
    private Ui ui;
    private Storage storage;
    private TaskList tasks;
    private Parser parser;

    /**
     * Initializes the Lucian chatbot with a storage file.
     */
    public Lucian() {
        ui = new Ui();
        storage = new Storage(FILE_NAME);
        parser = new Parser();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
    }

    public String getGreeting() {
        return ui.showWelcome();
    }

    /**
     * Processes a user command.
     *
     * @param userInput The input command entered by the user.
     * @return {@code true} if the program should exit, {@code false} otherwise.
     */
    public String handleCommand(String userInput) {
        try {
            Command command = parser.parse(userInput);
            return command.execute(tasks, storage, ui);
        } catch (LucianException e) {
            return e.getMessage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}
