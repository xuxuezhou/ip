package jarvis.command;

import jarvis.task.Task;
import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws Exception {
        if (index < 0 || index >= tasks.getTasks().size()) {
            return "Invalid task number.";
        }
        Task removedTask = tasks.getTasks().get(index);
        tasks.deleteTask(index);
        storage.save(tasks.getTasks());
        return "Removed: " + removedTask;
    }
}
