import java.io.*;
import java.util.ArrayList;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

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

    public void save(TaskList taskList) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (int i = 0; i < taskList.getSize(); i++) {
            writer.write(taskList.getTask(i).toFileFormat() + "\n");
        }
        writer.close();
    }
}
