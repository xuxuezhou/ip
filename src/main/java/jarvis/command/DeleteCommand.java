package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;
import jarvis.task.Task;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private static final String MESSAGE_INVALID_INDEX = "Invalid task number.";
    private static final String MESSAGE_REMOVED = "Removed: ";
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted (0-based).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!isValidIndex(index, tasks)) {
            return MESSAGE_INVALID_INDEX;
        }
        Task removedTask = tasks.getTask(index);
        tasks.deleteTask(index);
        storage.save(tasks.getTasks());
        return MESSAGE_REMOVED + removedTask;
    }

    /**
     * Validates whether the given index is within valid range.
     */
    private boolean isValidIndex(int index, TaskList tasks) {
        return index >= 0 && index < tasks.getSize();
    }
}
