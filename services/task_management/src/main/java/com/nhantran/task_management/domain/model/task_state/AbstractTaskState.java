package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;
import lombok.Getter;

@Getter
public abstract class AbstractTaskState implements TaskState {
    private final TaskStatus statusRepresentative;
    private final Task task;

    public AbstractTaskState(Task task, TaskStatus statusRepresentative) {
        this.task = task;
        this.statusRepresentative = statusRepresentative;
    }

    public void cancel() {
        task.changeState(new CancelledState(task));
    }

    public void hold() {
        task.changeState(new PendingState(task));
    }

    public void restore() {
    }

    protected void changeState(TaskState nextState) {
        task.changeState(nextState);
    }
}
