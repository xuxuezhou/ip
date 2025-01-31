package jarvis.util;

import jarvis.command.*;
import jarvis.task.ToDo;
import jarvis.task.Deadline;
import jarvis.task.Event;

/**
 * Parses user input and returns corresponding Command objects.
 */
public class Parser {
    /**
     * Parses the given user input and returns an appropriate Command.
     *
     * @param userInput The user input string.
     * @return A Command representing the user's input.
     */
    public static Command parse(String userInput) {
        String[] parts = userInput.split(" ", 2);
        String commandWord = parts[0];

        return switch (commandWord) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> {
                int index = parseIndex(parts);
                yield new MarkCommand(index);
            }
            case "unmark" -> {
                int index = parseIndex(parts);
                yield new UnmarkCommand(index);
            }
            case "delete" -> {
                int index = parseIndex(parts);
                yield new DeleteCommand(index);
            }
            case "todo" -> new AddCommand(new ToDo(parts[1]));
            case "deadline" -> {
                String[] args = parts[1].split(" /by ", 2);
                if (args.length < 2) {
                    throw new IllegalArgumentException("Invalid deadline format. Expected: deadline <description> /by <date>");
                }
                yield new AddCommand(new Deadline(args[0], args[1]));
            }
            case "event" -> {
                String[] args = parts[1].split(" /from | /to ", 3);
                if (args.length < 3) {
                    throw new IllegalArgumentException("Invalid event format. Expected: event <description> /from <start> /to <end>");
                }
                yield new AddCommand(new Event(args[0], args[1], args[2]));
            }
            default -> throw new IllegalArgumentException("Unknown command: " + commandWord);
        };
    }

    /**
     * Parses an index from the input command.
     *
     * @param parts The split command parts.
     * @return The parsed index.
     * @throws IllegalArgumentException If the index is missing or invalid.
     */
    private static int parseIndex(String[] parts) {
        if (parts.length < 2) {
            throw new IllegalArgumentException("Missing index for command.");
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid number format for index.");
        }
    }
}
