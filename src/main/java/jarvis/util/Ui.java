package jarvis.util;

import java.util.Scanner;

/**
 * Handles user interaction.
 */
public class Ui {
    private final Scanner scanner;

    /**
     * Constructs a UI object for handling user input.
     */
    public Ui() {
        scanner = new Scanner(System.in);
    }

    /**
     * Reads the user's command input.
     *
     * @return The command string entered by the user.
     */
    public String readCommand() {
        System.out.print("> "); // Command prompt
        return scanner.nextLine();
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println("      ____.                  .__        ");
        System.out.println("     |    |____ __________  _|__| ______");
        System.out.println("     |    \\__  \\_  __ \\  \\/ /  |/  ___/");
        System.out.println(" /\\__|    |/ __ \\|  | \\/\\   /|  |\\___ \\ ");
        System.out.println(" \\________(____  /__|    \\_/ |__/____  >");
        System.out.println("               \\/                    \\/ ");
        System.out.println("_________________________________________");
        System.out.println("Hello, sir. I am Jarvis, your task assistant.");
        System.out.println("How can I assist you today?");
        System.out.println("_________________________________________");
    }

    /**
     * Displays a horizontal line separator.
     */
    public void showLine() {
        System.out.println("_________________________________________");
    }

    /**
     * Displays an error message when loading tasks.
     */
    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting fresh.");
    }

    /**
     * Displays an error message.
     *
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
