package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

public class InReviewState extends AbstractTaskState {
    public InReviewState(Task task) {
        super(task, TaskStatus.IN_REVIEW);
    }

    @Override
    public void next() {
        changeState(new ReadyForTestState(getTask()));
    }
}
