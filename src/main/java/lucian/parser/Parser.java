package lucian.parser;

import lucian.command.ByeCommand;
import lucian.command.Command;
import lucian.command.CreateCommand;
import lucian.command.DeleteCommand;
import lucian.command.ErrorCommand;
import lucian.command.FindKeywordCommand;
import lucian.command.ListCommand;
import lucian.command.MarkCommand;
import lucian.command.StatisticsCommand;
import lucian.command.UnmarkCommand;
import lucian.exceptions.LucianException;

public class Parser {
    private static final String TODO = "todo";
    private static final String DEADLINE = "deadline";
    private static final String EVENT = "event";

    /**
     * Processes a user command.
     *
     * @param userInput The input command entered by the user.
     * @return {@code true} if the program should exit, {@code false} otherwise.
     */
    public Command parse(String userInput) throws LucianException {
        String[] words = userInput.split(" ");
        String command = words[0];
        switch (command) {
        case "bye":
            return new ByeCommand();
        case "find":
            return new FindKeywordCommand(words);
        case "list":
            return new ListCommand();
        case "stats":
            return new StatisticsCommand();
        case "mark":
            return new MarkCommand(words);
        case "unmark":
            return new UnmarkCommand(words);
        case "delete":
            return new DeleteCommand(words);
        case TODO:
        case DEADLINE:
        case EVENT:
            return new CreateCommand(userInput);
        default:
            return new ErrorCommand("You did not give me a valid command...");
        }
    }
}
