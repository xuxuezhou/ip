package jarvis.util;

import jarvis.command.*;
import jarvis.exception.JarvisException;

/**
 * Parses user input and returns corresponding Command objects or hardcoded responses.
 */
public class Parser {
    /**
     * Parses the given user input and returns an appropriate Command or a hardcoded response.
     *
     * @param userInput The user input string.
     * @return A response string or a Command.
     * @throws JarvisException If the input is invalid.
     */
    public static String parse(String userInput) throws JarvisException {
        userInput = userInput.trim().toLowerCase();  // Convert to lowercase for case-insensitive matching

        return switch (userInput) {
            case "hello" -> "Hi!";
//            case "what's your favorite food?" -> "Matcha!";
//            case "who is your best friend?" -> "It's you! Xiongmao.";
//            case "what's your research interest?" -> "I'm interested in magnetic fields!";
//            case "could you explain maxwell's equation for me?" ->
//                    "Sure. Maxwell's equations are four fundamental equations that describe classical electromagnetism. " +
//                            "They explain how electric and magnetic fields interact with each other and with charges and currents. " +
//                            "These equations unify electricity, magnetism, and optics into a single theory.";
//            // Astronomy-related questions and answers
//            case "what is the largest planet in our solar system?" ->
//                    "Jupiter is the largest planet in our solar system.";
//            case "how far is the sun from the earth?" ->
//                    "On average, the Sun is about 93 million miles (150 million kilometers) away from Earth.";
//            case "what is a black hole?" ->
//                    "A black hole is a region of spacetime where gravity is so strong that nothing, not even light, can escape from it.";
//            case "what is the milky way?" ->
//                    "The Milky Way is the galaxy that contains our Solar System.";
//            case "how many planets are in our solar system?" ->
//                    "There are eight planets in our solar system.";
//            case "what is the speed of light?" ->
//                    "The speed of light in a vacuum is approximately 299,792 kilometers per second (about 186,282 miles per second).";
//            case "what causes a solar eclipse?" ->
//                    "A solar eclipse occurs when the Moon passes between the Earth and the Sun, blocking the Sun's light.";
//            case "what is a supernova?" ->
//                    "A supernova is a powerful and luminous stellar explosion that occurs during the last evolutionary stages of a massive star.";
//            case "how old is the universe?" ->
//                    "The universe is estimated to be around 13.8 billion years old.";
            default -> null; // Not a hardcoded response, continue normal command parsing
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
            case "todo" -> new AddCommand(new jarvis.task.ToDo(parseTaskDescription(parts)));
            case "deadline" -> {
                String[] args = parseDeadline(parts);
                yield new AddCommand(new jarvis.task.Deadline(args[0], args[1]));
            }
            case "event" -> {
                String[] args = parseEvent(parts);
                yield new AddCommand(new jarvis.task.Event(args[0], args[1], args[2]));
            }
//            case "chatGPT" -> {
//                if (parts.length < 2 || parts[1].trim().isEmpty()) {
//                    throw new JarvisException("Oops! Please provide a message to send to ChatGPT.");
//                }
//                yield new GPTCommand(parts[1], new ChatGPTService());
//            }

            default -> throw new JarvisException("I'm sorry, but I don't recognize this command: " + commandWord);
        };
    }

    private static int parseIndex(String[] parts) throws JarvisException {
        if (parts.length < 2) throw new JarvisException("Please provide an index.");
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
        if (parts.length < 2) throw new JarvisException("Invalid deadline format. Use: deadline <description> /by <date>");
        return parts[1].split(" /by ", 2);
    }

    private static String[] parseEvent(String[] parts) throws JarvisException {
        if (parts.length < 2) throw new JarvisException("Invalid event format. Use: event <description> /from <start> /to <end>");
        return parts[1].split(" /from | /to ", 3);
    }
}
