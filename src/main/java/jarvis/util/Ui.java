package jarvis.util;

import java.util.Scanner;

/**
 * Handles user interaction.
 */
public class Ui {
    private final Scanner scanner;

    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the next command from the user.
     *
     * @return The user input.
     */
    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message to the user.
     */
    public void showWelcome() {
        System.out.println("Hello, sir. I am Jarvis, your task assistant.");
        System.out.println("How can I assist you today?");
    }

    /**
     * Displays a line separator.
     */
    public void showLine() {
        System.out.println("_________________________________________");
    }

    /**
     * Displays an error message when loading data fails.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting fresh.");
    }

    /**
     * Displays a generic error message.
     *
     * @param message The error message.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
