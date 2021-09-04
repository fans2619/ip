package fan.cs2103t.duke.command;

import static fan.cs2103t.duke.commons.Messages.MESSAGE_SUCCESSFULLY_DELETED_FORMAT;
import static fan.cs2103t.duke.commons.Messages.MESSAGE_TASK_NOT_FOUND;

import fan.cs2103t.duke.task.Task;
import fan.cs2103t.duke.task.TaskList;
import fan.cs2103t.duke.ui.Ui;

/**
 * Represents a Duke's command which deletes tasks from Duke's task list upon execution.
 * <p>
 * This is a subclass of the <code>Command</code> class.
 */
public class DeleteCommand extends Command {

    private final int taskIndex;

    /**
     * Constructs a delete command with the specified index of the task to be deleted from Duke's task list.
     *
     * @param taskIndex the index of the task to be deleted.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Executes this command. Deletes the task with the index from the specified task list.
     * Displays a message to the user through the specified UI if the task is successfully deleted,
     * or exits the current program immediately if the process fails.
     * Returns the status message as a string.
     *
     * @param taskList the task list for the task to be deleted from.
     * @param ui the UI for the message to be displayed through.
     * @return a message to indicate the status of execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui) {
        String output = null;
        try {
            Task t = taskList.getTasks().get(taskIndex - 1);
            String description = t.getDescriptionWithStatus();
            if (taskList.deleteTask(taskIndex - 1)) {
                output = String.format(MESSAGE_SUCCESSFULLY_DELETED_FORMAT, description, taskList.getNumOfTasks());
                ui.displayText(output);
            } else {
                System.exit(1);
            }
        } catch (IndexOutOfBoundsException ex) {
            output = MESSAGE_TASK_NOT_FOUND;
            ui.displayText(output);
        }
        return output;
    }

}
