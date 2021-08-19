import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        this.tasks = list;
    }

    public boolean addTask(Task task) {
        try {
            tasks.add(task);
        } catch (Exception e) {
            System.err.println("Fail to add task: " + e.getMessage());
            return false;
        }
        return true;
    }

    public boolean deleteTask(int taskIndex) {
        try {
            tasks.remove(taskIndex);
        } catch (Exception e) {
            System.err.println("Fail to delete task: " + e.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }

    public int getNumOfTasks() {
        return this.tasks.size();
    }

    public String printList() {
        StringBuilder listAsString = new StringBuilder();
        int count = 0;
        for (Task task : tasks) {
            count++;
            listAsString.append(Ui.space)
                    .append(count)
                    .append(".")
                    .append(task.getDescriptionWithStatus())
                    .append("\n");
        }
        if (count > 0) {
            listAsString.setLength(listAsString.length() - 1);
        }
        return listAsString.toString();
    }

}
