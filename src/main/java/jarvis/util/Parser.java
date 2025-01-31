package jarvis.util;

import jarvis.command.*;
import jarvis.task.ToDo;
import jarvis.task.Deadline;
import jarvis.task.Event;

/**
 * Parses user input and returns the corresponding command.
 */
public class Parser {

    /**
     * Parses the user input and returns the appropriate command.
     *
     * @param userInput The input string provided by the user.
     * @return A command corresponding to the user input.
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
            default -> throw new IllegalArgumentException("Unknown command: " + commandWord);
        };
    }
}
