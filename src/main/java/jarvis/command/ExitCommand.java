package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

/**
 * Represents a command to exit the application.
 */
public class ExitCommand extends Command {

    /**
     * Executes the exit command by displaying a farewell message.
     *
     * @param tasks   The task list (not used in this command).
     * @param ui      The user interface to display messages.
     * @param storage The storage (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * Indicates that this command is an exit command.
     *
     * @return true since this command exits the program.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}
