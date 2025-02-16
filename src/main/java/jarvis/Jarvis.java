package jarvis;

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
        assert ui != null : "UI should not be null";

        storage = new Storage(filePath);
        assert storage != null : "Storage should not be null";

        try {
            tasks = new TaskList(storage.load());
            assert tasks != null : "TaskList should not be null after loading from storage";
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public Jarvis() {
        ui = new Ui();
        assert ui != null : "UI should not be null";

        tasks = new TaskList();
        assert tasks != null : "TaskList should not be null";
    }

    /**
     * Processes user input and returns Jarvis's response.
     * If "bye" is entered, the application will clear the chat and exit.
     *
     * @param input The user input command.
     * @return The response from Jarvis.
     */
    public String getResponse(String input) {
        assert input != null : "User input should not be null";

        if (input.equalsIgnoreCase("bye")) {
            Platform.runLater(() -> {
                MainWindow.clearDialogContainer(); // Clear chat window
                Stage stage = MainWindow.getStage();
                assert stage != null : "Stage should not be null before closing";
                if (stage != null) {
                    stage.close(); // Exit JavaFX application
                }
            });
            return "Bye! See you again!";
        }
        try {
            Command command = Parser.parse(input);
            assert command != null : "Parsed command should not be null";
            return command.execute(tasks, ui, storage);
        } catch (Exception e) {
            return "Error: " + e.getMessage();
        }
    }
}
