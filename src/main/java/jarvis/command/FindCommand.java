package jarvis.command;

import jarvis.task.Task; // Fix import order: jarvis.task.Task should come before jarvis.util.*
import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;
import java.util.List; // Fix import order: Standard Java packages should come first
import java.util.stream.Collectors;

/**
 * Represents a command to find tasks based on a keyword.
 */
public class FindCommand extends Command {
    private final String keyword;

    /**
     * Constructs a FindCommand with a keyword.
     *
     * @param keyword The search keyword.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the find command by searching for matching tasks in the TaskList.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui for displaying messages.
     * @param storage The Storage for saving data (not used in this command).
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        if (matchingTasks.isEmpty()) {
            return "No matching tasks found.";
        } else {
            StringBuilder result = new StringBuilder("Here are the matching tasks in your list:\n");
            for (int i = 0; i < matchingTasks.size(); i++) {
                result.append((i + 1)).append(". ").append(matchingTasks.get(i)).append("\n");
            }
            return result.toString().trim();
        }
    }
}
