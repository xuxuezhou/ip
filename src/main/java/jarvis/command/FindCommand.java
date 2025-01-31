package jarvis.command;

import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;
import jarvis.task.Task;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with a keyword.
     * @param keyword The search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for matching tasks in the TaskList.
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui for displaying messages.
     * @param storage The Storage for saving data (not used in this command).
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            System.out.println("No matching tasks found.");
        } else {
            System.out.println("Here are the matching tasks in your list:");
            for (int i = 0; i < matchingTasks.size(); i++) {
                System.out.println((i + 1) + ". " + matchingTasks.get(i));
            }
        }
    }
}
