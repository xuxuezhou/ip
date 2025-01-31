package jarvis.task;

/**
 * Represents a task with a deadline.
 */
public class Deadline extends Task {
    protected String by;  // The deadline for the task.

    /**
     * Constructs a Deadline task.
     *
     * @param description The description of the task.
     * @param by          The deadline of the task.
     */
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Gets the deadline of the task.
     *
     * @return The deadline as a string.
     */
    public String getBy() {
        return by;
    }

    /**
     * Converts the task to a format suitable for file storage.
     *
     * @return A formatted string representing the task.
     */
    @Override
    public String toFileFormat() {
        return "D | " + (isDone ? "1" : "0") + " | " + description + " | " + by;
    }

    /**
     * Returns a string representation of the task.
     *
     * @return A formatted string with task details.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}
