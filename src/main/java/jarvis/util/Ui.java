// Ui.java
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

    public String readCommand() {
        System.out.print("> ");
        return scanner.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello, sir. I am Jarvis, your task assistant.");
        System.out.println("How can I assist you today?");
    }

    public void showLine() {
        System.out.println("_________________________________________");
    }

    public void showLoadingError() {
        System.out.println("Error loading tasks. Starting fresh.");
    }

    public void showError(String message) {
        System.out.println("Error: " + message);
    }
}
