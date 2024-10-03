package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

public class TodoState extends AbstractTaskState {
    public TodoState(Task task) {
        super(task, TaskStatus.TODO);
    }

    @Override
    public void next() {
        changeState(TaskStateFactory.ofInProgress(task));
    }
}
