package jarvis.command;

import java.util.List;
import java.util.stream.Collectors;

import jarvis.task.Task;
import jarvis.util.Storage;
import jarvis.util.TaskList;
import jarvis.util.Ui;

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

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> matchingTasks = tasks.getTasks().stream()
                .filter(task -> task.getDescription().contains(keyword))
                .collect(Collectors.toList());

        return matchingTasks.isEmpty()
                ? "No matching tasks found."
                : "Here are the matching tasks in your list:\n"
                + matchingTasks.stream()
                .map(task -> (tasks.getTasks().indexOf(task) + 1) + ". " + task)
                .collect(Collectors.joining("\n"));
    }
}
