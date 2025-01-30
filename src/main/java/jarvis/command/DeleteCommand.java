package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

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
