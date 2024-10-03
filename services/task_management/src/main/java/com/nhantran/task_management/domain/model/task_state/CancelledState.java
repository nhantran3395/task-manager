package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

public class CancelledState extends AbstractTaskState {
    public CancelledState(Task task) {
        super(task, TaskStatus.CANCELLED);
    }

    @Override
    public void cancel() {
        // should do nothing since task is already cancelled
    }

    @Override
    public void hold() {
        // should do nothing since task is already cancelled
    }

    @Override
    public void next() {
        // should do nothing since task is already cancelled
    }
}
