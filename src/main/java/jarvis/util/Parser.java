package jarvis.util;

import jarvis.command.*;
import jarvis.task.ToDo;
import jarvis.task.Deadline;
import jarvis.task.Event;
import jarvis.exception.JarvisException;

/**
 * Parses user input and returns corresponding Command objects.
 */
public class Parser {
    /**
     * Parses the given user input and returns an appropriate Command.
     *
     * @param userInput The user input string.
     * @return A Command representing the user's input.
     * @throws JarvisException If the input is invalid.
     */
    public static Command parse(String userInput) throws JarvisException {
        String[] parts = userInput.trim().split(" ", 2);
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
            case "todo" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new JarvisException("Oops! The description of a todo cannot be empty.");
                }
                yield new AddCommand(new ToDo(parts[1]));
            }
            case "deadline" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new JarvisException("Oops! The description of a deadline cannot be empty.");
                }
                String[] args = parts[1].split(" /by ", 2);
                if (args.length < 2) {
                    throw new JarvisException("Invalid deadline format. Use: deadline <description> /by <date>");
                }
                yield new AddCommand(new Deadline(args[0], args[1]));
            }
            case "event" -> {
                if (parts.length < 2 || parts[1].trim().isEmpty()) {
                    throw new JarvisException("Oops! The description of an event cannot be empty.");
                }
                String[] args = parts[1].split(" /from | /to ", 3);
                if (args.length < 3) {
                    throw new JarvisException("Invalid event format. Use: event <description> /from <start> /to <end>");
                }
                yield new AddCommand(new Event(args[0], args[1], args[2]));
            }
            default -> throw new JarvisException("I'm sorry, but I don't recognize this command: " + commandWord);
        };
    }

    /**
     * Parses an index from the input command.
     *
     * @param parts The split command parts.
     * @return The parsed index (0-based).
     * @throws JarvisException If the index is missing or invalid.
     */
    private static int parseIndex(String[] parts) throws JarvisException {
        if (parts.length < 2) {
            throw new JarvisException("Oops! Please provide an index for this command.");
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JarvisException("Oops! The index must be a valid number.");
        }
    }

}
