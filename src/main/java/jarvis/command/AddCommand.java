package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;
import jarvis.task.Task;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand extends Command {
    private final Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command to add the task to the task list.
     *
     * @param tasks   The task list to which the task is added.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws Exception If an error occurs while saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        tasks.addTask(task);
        System.out.println("Added: " + task);
        storage.save(tasks.getTasks());
    }

    /**
     * Retrieves the task associated with this command.
     *
     * @return The task that was added.
     */
    public Task getTask() {
        return task;
    }
}
