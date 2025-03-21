package jarvis;

import jarvis.command.Command;
import jarvis.util.Parser;
import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;
import javafx.MainWindow;
import javafx.application.Platform;
import javafx.stage.Stage;

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
     *
     * @param input The user input command.
     * @return The response from Jarvis.
     */
    public String getResponse(String input) {
        if (input.equalsIgnoreCase("bye")) {
            Platform.runLater(() -> {
                MainWindow.clearDialogContainer();
                Stage stage = MainWindow.getStage();
                if (stage != null) {
                    stage.close();
                }
            });
            return "Bye! See you again!";
        }

        try {
            String hardcodedResponse = Parser.parse(input);
            if (hardcodedResponse != null) {
                return hardcodedResponse;
            }
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
