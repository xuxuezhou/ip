package jarvis.task;

/**
 * Represents a simple to-do task.
 */
public class ToDo extends Task {

    /**
     * Constructs a ToDo task.
     *
     * @param description The description of the task.
     */
    public ToDo(String description) {
        super(description);
    }

    /**
     * Converts the task to a format suitable for file storage.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toFileFormat() {
        return "T | " + (isDone ? "1" : "0") + " | " + description;
    }

    /**
     * Returns a string representation of the to-do task.
     *
     * @return A formatted string with task details.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
