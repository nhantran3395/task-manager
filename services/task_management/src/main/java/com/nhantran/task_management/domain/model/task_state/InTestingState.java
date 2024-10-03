package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

public class InTestingState extends AbstractTaskState {
    public InTestingState(Task task) {
        super(task, TaskStatus.IN_TESTING);
    }

    @Override
    public void next() {
        changeState(TaskStateFactory.ofCompleted(task));
    }
}
