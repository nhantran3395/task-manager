package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

public class ReadyForTestState extends AbstractTaskState {
    public ReadyForTestState(Task task) {
        super(task, TaskStatus.READY_FOR_TEST);
    }

    @Override
    public void next() {
        changeState(new InTestingState(getTask()));
    }
}
