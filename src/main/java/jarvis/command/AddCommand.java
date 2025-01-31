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
     * Constructs an AddCommand with the given task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list and saving the updated list.
     *
     * @param tasks   The TaskList to modify.
     * @param ui      The user interface for displaying messages.
     * @param storage The storage handler to save data.
     * @throws Exception If an error occurs while saving the task.
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
     * @return The task being added.
     */
    public Task getTask() {
        return task;
    }
}
