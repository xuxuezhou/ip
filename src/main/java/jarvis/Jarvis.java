package jarvis;

import jarvis.exception.JarvisException;
import javafx.application.Platform;
import javafx.stage.Stage;
import jarvis.command.Command;
import jarvis.util.Parser;
import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;
import javafx.MainWindow;

/**
 * The main class for the Jarvis task manager.
 */
public class Jarvis {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructs a Jarvis instance with the given file path for storage.
     *
     * @param filePath The file path for storing tasks.
     */
    public Jarvis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Constructs a Jarvis instance without specifying a file path.
     */
    public Jarvis() {
        ui = new Ui();
        tasks = new TaskList();
    }

    /**
     * Processes user input and returns Jarvis's response.
     * If "bye" is entered, the application will clear the chat and exit.
     *
     * @param input The user input command.
     * @return The response from Jarvis.
     */
    public String getResponse(String input) {
        // If the user types "bye", exit the application.
        if (input.equalsIgnoreCase("bye")) {
            Platform.runLater(() -> {
                MainWindow.clearDialogContainer(); // Clear chat window
                Stage stage = MainWindow.getStage();
                if (stage != null) {
                    stage.close(); // Exit JavaFX application
                }
            });
            return "Bye! See you again!";
        }

        // Attempt to get a hardcoded response by parsing the input.
        String hardcodedResponse = null;
        try {
            hardcodedResponse = Parser.parse(input);
        } catch (JarvisException e) {
            // Return an error message if a JarvisException is thrown.
            return "Error: " + e.getMessage();
        }
        if (hardcodedResponse != null) {
            return hardcodedResponse;
        }

        // If no hardcoded response, proceed with normal command parsing.
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(tasks, ui, storage); // This may throw an exception
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
