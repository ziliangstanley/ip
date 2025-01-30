public abstract class Task {

    protected String description;
    protected boolean isDone;

    public abstract String toFileFormat();

    public static Task fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        Task task;
        if (type.equals("T")) {
            task = new ToDo(description);
        } else if (type.equals("D")) {
            String by = parts[3];
            task = new Deadline(description, by);
        } else {
            String from = parts[3];
            String to = parts[4];
            task = new Event(description, from, to);
        }

        if (isDone) task.markAsDone();
        return task;
    }

    @Override
    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.getDescription();
    }

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " ");
    }

    public String getDescription() {
        return this.description;
    }

    public void markAsDone() {
        this.isDone = true;
        System.out.println("Alright, I've marked this task as done:");
        System.out.println(this.toString());
    }

    public void markAsNotDone() {
        this.isDone = false;
        System.out.println("Alright, I've marked this task as not done yet:");
        System.out.println(this.toString());
    }
}
