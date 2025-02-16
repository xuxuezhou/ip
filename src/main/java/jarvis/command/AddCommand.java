package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;
import jarvis.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private static final String MESSAGE_ADDED = "Added: ";
    private final Task task;

    /**
     * Constructs an AddCommand with the given task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.addTask(task);
        storage.save(tasks.getTasks());
        return MESSAGE_ADDED + task;
    }

    /**
     * Retrieves the task associated with this command.
     *
     * @return The task to be added.
     */
    public Task getTask() {
        return task;
    }
}
