public class Task {

    protected String description;
    protected boolean isDone;

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
