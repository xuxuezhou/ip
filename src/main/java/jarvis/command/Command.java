package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

/**
 * Represents an abstract command that can be executed.
 */
public abstract class Command {

    /**
     * Executes the command with the given task list, user interface, and storage.
     *
     * @param tasks   The task list to modify.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save updates.
     * @throws Exception If an error occurs during execution.
     */
    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws Exception;

    /**
     * Indicates whether this command is an exit command.
     *
     * @return false by default; should be overridden for exit commands.
     */
    public boolean isExit() {
        return false;
    }
}
