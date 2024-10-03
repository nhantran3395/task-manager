package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;
import lombok.Getter;

public abstract class AbstractTaskState implements TaskState {
    @Getter
    private final TaskStatus statusRepresentative;
    protected final Task task;

    public AbstractTaskState(Task task, TaskStatus statusRepresentative) {
        this.task = task;
        this.statusRepresentative = statusRepresentative;
    }

    public void cancel() {
        task.changeState(TaskStateFactory.ofCancelled(task));
    }

    public void hold() {
        task.changeState(TaskStateFactory.ofPending(task));
    }

    public void restore() {
    }

    protected void changeState(TaskState nextState) {
        task.changeState(nextState);
    }
}
