package jarvis.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    // **添加 get 方法**
    public boolean isDone() {
        return isDone;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    public abstract String toFileFormat();

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + description;
    }
}
