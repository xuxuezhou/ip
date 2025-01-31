package jarvis.util;

import jarvis.command.*;
import jarvis.task.ToDo;
import jarvis.task.Deadline;
import jarvis.task.Event;

/**
 * Handles parsing of user commands and returns the appropriate Command.
 */
public class Parser {
    /**
     * Parses the user input and returns the corresponding Command.
     * @param userInput The user input string.
     * @return The corresponding Command.
     */
    public static Command parse(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> new MarkCommand(Integer.parseInt(parts[1]) - 1);
            case "unmark" -> new UnmarkCommand(Integer.parseInt(parts[1]) - 1);
            case "delete" -> new DeleteCommand(Integer.parseInt(parts[1]) - 1);
            case "todo" -> new AddCommand(new ToDo(parts[1]));
            case "deadline" -> {
                String[] args = parts[1].split(" /by ");
                yield new AddCommand(new Deadline(args[0], args[1]));
            }
            case "event" -> {
                String[] args = parts[1].split(" /from | /to ");
                yield new AddCommand(new Event(args[0], args[1], args[2]));
            }
            case "find" -> new FindCommand(parts[1]);
            default -> throw new IllegalArgumentException("Unknown command: " + commandWord);
        };
    }
}
