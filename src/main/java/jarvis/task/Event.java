package jarvis.task;

/**
 * Represents an event task that has a start and end time.
 */
public class Event extends Task {
    protected String from;
    protected String to;

    /**
     * Constructs an Event task.
     *
     * @param description The description of the event.
     * @param from        The start time of the event.
     * @param to          The end time of the event.
     */
    public Event(String description, String from, String to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    /**
     * Gets the start time of the event.
     *
     * @return The start time as a string.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Gets the end time of the event.
     *
     * @return The end time as a string.
     */
    public String getTo() {
        return to;
    }

    /**
     * Converts the task to a format suitable for file storage.
     *
     * @return A formatted string representing the event.
     */
    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    /**
     * Returns a string representation of the event.
     *
     * @return A formatted string with event details.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}
