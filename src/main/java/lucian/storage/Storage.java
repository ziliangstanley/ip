package lucian.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import lucian.task.Task;
import lucian.task.TaskList;

/**
 * Handles loading and saving tasks from/to a file.
 */
public class Storage {
    private String filePath;

    /**
     * Constructs a Storage instance with a specified file path.
     *
     * @param filePath The path to the storage file.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads tasks from the storage file.
     *
     * @return A list of tasks.
     * @throws IOException If an error occurs while reading the file.
     */
    public ArrayList<Task> load() throws IOException {
        ArrayList<Task> tasks = new ArrayList<>();
        File file = new File(filePath);

        if (!file.exists()) {
            file.getParentFile().mkdirs();
            file.createNewFile();
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line;
        while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) {
                tasks.add(Task.fromFileFormat(line));
            }
        }
        reader.close();
        return tasks;
    }

    /**
     * Saves tasks to the storage file.
     *
     * @param taskList The list of tasks to save.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void save(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < taskList.getSize(); i++) {
            writer.write(taskList.getTask(i).toFileFormat() + "\n");
        }
        writer.close();
    }
}
