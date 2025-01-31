package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

/**
 * Represents a command to unmark a task (mark it as not done).
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Constructs an UnmarkCommand with the specified task index.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to unmark the specified task.
     *
     * @param tasks   The task list containing the task.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws Exception If an error occurs while saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.unmarkTask(index);
        storage.save(tasks.getTasks());
    }
}
