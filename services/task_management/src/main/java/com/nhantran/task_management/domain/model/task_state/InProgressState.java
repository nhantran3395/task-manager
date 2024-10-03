package com.nhantran.task_management.domain.model.task_state;

import com.nhantran.task_management.domain.model.Task;
import com.nhantran.task_management.domain.model.TaskStatus;

public class InProgressState extends AbstractTaskState {
    public InProgressState(Task task) {
        super(task, TaskStatus.IN_PROGRESS);
    }

    @Override
    public void next() {
        changeState(TaskStateFactory.ofInReview(task));
    }
}
