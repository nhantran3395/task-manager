package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

public class CompletedState extends AbstractTaskState {
    public CompletedState(Task task) {
        super(task, TaskStatus.COMPLETED);
    }

    @Override
    public void cancel() {
        // should do nothing since task is already finished
    }

    @Override
    public void hold() {
        // should do nothing since task is already finished
    }

    @Override
    public void next() {
        // should do nothing since task is already finished
    }
}
