package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the command to delete the task from the task list.
     *
     * @param tasks   The task list from which the task is removed.
     * @param ui      The user interface to display messages.
     * @param storage The storage to save the updated task list.
     * @throws Exception If an error occurs while saving.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (index < 0 || index >= tasks.getTasks().size()) {
            System.out.println("Invalid task number.");
            return;
        }
        System.out.println("Removed: " + tasks.getTasks().get(index));
        tasks.deleteTask(index);
        storage.save(tasks.getTasks());
    }
}
