package jarvis.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import jarvis.command.AddCommand;
import jarvis.command.Command;
import jarvis.command.DeleteCommand;
import jarvis.command.ExitCommand;
import jarvis.command.FindCommand;
import jarvis.command.ListCommand;
import jarvis.command.MarkCommand;
import jarvis.command.UnmarkCommand;
import jarvis.exception.JarvisException;

/**
 * Parses user input and returns corresponding Command objects or hardcoded responses.
 */
public class Parser {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    /**
     * Parses the given user input and returns an appropriate Command or a hardcoded response.
     *
     * @param userInput The user input string.
     * @return A response string or a Command.
     * @throws JarvisException If the input is invalid.
     */
    public static String parse(String userInput) throws JarvisException {
        userInput = userInput.trim().toLowerCase();

        return switch (userInput) {
        case "hello" -> "Hi!";
        default -> null;
        };
    }

    /**
     * Parses user input and returns a Command object for task-related operations.
     *
     * @param userInput The user input string.
     * @return A Command object.
     * @throws JarvisException If the input is invalid.
     */
    public static Command parseCommand(String userInput) throws JarvisException {
        String[] parts = userInput.trim().split(" ", 2);
        String commandWord = parts[0];

        return switch (commandWord) {
        case "bye" -> new ExitCommand();
        case "list" -> new ListCommand();
        case "mark" -> new MarkCommand(parseIndex(parts));
        case "unmark" -> new UnmarkCommand(parseIndex(parts));
        case "delete" -> new DeleteCommand(parseIndex(parts));
        case "find" -> new FindCommand(parts[1]);
        case "todo" -> new AddCommand(new jarvis.task.ToDo(parseTaskDescription(parts)));
        case "deadline" -> {
            String[] args = parseDeadline(parts);
            yield new AddCommand(new jarvis.task.Deadline(args[0], args[1]));
        }
        case "event" -> {
            String[] args = parseEvent(parts);
            yield new AddCommand(new jarvis.task.Event(args[0], args[1], args[2]));
        }
        default -> throw new JarvisException("I'm sorry, but I don't recognize this command: " + commandWord);
        };
    }

    private static int parseIndex(String[] parts) throws JarvisException {
        if (parts.length < 2) {
            throw new JarvisException("Please provide an index.");
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new JarvisException("Invalid index. It must be a number.");
        }
    }

    private static String parseTaskDescription(String[] parts) throws JarvisException {
        if (parts.length < 2 || parts[1].trim().isEmpty()) {
            throw new JarvisException("Task description cannot be empty.");
        }
        return parts[1];
    }

    private static String[] parseDeadline(String[] parts) throws JarvisException {
        if (parts.length < 2 || !parts[1].contains(" /by ")) {
            throw new JarvisException("Invalid deadline format. Use: deadline <description> /by <date>");
        }
        String[] args = parts[1].split(" /by ", 2);
        if (args.length < 2) {
            throw new JarvisException("Deadline must include a date after /by.");
        }
        validateDateTime(args[1].trim());
        return args;
    }

    private static String[] parseEvent(String[] parts) throws JarvisException {
        if (parts.length < 2 || !parts[1].contains(" /from ") || !parts[1].contains(" /to ")) {
            throw new JarvisException("Invalid event format. Use: event <description> /from <start> /to <end>");
        }
        String[] args = parts[1].split(" /from | /to ", 3);
        if (args.length < 3) {
            throw new JarvisException("Event must include start and end dates.");
        }
        validateDateTime(args[1].trim());
        validateDateTime(args[2].trim());
        return args;
    }

    private static void validateDateTime(String dateStr) throws JarvisException {
        try {
            LocalDateTime.parse(dateStr, DATE_TIME_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new JarvisException(
                "Invalid date format or non-existent date. Use: yyyy-MM-dd HH:mm (e.g., 2024-02-28 18:00)");
        }
    }
}
