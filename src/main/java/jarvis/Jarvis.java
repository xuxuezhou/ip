package jarvis;

import jarvis.command.Command;
import jarvis.util.Parser;
import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

/**
 * The main class for the Jarvis task assistant application.
 * It handles user input, executes commands, and manages task storage.
 */
public class Jarvis {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initializes the Jarvis application.
     *
     * @param filePath The file path to store task data.
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
     * Runs the main loop of the Jarvis application.
     * Reads user commands, processes them, and executes the corresponding actions.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    /**
     * The main entry point of the Jarvis application.
     *
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        new Jarvis("data/Jarvis.txt").run();
    }
}
