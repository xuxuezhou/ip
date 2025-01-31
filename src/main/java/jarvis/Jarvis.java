package jarvis;

import jarvis.command.Command;
import jarvis.util.Parser;
import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

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
     * Runs the main loop for the Jarvis task manager.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
        }
    }

    /**
     * The main entry point for the Jarvis application.
     *
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        new Jarvis("data/Jarvis.txt").run();
    }
}
