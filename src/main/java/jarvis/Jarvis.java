package jarvis;

import jarvis.exception.JarvisException;
import javafx.application.Platform;
import javafx.stage.Stage;
import jarvis.command.Command;
import jarvis.util.Parser;
import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;
import jarvis.util.ChatGPTService;
import javafx.MainWindow;

/**
 * The main class for the Jarvis task manager.
 */
public class Jarvis {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean useGPT = false; // Flag to enable GPT responses
    private ChatGPTService gptService;

    /**
     * Constructs a Jarvis instance with the given file path for storage.
     *
     * @param filePath The file path for storing tasks.
     */
    public Jarvis(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        gptService = new ChatGPTService(); // Initialize GPT service
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
        gptService = new ChatGPTService();
    }

    /**
     * Processes user input and returns Jarvis's response.
     * If "bye" is entered, the application will clear the chat and exit.
     *
     * @param input The user input command.
     * @return The response from Jarvis.
     */
    public String getResponse(String input) {
        // Enable GPT mode if user inputs "PSY"
        if (input.equalsIgnoreCase("chatGPT")) {
            useGPT = true;
            return "The conversation will be handled by AI from now...";
        }

        // If GPT mode is enabled, forward input to ChatGPTService
        if (useGPT) {
            return gptService.chatWithGPT(input);
        }

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
            return "Error: " + e.getMessage();
        }
        if (hardcodedResponse != null) {
            return hardcodedResponse;
        }

        // If no hardcoded response, proceed with normal command parsing.
        try {
            Command command = Parser.parseCommand(input);
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }

    /**
     * Runs the main loop for the Jarvis task manager.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        }
    }
}
