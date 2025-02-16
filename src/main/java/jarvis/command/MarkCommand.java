package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

/**
 * Represents a command to mark a task as done.
 */
public class MarkCommand extends Command {
    private static final String MESSAGE_MARKED = "Task marked as done: ";
    private final int index;

    /**
     * Constructs a MarkCommand with the specified task index.
     *
     * @param index The index of the task to mark as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (!isValidIndex(index, tasks)) {
            return "Invalid task number.";
        }
        tasks.markTask(index);
        storage.save(tasks.getTasks());
        return MESSAGE_MARKED + tasks.getTask(index);
    }

    /**
     * Checks if the index is within valid bounds.
     */
    private boolean isValidIndex(int index, TaskList tasks) {
        return index >= 0 && index < tasks.getSize();
    }
}
