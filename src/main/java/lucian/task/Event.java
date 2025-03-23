package lucian.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Type of task
 * Has a description, completion status, start time and end time
 */
public class Event extends Task {

    protected LocalDate from;
    protected LocalDate to;

    public Event(String description, LocalDate from, LocalDate to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toFileFormat() {
        return "E | " + (isDone ? "1" : "0") + " | " + description + " | " + from + " | " + to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from.format(DateTimeFormatter.ofPattern("MMM d yyy"))
                + " to: " + to.format(DateTimeFormatter.ofPattern("MMM d yyy")) + ")";
    }
}
