package Thames.command;

import java.io.IOException;
import Thames.TaskList;
import Thames.Ui;
import Thames.Storage;
import Thames.ThamesException;
import Thames.task.Task;

public class AddCommand extends Command {
    protected Task task;

    public AddCommand(Task task) {
        this.task = task;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws ThamesException {
        tasks.add(task);
        ui.addTask(task, tasks.size());
        try {
            storage.save(tasks);
        } catch(IOException e) {
            throw new ThamesException("There was an error saving the file. Please try again.");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof AddCommand) {
            return ((AddCommand) o).task.equals(this.task);
        }
        return false;
    }
}
