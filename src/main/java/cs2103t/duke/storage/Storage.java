package cs2103t.duke.storage;

import cs2103t.duke.exception.DukeException;
import cs2103t.duke.task.*;
import cs2103t.duke.ui.Ui;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Storage {

    private final String filePath;
    private final String space = Ui.SPACE;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public TaskList retrieveTaskList() throws DukeException {
        ArrayList<Task> list = new ArrayList<>();
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("\nOOPS!!! Fail to create data file!");
                System.exit(1);
            }
        }
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                Task t = parse(scanner.nextLine());
                list.add(t);
            }
        } catch (Exception e) {
            System.err.println("\nOOPS!!! An error occurred when reading from the data file.");
            throw new DukeException("corrupted data file");
        }
        return new TaskList(list);
    }

    public void storeTaskList(TaskList list) {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(list.printList());
            fw.flush();
            fw.close();
        } catch (IOException e) {
            throw new DukeException(space + "OOPS!!! An error occurred when writing to the data file.");
        }
    }

    private Task parse(String task) {
        task = task.trim();
        int m = task.indexOf('.');
        task = task.substring(m + 1);
        boolean isDone = task.charAt(4) == 'X';
        if (task.startsWith("[T]")) {
            task = task.substring(7);
            Task t = new Todo(task);
            if (isDone) {
                t.markAsDone();
            }
            return t;
        } else if (task.startsWith("[D]")) {
            task = task.substring(7);
            m = task.lastIndexOf(" (by: ");
            String description = task.substring(0, m);
            String by = task.substring(m + 6, task.length() - 1);
            LocalDate deadline = LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy", Locale.ENGLISH));
            Task t = new Deadline(description, deadline);
            if (isDone) {
                t.markAsDone();
            }
            return t;
        } else if (task.startsWith("[E]")) {
            task = task.substring(7);
            m = task.lastIndexOf(" (at: ");
            String description = task.substring(0, m);
            String at = task.substring(m + 6, task.length() - 1);
            Task t = new Event(description, at);
            if (isDone) {
                t.markAsDone();
            }
            return t;
        } else {
            throw new DukeException("fail to parse");
        }
    }

}
